package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminTeacherAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTeacherListVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTeacherModifyVO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kny
 * 2019/04/13
 */
@Service
public interface AdminTeacherService {
    /**
     * @author Kny
     * 通过传回来的信息添加教师信息
     */
    void addTeacher(AdminTeacherAddVO teacherAddVO, Integer schoolId) throws BusinessException;

    /**
     * @author Kny
     * 通过前端传回来的信息修改教师信息
     */
    void modifyTeacher(AdminTeacherModifyVO teacherModifyVO) throws BusinessException;

    /**
     * @author Kny
     * 通过前端传回来的教师id列表逻辑删除教师列表
     */
    void deleteTeacher(List<Integer> teacherList) throws BusinessException;

    /**
     * @author Kny
     * 通过机构id查询教师信息列表
     */
    List<AdminTeacherListVO> getTeacherList(Integer schoolId);
}
