package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminFixedCourseVO;
import com.youjiao.demo.dataobject.FixedCourseDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface FixedCourseDOMapper {
    int deleteByPrimaryKey(Integer fixedClassId);

    int insert(FixedCourseDO record);

    int insertSelective(FixedCourseDO record);

    FixedCourseDO selectByPrimaryKey(Integer fixedClassId);

    int updateByPrimaryKeySelective(FixedCourseDO record);

    int updateByPrimaryKey(FixedCourseDO record);

    /**
     * @author Ck
     * 根据id逻辑删除固定课程
     */
    @Update("UPDATE fixed_course set alive = #{alive} where fixed_course_id = #{id}")
    int logicalDeleteById(Integer id,Boolean alive);

    /**
     * @author Ck
     * 添加固定课程并获取自增主键
     */
    @Insert("INSERT INTO fixed_course (name,alive) values(#{name},#{alive})")
    @Options(useGeneratedKeys = true, keyProperty = "fixedCourseId", keyColumn = "fixed_course_id")
    int insertFixture(FixedCourseDO fixedCourseDO);

    /**
     * @author Ck
     * 添加固定课程
     */
    @Insert("INSERT INTO fixed_course (name,alive) values(#{name},#{alive})")
    int insertFixtureByName(String name,Boolean alive);

    /**
     * @author Ck
     * 根据固定课程id查询
     */
    @Select("SELECT fixed_course_id, name from fixed_course where fixed_course_id = #{fixedCourseId}")
    AdminFixedCourseVO selectFixtureById(Integer fixedCourseId);

    /**
     * @author Ck
     * 获取所有固定课程
     */
    @Select("SELECT fixed_course_id, name from fixed_course where alive = #{alive}")
    List<AdminFixedCourseVO> selectFixtureAll(Boolean alive);

    /**
     * @author Ck
     * 根据名称查找固定课程
     */
    @Select("select * from fixed_course where name = #{name} and alive = #{alive}")
    List<FixedCourseDO> selectByName(String name,boolean alive);
}