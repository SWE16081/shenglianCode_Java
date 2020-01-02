package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminCourseListVO;
import com.youjiao.demo.controller.viewobject.admin.ScheduleCourseVO;
import com.youjiao.demo.dataobject.CourseDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CourseDOMapper {
    int deleteByPrimaryKey(Integer courseId);

    int insert(CourseDO record);

    int insertSelective(CourseDO record);

    CourseDO selectByPrimaryKey(Integer courseId);

    int updateByPrimaryKeySelective(CourseDO record);

    int updateByPrimaryKey(CourseDO record);

    

 	/**
     * @author Sxl
     * 四表联立
     */
    @Select("select c.course_id,c.course_name,c.use_textbook,c.first_id,c.second_id," +
            " first_name,second_name," +
            " t.id,t.book_name,t.publish" +
            " from course c left outer join textbook t on (c.course_id=t.course_id) \n" +
            " left outer join first_classification fc on (c.first_id=fc.first_id)\n" +
            " left outer join second_classification sc on (c.second_id=sc.second_id)\n" +
            " where alive=1;")
    List<AdminCourseListVO> selectAllCourse();

    /**
     * @author Sxl
     * 返回所有未逻辑删除的课程
     */
    @Select("select * from course where alive=1")
    List<CourseDO> selectCourseList();

    /**
     * @author Sxl
     * 获得新插入的课程id
     */
    @Select("select max(course_id) from course ")
    int selectMaxId();

    /**
     * @author Sxl
     * 批量逻辑删除课程
     */
    void deleteCourseList(List<Integer> courseIdList);

    /**
     * @author Sxl
     * 查询某一级分类下存在的课程
     */
    @Select("select * from course where first_id=#{firstId} and alive=1")
    List<CourseDO> selectByFirstId(Integer firstId);

    /**
     * @author Sxl
     * 查询某二级分类下存在的课程
     */
    @Select("select * from course where second_id=#{secondId} and alive=1")
    List<CourseDO> selectBySecondId(Integer secondId);

    /**
     * @author Ck
     * 根据二级分类id获取课程列表
     */
    @Select("select course_id,course_name from course where second_id= #{secondId} and alive =true")
    @Results({
            @Result(column = "course_id", property = "id"),
            @Result(column = "course_name", property = "name")})
    List<ScheduleCourseVO> selectScheduleCourse(Integer secondId);
}