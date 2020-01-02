package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lyy
 * #date 2019/03/24 15:05
 */
@Service
public interface AdminStudentService {
    /**
     * @author Lyy
     * 根据前端提交的参数添加孩子基本信息
     */
    void addStudentInfo(AdminStudentAddVO adminStudentAddVO) throws BusinessException;

    /**
     * @author Lyy
     * 根据前端提交的参数修改孩子基本信息
     */
    void updateStudentInfo(AdminStudentModifyVO adminStudentModifyVO) throws BusinessException;

    /**
     * @author Lyy
     * 根据前端提交的参数修改家长基本信息
     */
    void updateParentInfo(AdminParentModifyVO adminParentModifyVO) throws BusinessException;

    /**
     * @author Lyy
     * 根据学生id列表删除对应学生信息
     */
    void deleteStudentInfoList(List<Integer> studentIdList) throws BusinessException;

    /**
     * @author Lyy
     * 根据班级id获得孩子信息列表
     */
    List<AdminStudentInfoVO> getStudentInfoList(Integer classId) throws BusinessException;

    /**
     * @author Lyy
     * 获取该机构名下的班级列表
     */
    List<AdminStudentClassListVO> getSchoolClassInfoList() throws BusinessException;

    /**
     * @author Lyy
     * 根据电话号码重置密码
     */
    void resetParentPassword(String telephone)throws BusinessException;

}
