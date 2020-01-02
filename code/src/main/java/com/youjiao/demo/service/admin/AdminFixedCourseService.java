package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminFixedCourseVO;
import com.youjiao.demo.error.BusinessException;

import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 16:33
 */
public interface AdminFixedCourseService {

    /**
     * @author Ck
     * 添加固定课程
     */
    void addFixedCourse(String name) throws BusinessException;


    /**
     * @author Ck
     * 逻辑删除固定课程
     * 该方法在运行环境下不使用，保留该方法用于开发测试
     */
    @Deprecated
    void logicalDeleteFixedCourse(Integer id) throws BusinessException;

    /**
     * @author Ck
     * 修改固定课程
     */
    void modifyFixedCourse(AdminFixedCourseVO fixedCourseVO) throws BusinessException;

    /**
     * @author Ck
     * 获取固定课程列表
     */
    List<AdminFixedCourseVO> getFixedCourseList();
}
