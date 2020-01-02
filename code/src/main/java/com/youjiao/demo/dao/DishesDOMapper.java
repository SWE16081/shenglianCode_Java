package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminDishesNameVO;
import com.youjiao.demo.controller.viewobject.admin.VarietyOfDishesVO;
import com.youjiao.demo.dataobject.DishesDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DishesDOMapper {
    int deleteByPrimaryKey(Integer dishId);

    int insert(DishesDO record);

    int insertSelective(DishesDO record);

    DishesDO selectByPrimaryKey(Integer dishId);

    int updateByPrimaryKeySelective(DishesDO record);

    int updateByPrimaryKey(DishesDO record);

    /**
     * @author WengWenxin
     * 返回所有菜品
     */
    @Select("select * from dishes where alive=1 ORDER BY dish_id DESC ")
    List<VarietyOfDishesVO> selectAll();

    /**
     * @author CainMJ
     * 返回所有菜品ID与Name
     */
    @Select("select dish_id,name from dishes where alive=1 ORDER BY dish_id DESC ")
    List<AdminDishesNameVO> selectAllName();

    /**
     * @author WengWenxin
     * 批量删除菜品 逻辑
     */
    void deleteDishesListLogic(List<Integer> dishesIdList);

    /**
     * @author WengWenxin
     * 批量删除菜品 物理
     */
    void deleteDishesListPhysics(List<Integer> dishesIdList);


    /**
     * @author WengWenxin
     * 根据菜品id返回图片img
     */
    @Select("select img from dishes where dish_id=#{dishId} and alive=1 ")
    String getImg(Integer dishId);

    /**
     * @author WengWenxin
     * 是否有重复菜名
     */
    @Select("select dish_id from dishes where name=#{name} and alive=1 ")
    Integer isNameRepeat(String name);
    /**
     * @author WengWenxin
     * 是否有重复菜名 修改用 排除自身
     */
    @Select("select dish_id from dishes where name =#{name} and alive=1 and dish_id !=#{dishId} ")
    Integer isNameRepeatForUpdate(String name,Integer dishId);

    /**
    * @author CaiMJ
    * 菜品是否被删除
    */
    @Select("select alive from dishes where dish_id=#{dishId}")
    Byte getAliveForDishId(Integer dishId);
}