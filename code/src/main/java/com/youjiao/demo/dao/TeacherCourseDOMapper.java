package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.TeacherCourseDO;

public interface TeacherCourseDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourseDO record);

    int insertSelective(TeacherCourseDO record);

    TeacherCourseDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherCourseDO record);

    int updateByPrimaryKey(TeacherCourseDO record);
}