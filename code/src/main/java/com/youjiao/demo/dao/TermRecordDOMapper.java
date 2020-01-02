package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.TermRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TermRecordDOMapper {
    int deleteByPrimaryKey(Integer termId);

    int insert(TermRecordDO record);

    int insertSelective(TermRecordDO record);

    TermRecordDO selectByPrimaryKey(Integer termId);

    int updateByPrimaryKeySelective(TermRecordDO record);

    int updateByPrimaryKey(TermRecordDO record);

    /**
     * @author Ck
     * 查询所有学期记录信息
     */
    @Select("select term_id,year,term_num,grade from term_record order by year desc,term_num desc,grade asc")
    List<TermRecordDO> selectTermRecordAll();

    /**
     * @author Ck
     * 根据学年学期年级查询
     */
    @Select("select * from term_record where year = #{year} and term_num = #{termNum} and grade = #{grade}")
    List<TermRecordDO> selectBySame(Integer year,Byte termNum,Byte grade);


    /**
     * @author Ck
     * 根据学年学期年级查询
     */
    @Select("select term_id from term_record where year = #{year} and term_num = #{termNum} and grade = #{grade}")
    Integer selectIdBySame(Integer year, Byte termNum, Byte grade);
}