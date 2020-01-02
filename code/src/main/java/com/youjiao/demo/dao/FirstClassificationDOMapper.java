package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.ScheduleFirstClassifyVO;
import com.youjiao.demo.dataobject.FirstClassificationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FirstClassificationDOMapper {
    int deleteByPrimaryKey(Integer firstId);

    int insert(FirstClassificationDO record);

    int insertSelective(FirstClassificationDO record);

    FirstClassificationDO selectByPrimaryKey(Integer firstId);

    int updateByPrimaryKeySelective(FirstClassificationDO record);

    int updateByPrimaryKey(FirstClassificationDO record);

    /**
     * @author WengWenxin
     * ����²����һ������id
     */
    @Select("select max(first_id) from first_classification")
    int selectMaxId();

    /**
     * @author  Sxl
     * ��ȡ����һ������
     */
    @Select("select *from first_classification order by first_name")
    List<FirstClassificationDO> selectAll();

    /**
     * @author Ck
     * ��ȡ����һ������, �����
     */
    @Select("select first_id,first_name from first_classification")
    @Results({
            @Result(column = "first_id", property = "id"),
            @Result(column = "first_name", property = "name")})
    List<ScheduleFirstClassifyVO> selectScheduleFirstClassify();
}