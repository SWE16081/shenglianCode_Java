package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminChapterAndCourseVO;
import com.youjiao.demo.controller.viewobject.admin.AdminChapterWithLessonPlanVO;
import com.youjiao.demo.controller.viewobject.admin.ScheduleChapterVO;
import com.youjiao.demo.dataobject.ChapterDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ChapterDOMapper {
    /**
     * @author Zjp
     * 跟据章节id获取课程id
     */
    @Select("select course_id from chapter where chapter_id = #{chapterId}")
    Integer getCourseIdByChapterId(Integer chapterId);

    /**
     * @author Sxl
     * 跟据课程id获取章节及其教案情况
     * 两表联立
     */
    @Select("select chapter.*,lp.file_url,lp.lesson_plan_id,lp.filename" +
            " from chapter left outer join lesson_plan lp on (chapter.chapter_id=lp.chapter_id) \n" +
            " where course_id=#{courseId} and alive=1" +
            " order by chapter_id;")
    List<AdminChapterWithLessonPlanVO> selectChapterByCourseId(Integer courseId);

    int deleteByPrimaryKey(Integer chapterId);

    int insert(ChapterDO record);

    int insertSelective(ChapterDO record);

    ChapterDO selectByPrimaryKey(Integer chapterId);

    int updateByPrimaryKeySelective(ChapterDO record);

    int updateByPrimaryKey(ChapterDO record);

    /**
     * @author Ck
     * 根据章节id查询对应的章节和课程信息
     */
    @Select("SELECT\n" +
            "	chapter_id,\n" +
            "	chapter.course_id,\n" +
            "	number,\n" +
            "	NAME,\n" +
            "	description,\n" +
            "	course_name\n" +
            "FROM\n" +
            "	chapter,\n" +
            "	course\n" +
            "WHERE\n" +
            "	chapter.course_id = course.course_id\n" +
            "AND chapter_id = #{chapterId}")
    AdminChapterAndCourseVO selectChapterAndCourseByChapterId(Integer chapterId);

    /**
     * @author Sxl
     * 获取某一门课程章节列表
     */
    @Select("select * from chapter where course_id = #{courseId} and alive=1")
    List<ChapterDO> selectByCourseId(Integer corseId);

    /**
     * @author Sxl
     * 删除某一门课程章节列表
     */
    @Delete("update chapter set alive=0 where course_id=#{courseId}")
    void deleteChapterByCourseId(Integer courseId);

    /**
     * @author Sxl
     * 获取某一门课程章节列表
     */
    @Select("select * from chapter where course_id=#{courseId} and alive=1")
    List<ChapterDO> getChapterByCourseId(Integer courseId);

    /**
     * @author Sxl
     * 批量逻辑删除章节
     */
    void logicDeleteChapterList(List<Integer> courseIdList);

    /**
     * @author Ck
     * 根据课程id获取章节
     */
    @Select("select chapter_id,number,name from chapter where course_id= #{courseId} and alive =true")
    @Results({
            @Result(column = "chapter_id", property = "id"),
            @Result(column = "number", property = "number"),
            @Result(column = "name", property = "name")})
    List<ScheduleChapterVO> selectScheduleChapter(Integer courseId);
}