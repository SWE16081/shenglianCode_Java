package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.dao.*;
import com.youjiao.demo.dataobject.StudentTransactionRecordDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminTransactionRecordService;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.util.SessionStuUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lyy
 * #date 2019/04/14 20:00
 */
@Service
public class AdminTransactionRecordImpl implements AdminTransactionRecordService {

    private final HttpServletRequest httpServletRequest;
    private final StudentDOMapper studentDOMapper;
    private final ClassDOMapper classDOMapper;
    private final StudentTransactionRecordDOMapper studentTransactionRecordDOMapper;
    private final AgentDOMapper agentDOMapper;
    private final TeacherDOMapper teacherDOMapper;

    @Autowired
    public AdminTransactionRecordImpl(HttpServletRequest httpServletRequest, StudentDOMapper studentDOMapper, ClassDOMapper classDOMapper, StudentTransactionRecordDOMapper studentTransactionRecordDOMapper, AgentDOMapper agentDOMapper,TeacherDOMapper teacherDOMapper) {
        this.httpServletRequest = httpServletRequest;
        this.studentDOMapper = studentDOMapper;
        this.classDOMapper = classDOMapper;
        this.studentTransactionRecordDOMapper = studentTransactionRecordDOMapper;
        this.agentDOMapper = agentDOMapper;
        this.teacherDOMapper=teacherDOMapper;
    }

    /**
     * @author Lyy
     * 根据前端的机构id获取除该机构id对应的机构以外的所有机构列表
     * 仅包括机构id和机构名称
     */
    @Override
    public List<AdminTransactionRecordSchoolListVO> getSchoolList() throws BusinessException {
        // 获取session中的机构i
        Integer schoolId = SessionStuUtil.initSchoolId(httpServletRequest);
        //获取到除了本机构以外的其他所有机构列表
        return agentDOMapper.getSchoolList(schoolId);
    }

    /**
     * @author Lyy
     * 根据前端的机构id获取该机构名下的所有班级列表
     */
    @Override
    public List<AdminStudentClassListVO> getClassList(Integer schoolId) {
        List<AdminStudentClassListVO> adminStudentClassListVOS = classDOMapper.getClassListBySchoolId(schoolId);
        //补充班主任姓名
        for(AdminStudentClassListVO adminStudentClassListVO:adminStudentClassListVOS){
            Integer teacherId=classDOMapper.selectByPrimaryKey(adminStudentClassListVO.getClassId()).getTeacherId();
            String teacherName=teacherDOMapper.selectByPrimaryKey(teacherId).getName();
            adminStudentClassListVO.setTeacherName(teacherName);
        }
        return adminStudentClassListVOS;
        //return classDOMapper.getTransactionRecordClassListBySchoolId(schoolId);
    }

    /**
     * @author Lyy
     * 根据前端的班级id获取该机构名下的所有学生列表
     */
    @Override
    public List<AdminTransactionRecordStudentListVO> getStudentList(Integer classId) {
        return studentDOMapper.getStudentList(classId);
    }

    /**
     * @author Lyy
     * 根据学生id获取该学生的所有交易记录列表
     */
    @Override
    public List<AdminTransactionRecordListVO> getTransactionRecordList(Integer studentId) {
        return studentTransactionRecordDOMapper.getTransactionRecordList(studentId);
    }

    /**
     * @author Lyy
     * 根据前端提交的开始时间和截至时间和当前的班级id获取在时间范围内的所有该班级学生的交易记录列表
     */
    @Override
    public List<AdminTransactionRecordAllVO> selectTransactionRecordList(AdminTransactionRecordSelectVO adminTransactionRecordSelectVO) {
        //获取该班级中的学生id列表
        List<Integer> studentIdList = studentDOMapper.selectStudentIdListByClassId(adminTransactionRecordSelectVO.getClassId());
        //从托管学生交易记录表中选取学生id为该model中的，且交易时间在范围内的List
        List<AdminTransactionRecordAllVO> adminTransactionRecordAllVOS = new ArrayList<>();
        List<StudentTransactionRecordDO> studentTransactionRecordDOS = studentTransactionRecordDOMapper.selectTransactionRecordList(studentIdList, adminTransactionRecordSelectVO.getStartTime(), adminTransactionRecordSelectVO.getDeadline());
        // 补充一些信息
        try {
            for (StudentTransactionRecordDO studentTransactionRecordDO : studentTransactionRecordDOS) {
                AdminTransactionRecordAllVO adminTransactionRecordAllVO = new AdminTransactionRecordAllVO();
                BeanUtils.copyProperties(studentTransactionRecordDO, adminTransactionRecordAllVO);
                AdminTransactionRecordStudentVO adminTransactionRecordStudentVO = studentDOMapper.selectStudentByStudentId(((AdminTransactionRecordAllVO) adminTransactionRecordAllVO).getStudentId());
                adminTransactionRecordAllVO.setBirthday(adminTransactionRecordStudentVO.getBirthday());
                adminTransactionRecordAllVO.setName(adminTransactionRecordStudentVO.getName());
                Date date = new Date(System.currentTimeMillis());
                adminTransactionRecordAllVO.setOverdue(date.compareTo(adminTransactionRecordAllVO.getDeadline()) == 1 ? true : false);
                adminTransactionRecordAllVOS.add(adminTransactionRecordAllVO);
            }
        } catch (Exception e) {
            System.out.println(MyExceptionUtil.getErrorMsg(e));
        }
        return adminTransactionRecordAllVOS;
    }

    /**
     * @author Lyy
     * 根据前端提交的当前的班级id获取所有该班级学生的交易记录列表
     */
    @Override
    public List<AdminTransactionRecordAllVO> selectAllTransactionRecordList(Integer classId) {
        //获取该班级中的学生id列表
        List<Integer> studentIdList = studentDOMapper.selectStudentIdListByClassId(classId);
        //从托管学生交易记录表中选取学生id为该model中的List
        List<AdminTransactionRecordAllVO> adminTransactionRecordAllVOS = new ArrayList<>();
        List<StudentTransactionRecordDO> studentTransactionRecordDOS = studentTransactionRecordDOMapper.selectAllTransactionRecordList(studentIdList);
        // 补充一些信息
        try {
            for (StudentTransactionRecordDO studentTransactionRecordDO : studentTransactionRecordDOS) {
                AdminTransactionRecordAllVO adminTransactionRecordAllVO = new AdminTransactionRecordAllVO();
                BeanUtils.copyProperties(studentTransactionRecordDO, adminTransactionRecordAllVO);
                AdminTransactionRecordStudentVO adminTransactionRecordStudentVO = studentDOMapper.selectStudentByStudentId(adminTransactionRecordAllVO.getStudentId());
                adminTransactionRecordAllVO.setBirthday(adminTransactionRecordStudentVO.getBirthday());
                adminTransactionRecordAllVO.setName(adminTransactionRecordStudentVO.getName());
                //补充一个变量 是否逾期
                Date date = new Date(System.currentTimeMillis());
                //int compareTo = date1.compareTo(date2);
                //compareTo()方法的返回值，date1小于date2返回-1，date1大于date2返回1，相等返回0
                adminTransactionRecordAllVO.setOverdue(date.compareTo(adminTransactionRecordAllVO.getDeadline()) == 1 ? true : false);
                adminTransactionRecordAllVOS.add(adminTransactionRecordAllVO);
            }
        } catch (Exception e) {
            System.out.println(MyExceptionUtil.getErrorMsg(e));
        }
        return adminTransactionRecordAllVOS;
    }

    /**
     * @author Lyy
     * 根据前端提交的托管学生交易记录数据添加一条记录到对应的机构id中
     */
    @Override
    public void addTransactionRecord(AdminTransactionRecordAddVO adminTransactionRecordAddVO) throws BusinessException {
        // 获取session中的机构id
        Integer schoolId = SessionStuUtil.initSchoolId(httpServletRequest);
        //Integer schoolId = 1;
        StudentTransactionRecordDO studentTransactionRecordDO = new StudentTransactionRecordDO();
        BeanUtils.copyProperties(adminTransactionRecordAddVO, studentTransactionRecordDO);
        studentTransactionRecordDO.setSchoolId(schoolId);
        try {
            studentTransactionRecordDOMapper.insert(studentTransactionRecordDO);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.INSERT_STUDENT_TRANSACTION_RECORD_FAILED);
        }
    }

    /**
     * @author Lyy
     * 根据前端提交的托管学生交易记录数据及序号修改对应的托管学生交易记录
     */
    @Override
    public void updateTransactionRecord(AdminTransactionRecordListVO adminTransactionRecordListVO) {
        StudentTransactionRecordDO studentTransactionRecordDO = new StudentTransactionRecordDO();
        BeanUtils.copyProperties(adminTransactionRecordListVO, studentTransactionRecordDO);
        studentTransactionRecordDOMapper.updateByPrimaryKeySelective(studentTransactionRecordDO);
    }

    /**
     * @author Lyy
     * 根据前端提交的序号List删除对应的托管学生交易记录
     */
    @Override
    public void deleteTransactionRecord(List<Integer> recordIdList) throws BusinessException {
        try {
            studentTransactionRecordDOMapper.deleteStudentTransactionRecordList(recordIdList);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.DELETE_STUDENT_TRANSACTION_RECORD_FAILED);
        }
    }

    /**
     * @author Lyy
     * 根据总园长的机构id查询总的交易记录
     */
    public List<AdminAllTransactionRecordVO> getAllTransactionRecord() {
        //检验身份是否为总园长
        //Integer role =
        List<AdminAllTransactionRecordVO> adminAllTransactionRecordVOS = new ArrayList<>();
        List<StudentTransactionRecordDO> studentTransactionRecordDOS = studentTransactionRecordDOMapper.getAllTransactionRecord();
        // 补充一些信息
        try {
            for (StudentTransactionRecordDO studentTransactionRecordDO : studentTransactionRecordDOS) {
                AdminAllTransactionRecordVO adminAllTransactionRecordVO = new AdminAllTransactionRecordVO();
                BeanUtils.copyProperties(studentTransactionRecordDO, adminAllTransactionRecordVO);
                adminAllTransactionRecordVO.setStudentName(studentDOMapper.selectName(studentTransactionRecordDO.getStudentId()));
                adminAllTransactionRecordVO.setBirthday(studentDOMapper.selectByPrimaryKey(adminAllTransactionRecordVO.getRecordId()).getBirthday());
                adminAllTransactionRecordVO.setSchoolName(agentDOMapper.getAgentNameByAgentId(studentTransactionRecordDO.getSchoolId()));
                adminAllTransactionRecordVOS.add(adminAllTransactionRecordVO);
            }
        } catch (Exception e) {
            System.out.println(MyExceptionUtil.getErrorMsg(e));
        }
        return adminAllTransactionRecordVOS;
    }
}
