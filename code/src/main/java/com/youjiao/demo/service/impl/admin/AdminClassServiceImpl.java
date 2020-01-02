package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.dao.ClassDOMapper;
import com.youjiao.demo.dao.StudentDOMapper;
import com.youjiao.demo.dao.TeacherClassDOMapper;
import com.youjiao.demo.dao.TeacherDOMapper;
import com.youjiao.demo.dataobject.ClassDO;
import com.youjiao.demo.dataobject.StudentDO;
import com.youjiao.demo.dataobject.TeacherDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminClassService;
import com.youjiao.demo.util.CompareUtil;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ck
 * #date 2019/03/24 16:23
 */
@Service("adminClassService")
public class AdminClassServiceImpl implements AdminClassService {
    private final ClassDOMapper classDOMapper;
    private final StudentDOMapper studentDOMapper;
    private final TeacherDOMapper teacherDOMapper;
    private final TeacherClassDOMapper teacherClassDOMapper;

    @Autowired
    public AdminClassServiceImpl(ClassDOMapper classDOMapper, StudentDOMapper studentDOMapper, TeacherDOMapper teacherDOMapper, TeacherClassDOMapper teacherClassDOMapper) {
        this.classDOMapper = classDOMapper;
        this.studentDOMapper = studentDOMapper;
        this.teacherDOMapper = teacherDOMapper;
        this.teacherClassDOMapper = teacherClassDOMapper;
    }

    /**
     * @author Ck
     * 根据code获取班级列表(0 全部、1正常班级、2归档班级)
     */
    @Override
    public List<AdminClassListVO> getClassesList(Integer code, Integer schoolId) throws BusinessException {
        final Integer GET_CLASSES_LIST_ALL = 0;
        final Integer GET_CLASSES_LIST_NORMAL = 1;
        final Integer GET_CLASSES_LIST_ARCHIVED = 2;

        List<AdminClassListVO> list;
        if (code.equals(GET_CLASSES_LIST_ALL)) {
            list = classDOMapper.selectBySchoolId(schoolId);
        } else if (code.equals(GET_CLASSES_LIST_NORMAL)) {
            list = classDOMapper.selectBySchoolIdAndIsArchive(schoolId, false);
        } else if (code.equals(GET_CLASSES_LIST_ARCHIVED)) {
            list = classDOMapper.selectBySchoolIdAndIsArchive(schoolId, true);
        } else {
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
        }
        if (list != null) {
            for (AdminClassListVO vo : list) {
                List<AdminClassTeacherListVO> teacherList = teacherDOMapper.selectAdminTeacherListByClassId(vo.getClassId());
                List<AdminClassStudentListVO> studentList = studentDOMapper.selectAdminStudentListByClassId(vo.getClassId());
                vo.setTeacherList(teacherList);
                vo.setStudentList(studentList);
            }
        }
        return list;
    }

    /**
     * @author Ck
     * 根据classid 和 isArchive 设置班级归档状态 （取反）
     */
    @Override
    public void modifyClassArchive(Integer classId, Boolean isArchive) throws BusinessException {
        try {
            classDOMapper.modifyClassArchive(classId, isArchive);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.CLASS_ARCHIVE_MODIFY_FAILED);
        }
    }

    /**
     * @author Ck
     * 通过VO转DO 插入班级
     */
    @Override
    public void addClass(AdminClassAddVO classAddVO, Integer schoolId) throws BusinessException {
        ClassDO classDO = new ClassDO();
        BeanUtils.copyProperties(classAddVO, classDO);

        classDO.setSchoolId(schoolId);
        classDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        classDO.setIsArchive(false);
        classDO.setAlive(Constants.ALIVE);
        try {
            classDOMapper.insert(classDO);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.CLASS_ADD_FAILED);
        }

        //通过foreach和update语句将班级id添加到学生列表
        if (!classAddVO.getStudentIdList().isEmpty()) {
            try {
                studentDOMapper.updateStudentListByStudentId(classDO.getClassId(), classAddVO.getStudentIdList());
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.CLASS_ADD_STUDENT_FAILED);
            }
        }

        //通过foreach和insert语句将班级id和教师id添加到教师授课的班级表中
        if (!classAddVO.getTeacherIdList().isEmpty()) {
            try {
                teacherClassDOMapper.insertListByClassIdAndTeacherId(classDO.getClassId(), classAddVO.getTeacherIdList());
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.CLASS_ADD_TEACHER_FAILED);
            }
        }
    }

    /**
     * @author Ck
     * 根据VO 修改班级信息
     */
    @Override
    public void modifyClass(AdminClassModifyVO classModifyVO) throws BusinessException {
        //获取数据库中数据作为比较
        ClassDO classDO = classDOMapper.selectByPrimaryKey(classModifyVO.getClassInfo().getClassId());

        if (classDO.getIsArchive()) {
            throw new BusinessException(EmBusinessErr.CLASS_MODIFY_FAILED);
        }

        //VO DO若字段值相同，将DO相关字段置为null（不更新），否则设置为VO中对应值，若DO VO完全相同则classDO为null不执行更新
        classDO = (ClassDO) CompareUtil.CompareDOVO(classDO, classModifyVO.getClassInfo());

        if (classDO != null) {
            //System.out.println("link db");
            classDO.setClassId(classModifyVO.getClassInfo().getClassId());
            try {
                classDOMapper.updateByPrimaryKeySelective(classDO);
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.CLASS_MODIFY_FAILED);
            }
        }

        //添加学生列表
        if (!classModifyVO.getAddStudentIdList().isEmpty()) {
            try {
                studentDOMapper.updateStudentListByStudentId(classModifyVO.getClassInfo().getClassId(), classModifyVO.getAddStudentIdList());
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.CLASS_ADD_STUDENT_FAILED);
            }
        }
        //添加教师列表
        if (!classModifyVO.getAddTeacherIdList().isEmpty()) {
            try {
                teacherClassDOMapper.insertListByClassIdAndTeacherId(classModifyVO.getClassInfo().getClassId(), classModifyVO.getAddTeacherIdList());
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.CLASS_ADD_TEACHER_FAILED);
            }
        }
        //删除学生列表
        if (!classModifyVO.getDeleteStudentIdList().isEmpty()) {
            try {
                studentDOMapper.updateClassIdByStudentId(classModifyVO.getDeleteStudentIdList());
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.CLASS_DELETE_STUDENT_FAILED);
            }
        }
        //删除教师列表
        if (!classModifyVO.getDeleteTeacherIdList().isEmpty()) {
            try {
                teacherClassDOMapper.deleteListByClassIdAndTeacherId(classModifyVO.getClassInfo().getClassId(), classModifyVO.getDeleteTeacherIdList());
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.CLASS_DELETE_TEACHER_FAILED);
            }
        }
    }

    /**
     * @author Ck
     * 根据班级id删除班级
     */
    @Override
    @Transactional
    public void deleteClass(Integer classId) throws BusinessException {
        try {
            classDOMapper.logicalDeleteClassById(classId, !Constants.ALIVE);
            studentDOMapper.updateStudentClassIdToNull(classId);
            teacherClassDOMapper.deleteTeacherClassByClassId(classId);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.CLASS_DELETE_FAILED);
        }
    }

    /**
     * @author Kny
     * 根据机构id获取没有班级的学生信息列表并返回
     */
    @Override
    public List<AdminClassStudentListVO> getStudentList(Integer schoolId) {
        List<StudentDO> list = studentDOMapper.selectStudentListBySchoolId(schoolId);
        List<AdminClassStudentListVO> ansList = new ArrayList<>();
        for (StudentDO student : list) {
            ansList.add(new AdminClassStudentListVO(student.getStudentId(), student.getName(), student.getBirthday(), student.getSex()));
        }

        return ansList;
    }

    /**
     * @author Kny
     * 根据机构id获取教师信息列表并返回
     */
    @Override
    public List<AdminClassTeacherListVO> getTeacherList(Integer schoolId) {
        List<TeacherDO> list = teacherDOMapper.selectTeacherListBySchoolId(schoolId);
        List<AdminClassTeacherListVO> ansList = new ArrayList<>();
        for (TeacherDO teacher : list) {
            ansList.add(new AdminClassTeacherListVO(teacher.getTeacherId(), teacher.getTeacherCode(), teacher.getName(), teacher.getSex()));
        }

        return ansList;
    }
}
