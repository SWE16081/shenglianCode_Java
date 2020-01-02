package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.TextbookDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TextbookDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TextbookDO record);

    int insertSelective(TextbookDO record);

    TextbookDO selectByPrimaryKey(Integer id);


    @Select("select *from textbook where course_id=#{courseId}")
    TextbookDO selectByCourseId(Integer courseId);

    int updateByPrimaryKeySelective(TextbookDO record);

    int updateByPrimaryKey(TextbookDO record);

    @Delete("delete from textbook where course_id=#{courseId}")
    int deleteByCourseId(Integer courseId);
}