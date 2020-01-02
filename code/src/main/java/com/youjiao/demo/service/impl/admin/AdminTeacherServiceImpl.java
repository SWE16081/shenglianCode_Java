package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminTeacherAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTeacherListVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTeacherModifyVO;
import com.youjiao.demo.dao.AgentDOMapper;
import com.youjiao.demo.dao.TeacherDOMapper;
import com.youjiao.demo.dataobject.TeacherDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminTeacherService;
import com.youjiao.demo.util.MD5Util;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kny
 * 2019/04/13
 */
@Service("AdminTeacherService")
public class AdminTeacherServiceImpl implements AdminTeacherService {

    private final TeacherDOMapper teacherDOMapper;
    private final AgentDOMapper agentDOMapper;

    @Autowired
    public AdminTeacherServiceImpl(TeacherDOMapper teacherDOMapper, AgentDOMapper agentDOMapper) {
        this.teacherDOMapper = teacherDOMapper;
        this.agentDOMapper = agentDOMapper;
    }

    /**
     * @author Kny
     * 通过传回来的信息添加教师信息
     */
    @Override
    public void addTeacher(AdminTeacherAddVO teacherVO, Integer schoolId) throws BusinessException {
        TeacherDO teacherDO = new TeacherDO();
        BeanUtils.copyProperties(teacherVO, teacherDO);

        //将机构id插入DO
        teacherDO.setSchoolId(schoolId);
        //设置初始密码为手机号
        teacherDO.setPassword(MD5Util.getMD5(teacherVO.getTelephone()));
        //设置教师未删除
        teacherDO.setAlive(true);

        //教师编号、身份证号、手机号查重
        if (teacherDOMapper.selectTeacherCodeRepeat(teacherVO.getTeacherCode()) != null) {
            throw new BusinessException(EmBusinessErr.TEACHER_CODE_REPEAT);
        } else if (teacherDOMapper.selectIdNumberRepeat(teacherVO.getIdNumber()) != null) {
            throw new BusinessException(EmBusinessErr.TEACHER_ID_NUMBER_REPEAT);
        } else if (teacherDOMapper.selectTelephoneRepeat(teacherVO.getTelephone()) != null) {
            throw new BusinessException(EmBusinessErr.TELEPHONE_EXIST);
        }

        //用insert语句将DO插入教师表
        try {
            teacherDOMapper.insertSelective(teacherDO);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.TEACHER_ADD_FAILED);
        }
    }

    /**
     * @author Kny
     * 通过前端传回来的信息修改教师信息
     */
    @Override
    public void modifyTeacher(AdminTeacherModifyVO teacherModifyVO) throws BusinessException {
        TeacherDO teacherDO = teacherDOMapper.selectByPrimaryKey(teacherModifyVO.getTeacherId());

        //教师编号、身份证号、手机号查重
        if (!teacherDO.getTeacherCode().equals(teacherModifyVO.getTeacherCode()) &&
                teacherDOMapper.selectTeacherCodeRepeat(teacherModifyVO.getTeacherCode()) != null) {
            throw new BusinessException(EmBusinessErr.TEACHER_CODE_REPEAT);
        }
        if (!teacherDO.getIdNumber().equals(teacherModifyVO.getIdNumber()) &&
                teacherDOMapper.selectIdNumberRepeat(teacherModifyVO.getIdNumber()) != null) {
            throw new BusinessException(EmBusinessErr.TEACHER_ID_NUMBER_REPEAT);
        }
        if (!teacherDO.getTelephone().equals(teacherModifyVO.getTelephone()) &&
                teacherDOMapper.selectTelephoneRepeat(teacherModifyVO.getTelephone()) != null) {
            throw new BusinessException(EmBusinessErr.TELEPHONE_EXIST);
        }

        BeanUtils.copyProperties(teacherModifyVO, teacherDO);

        //如果passwordReset为true则重置密码
        if (teacherModifyVO.getPasswordReset()) {
            teacherDO.setPassword(MD5Util.getMD5(teacherModifyVO.getTelephone()));
        }

        //用update语句修改教师信息
        try {
            teacherDOMapper.updateByPrimaryKey(teacherDO);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.TEACHER_MODIFY_FAILED);
        }
    }

    /**
     * @author Kny
     * 通过前端传回来的教师id列表逻辑删除教师列表
     */
    @Override
    public void deleteTeacher(List<Integer> teacherList) throws BusinessException {
        //用update语句将教师列表逻辑删除
        try {
            teacherDOMapper.updateTeacherAliveByTeacherId(teacherList);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.TEACHER_DELETE_FAILED);
        }
    }

    /**
     * @author Kny
     * 通过机构id查询教师信息列表
     */
    @Override
    public List<AdminTeacherListVO> getTeacherList(Integer schoolId) {
        //通过机构id查询机构名称
        String schoolName = agentDOMapper.selectAgentNameByAgentId(schoolId);

        List<TeacherDO> teacherDOList = teacherDOMapper.selectTeacherListBySchoolId(schoolId);
        List<AdminTeacherListVO> teacherListVO = new ArrayList<>();
        for (TeacherDO teacherDO : teacherDOList) {
            teacherListVO.add(new AdminTeacherListVO(teacherDO.getTeacherId(),
                    teacherDO.getTeacherCode(),
                    teacherDO.getName(),
                    teacherDO.getSex(),
                    teacherDO.getIdNumber(),
                    teacherDO.getTelephone(),
                    teacherDO.getBirthday(),
                    teacherDO.getPosition(),
                    teacherDO.getEducation(),
                    teacherDO.getMajor(),
                    teacherDO.getEmail(),
                    schoolName));
        }

        return teacherListVO;
    }
}
