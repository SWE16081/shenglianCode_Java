package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.controller.viewobject.admin.AdminClassListVO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ck
 * #date 2019/03/24 16:08
 */
@Service
public interface AdminClassService {
    /**
     * @author Ck
     * 根据code获取班级列表(0 全部、1正常班级、2归档班级)
     */
    List<AdminClassListVO> getClassesList(Integer code, Integer schoolId)  throws BusinessException;

    /**
     * @author Ck
     * 根据classid 和 isArchive 设置班级归档状态 （取反）
     */
    void modifyClassArchive(Integer classId,Boolean isArchive) throws BusinessException;

    /**
     * @author Ck
     * 通过VO转DO 插入班级
     */
    void addClass(AdminClassAddVO classAddVO, Integer schoolId) throws BusinessException;

    /**
     * @author Ck
     * 根据VO 修改班级信息
     */
    void modifyClass(AdminClassModifyVO classModifyVO) throws BusinessException;

    /**
     * @author Ck
     * 根据班级id删除班级
     */
    void deleteClass(Integer classId) throws BusinessException;
	
	/**
     * @author Kny
     * 根据机构id获取没有班级的学生信息列表并返回
     */
    List<AdminClassStudentListVO> getStudentList(Integer schoolId);

    /**
     * @author Kny
     * 根据机构id获取教师信息列表并返回
     */
    List<AdminClassTeacherListVO> getTeacherList(Integer schoolId);
}
