package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.DishesRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DishesRecordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DishesRecordDO record);

    int insertSelective(DishesRecordDO record);

    DishesRecordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DishesRecordDO record);

    int updateByPrimaryKey(DishesRecordDO record);

    /**
     * @author CainMJ
     * 通过recordId列表在用餐记录表中删除数据
     */
    void deleteDishesRecordList(@Param("list")List<Integer> recordId);

    /**
     * @author CainMJ
     * 通过recordId与dishId列表往用餐记录表中插入数据
     */
    void insertToDishesRecord(@Param("recordId") Integer recordId, @Param("list") List<Integer> dishId);

    /**
     * @author Wengwenxin
     * 获取用到了哪些菜品，返回dishId 列表
     */
    @Select("select DISTINCT dish_id FROM dishes_record ")
    List<Integer> getDishIdList();

}