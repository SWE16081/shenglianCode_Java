package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.ScheduleSecondClassifyVO;
import com.youjiao.demo.dataobject.SecondClassificationDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SecondClassificationDOMapper {
    int deleteByPrimaryKey(Integer secondId);

    int insert(SecondClassificationDO record);

    int insertSelective(SecondClassificationDO record);

    SecondClassificationDO selectByPrimaryKey(Integer secondId);

    int updateByPrimaryKeySelective(SecondClassificationDO record);

    int updateByPrimaryKey(SecondClassificationDO record);

    /**
     * @author Sxl
     * 通过一级分类id查询其所属二级分类
     */
    @Select("select * from second_classification where first_id=#{firstId} order by second_name")
    List<SecondClassificationDO> selectByFirstId(Integer firstId);

    @Delete("delete from second_classification where first_id=#{firstId}")
    void deleteByFirstId(Integer firstId);


    /**
     * @author Ck
     * 根据一级分类id获取二级分类, 并填充
     */
    @Select("select second_id,second_name from second_classification where first_id = #{firstId}")
    @Results({
            @Result(column = "second_id", property = "id"),
            @Result(column = "second_name", property = "name")})
    List<ScheduleSecondClassifyVO> selectScheduleSecondClassify(Integer firstId);
}