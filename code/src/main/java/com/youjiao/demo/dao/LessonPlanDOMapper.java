package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.LessonPlanDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LessonPlanDOMapper {
    /**
     * @author Zjp
     * 根据章节id获取教案对象
     */
    @Select("select * from lesson_plan where chapter_id=#{chapterId}")
    LessonPlanDO selectByChapterId(Integer chapterId);

    /**
     * @author Zjp
     * 根据文件路径获取教案对象，判断同一门课程下是否有重复的文件名
     */
    @Select("select * from lesson_plan where file_url=#{fileUrl}")
    LessonPlanDO selectByFileUrl(String fileUrl);

    /**
     * @author Sxl
     * 根据chapterId删除教案对象
     */
    @Delete("delete from lesson_plan where chapter_id=#{chapterId}")
    void deleteByChapterId(Integer chapterId);

    int deleteByPrimaryKey(Integer lessonPlanId);

    int insert(LessonPlanDO record);

    int insertSelective(LessonPlanDO record);

    LessonPlanDO selectByPrimaryKey(Integer lessonPlanId);

    int updateByPrimaryKeySelective(LessonPlanDO record);

    int updateByPrimaryKey(LessonPlanDO record);
}