package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.dao.*;
import com.youjiao.demo.dataobject.ParentDO;
import com.youjiao.demo.dataobject.StudentDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminStudentService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MD5Util;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.util.SessionStuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author Lyy
 * #date 2019/03/24 15:06
 */
@Service
public class AdminStudentServiceImpl implements AdminStudentService {

    private final StudentDOMapper studentDOMapper;
    private final HttpServletRequest httpServletRequest;
    private final ParentDOMapper parentDOMapper;
    private final ClassDOMapper classDOMapper;
    private final AgentDOMapper agentDOMapper;
    private final TeacherDOMapper teacherDOMapper;

    @Autowired
    public AdminStudentServiceImpl(StudentDOMapper studentDOMapper, HttpServletRequest httpServletRequest, ParentDOMapper parentDOMapper, ClassDOMapper classDOMapper,AgentDOMapper agentDOMapper,TeacherDOMapper teacherDOMapper) {
        this.studentDOMapper = studentDOMapper;
        this.httpServletRequest = httpServletRequest;
        this.parentDOMapper = parentDOMapper;
        this.classDOMapper = classDOMapper;
        this.agentDOMapper=agentDOMapper;
        this.teacherDOMapper=teacherDOMapper;
    }

    /**
     * @author Lyy
     * 根据前端提交的参数添加孩子基本信息
     */
    @Override
    public void addStudentInfo(AdminStudentAddVO adminStudentAddVO) throws BusinessException {
        // 获取session中的机构id
        Integer schoolId = SessionStuUtil.initSchoolId(httpServletRequest);
        //Integer schoolId = 1;
        //身份证倒数第二位奇男偶女，是否需要和性别进行比对？
        //假设学生性别男为true，女为false
        //身份证倒数第二位奇数为男，偶数为女，即对2取模=0是女，!=0是男
        //即equals后半部分式子为true是女，为false是男
        //当二者相等时，说明学生性别与身份证上不符，则报错
        if (adminStudentAddVO.getsSex().equals(Integer.parseInt(adminStudentAddVO.getIdNumber().substring(16, 17)) % 2 == 0)) {
            throw new BusinessException(EmBusinessErr.CHILD_SEX_ILLEGAL);
        }
        //判断身份证号是否重复
        if (studentDOMapper.selectByIdNumber(adminStudentAddVO.getIdNumber()) != null) {
            throw new BusinessException(EmBusinessErr.CHILD_ID_NUMBER_ALREADY_EXIST, "身份证号已存在");
        }
        //生日是否要与身份证进行对比验证？ 暂时不
        //向父母表中插入数据
//        String a=MD5Util.getMD5(adminStudentAddVO.getTelephone());
//        parentDOMapper.addParentInfo(adminStudentAddVO.getTelephone(), adminStudentAddVO.getJob(), adminStudentAddVO.getAddress(), adminStudentAddVO.getpSex(), adminStudentAddVO.getParentName(),a);
//        Integer parentId = parentDOMapper.getParentIdByTelephone(adminStudentAddVO.getTelephone());
        //向学生表中插入数据
        if(adminStudentAddVO.getClassId()!=null){
            studentDOMapper.addAllStudentInfo(adminStudentAddVO.getName(), adminStudentAddVO.getsSex(), adminStudentAddVO.getBirthday(), adminStudentAddVO.getIdNumber(), adminStudentAddVO.getStartGrade(), schoolId,adminStudentAddVO.getClassId());
        }else {
            studentDOMapper.addStudentInfo(adminStudentAddVO.getName(), adminStudentAddVO.getsSex(), adminStudentAddVO.getBirthday(), adminStudentAddVO.getIdNumber(), adminStudentAddVO.getStartGrade(), schoolId);
        }
    }

    /**
     * @author Lyy
     * 根据前端提交的参数修改孩子基本信息
     */
    @Override
    public void updateStudentInfo(AdminStudentModifyVO adminStudentModifyVO) throws BusinessException {
        StudentDO studentDO = studentDOMapper.selectByPrimaryKey(adminStudentModifyVO.getStudentId());
        //调用DOMapper.xml中的方法，将未做修改的属性设置为null
        studentDO.setSex(adminStudentModifyVO.getsSex().equals(studentDO.getSex()) ? null : adminStudentModifyVO.getsSex());
        studentDO.setBirthday(adminStudentModifyVO.getBirthday().equals(studentDO.getBirthday()) ? null : adminStudentModifyVO.getBirthday());
        studentDO.setName(adminStudentModifyVO.getName().equals(studentDO.getName()) ? null : adminStudentModifyVO.getName());
        studentDO.setStartGrade(adminStudentModifyVO.getStartGrade().equals(studentDO.getStartGrade()) ? null : adminStudentModifyVO.getStartGrade());
        //若身份证号未修改则置为null
        if (adminStudentModifyVO.getIdNumber().equals(studentDO.getIdNumber())) {
            studentDO.setIdNumber(null);
        } else {//如果身份证号有改动则需判断是否有重复
            if (studentDOMapper.selectByIdNumber(adminStudentModifyVO.getIdNumber()) != null) {
                throw new BusinessException(EmBusinessErr.CHILD_ID_NUMBER_ALREADY_EXIST, "身份证号已存在");
            } else {
                //身份证倒数第二位奇男偶女，是否需要和性别进行比对？
                //假设学生性别男为true，女为false
                //身份证倒数第二位奇数为男，偶数为女，即对2取模=0是女，!=0是男
                //即equals后半部分式子为true是女，为false是男
                //当二者相等时，说明学生性别与身份证上不符，则报错
                if (adminStudentModifyVO.getsSex().equals(Integer.parseInt(adminStudentModifyVO.getIdNumber().substring(16, 17)) % 2 == 0)) {
                    throw new BusinessException(EmBusinessErr.CHILD_SEX_ILLEGAL);
                }
                studentDO.setIdNumber(adminStudentModifyVO.getIdNumber());
            }
        }
        //更新学生表数据
        studentDOMapper.updateByPrimaryKeySelective(studentDO);
    }

    /**
     * @author Lyy
     * 根据前端提交的参数修改家长基本信息
     */
    @Override
    public void updateParentInfo(AdminParentModifyVO adminParentModifyVO) throws BusinessException {

        ParentDO parentDO = parentDOMapper.selectByPrimaryKey(adminParentModifyVO.getParentId());
        //调用DOMapper.xml中的方法，将未做修改的属性设置为null
        parentDO.setName(parentDO.getName().equals(adminParentModifyVO.getParentName()) ? null : adminParentModifyVO.getParentName());
        parentDO.setJob(parentDO.getJob().equals(adminParentModifyVO.getJob()) ? null : adminParentModifyVO.getJob());
        parentDO.setAddress(parentDO.getAddress().equals(adminParentModifyVO.getAddress()) ? null : adminParentModifyVO.getAddress());
        parentDO.setSex(parentDO.getSex().equals(adminParentModifyVO.getpSex()) ? null : adminParentModifyVO.getpSex());
        //若电话号码未修改则置为null
        if (adminParentModifyVO.getTelephone().equals(parentDO.getTelephone())) {
            parentDO.setTelephone(null);
        } else {//若有改动电话号码则需要判断是否已被注册
            if (parentDOMapper.selectByTelephone(adminParentModifyVO.getTelephone()) != null) {
                throw new BusinessException(EmBusinessErr.TELEPHONE_EXIST, "手机号已被注册");
            } else {
                parentDO.setTelephone(adminParentModifyVO.getTelephone());
            }
        }
        parentDOMapper.updateByPrimaryKeySelective(parentDO);
    }

    /**
     * @author Lyy
     * 根据学生id列表删除对应学生信息
     */
    @Override
    public void deleteStudentInfoList(List<Integer> studentIdList) {
        for (Integer i : studentIdList) {
            //根据学生id获取家长id
            Integer parentId = studentDOMapper.selectByPrimaryKey(i).getParentId();
            //将对应学生从学生表中删除
            studentDOMapper.updateStudentAliveByStudentId(i);
            //如果学生表中仍能查找到该父母id，则说明该父母有多个孩子（一个电话号码注册了多个小孩）
            //那么不删除家长表中的家长信息
            //否则删除
            if (parentId!=null&&studentDOMapper.getCountByParentId(parentId) == 0) {
                parentDOMapper.deleteByPrimaryKey(parentId);
            }
        }
    }

    /**
     * @author Lyy
     * 根据班级id获得孩子信息列表
     */
    @Override
    public List<AdminStudentInfoVO> getStudentInfoList(Integer classId) {
        //获取学生信息列表
        List<AdminStudentInfoVO> studentInfoList = studentDOMapper.getStudentInfoListByClassId(classId);
        //补充家长部分信息及班级名称
        for (AdminStudentInfoVO adminStudentInfoVO : studentInfoList) {
            StudentDO studentDO = studentDOMapper.selectByPrimaryKey(adminStudentInfoVO.getStudentId());
            adminStudentInfoVO.setsSex(studentDO.getSex());
            Integer parentId=adminStudentInfoVO.getParentId();
            if(parentId!=null) {
                ParentDO parentDO = parentDOMapper.selectByPrimaryKey(adminStudentInfoVO.getParentId());
                adminStudentInfoVO.setPname(parentDO.getName());
                adminStudentInfoVO.setAddress(parentDO.getAddress());
                adminStudentInfoVO.setJob(parentDO.getJob());
                adminStudentInfoVO.setTelephone(parentDO.getTelephone());
                adminStudentInfoVO.setpSex(parentDO.getSex());
            }
            adminStudentInfoVO.setClassName(classDOMapper.selectByPrimaryKey(studentDO.getClassId()).getName());
            adminStudentInfoVO.setAgentName(agentDOMapper.getAgentNameByAgentId(studentDO.getSchoolId()));
        }
        return studentInfoList;
    }

    /**
     * @author Lyy
     * 获取该机构名下的班级列表
     * 包括班级id，班级名称，班主任名称，是否归档
     */
    @Override
    public List<AdminStudentClassListVO> getSchoolClassInfoList() {
        // 获取session中的机构id
        Integer schoolId = (Integer) (httpServletRequest.getSession().getAttribute(Constants.SCHOOL_ID_SESSION));
        //前端测试
        //Integer schoolId = 1;
        List<AdminStudentClassListVO> adminStudentClassListVOS = classDOMapper.getClassListBySchoolId(schoolId);
        //补充班主任姓名
        for(AdminStudentClassListVO adminStudentClassListVO:adminStudentClassListVOS){
            Integer teacherId=classDOMapper.selectByPrimaryKey(adminStudentClassListVO.getClassId()).getTeacherId();
            String teacherName=teacherDOMapper.selectByPrimaryKey(teacherId).getName();
            adminStudentClassListVO.setTeacherName(teacherName);
        }
        return adminStudentClassListVOS;
    }

    /**
     * @author Lyy
     * 根据电话号码重置密码
     */
    public void resetParentPassword(String telephone)throws BusinessException{
        String a=MD5Util.getMD5(telephone);
        parentDOMapper.resetPassword(telephone,a);
    }
}
