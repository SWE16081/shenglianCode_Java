package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.user.DishesVO;
import com.youjiao.demo.controller.viewobject.user.RecordDishesListVO;
import com.youjiao.demo.dataobject.DishesRecordInformationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Mapper
@Component
public interface DishesRecordInformationDOMapper {

    int deleteByPrimaryKey(Integer recordId);

    int insert(DishesRecordInformationDO record);

    int insertSelective(DishesRecordInformationDO record);

    DishesRecordInformationDO selectByPrimaryKey(Integer recordId);

    /**
     * @author CaiMJ
     * 三表联立     dishes 、 dishes_record 、 dishes_record_information
     * 通过date 查找到 name,material,img,type,order_time 这五个元素
     * 并且按照日期、dishId进行排序
     */
    @Select("select * from dishes d, dishes_record dr, dishes_record_information dri " +
            "where d.dish_id=dr.dish_id and dr.record_id=dri.record_id and order_time=#{date}\n" +
            "order by order_time desc , dr.dish_id desc")
    List<DishesVO> selectByDatetime(Date date);

    /**
     * @author CainMJ
     * 三表联立     dishes 、 dishes_record 、 dishes_record_information
     * 查找 record_id , dish_id , type , name , order_time
     * 通过order_time进行排序
     * 并且按照日期、dishId进行排序
     */
    @Select("select * from dishes d, dishes_record dr, dishes_record_information dri " +
            "where d.dish_id=dr.dish_id and dr.record_id=dri.record_id\n" +
            "order by order_time desc , dr.dish_id desc")
    List<RecordDishesListVO> selectForOrderTime();

    /**
     * @author CaiMJ
     * 三表联立     dishes 、 dishes_record 、 dishes_record_information
     * 通过date 查找菜谱 record_id , dish_id , type , name , order_time
     * 并且按照日期、dishId进行排序
     */
    @Select("select * from dishes d, dishes_record dr, dishes_record_information dri " +
            "where d.dish_id=dr.dish_id and dr.record_id=dri.record_id and order_time=#{date}\n" +
            "order by order_time desc , dr.dish_id desc")
    List<RecordDishesListVO> selectRecordDishByOrderTime(Date date);

    /**
     * @author CainMJ
     * 三表联立     dishes 、 dishes_record 、 dishes_record_information
     * 查找 record_id , dish_id , type , name , order_time
     * 通过Monday与Friday进行日期的判断
     * 通过order_time进行排序
     */
    @Select("select dr.record_id,dr.dish_id,type,name,order_time" +
            " from dishes d, dishes_record dr, dishes_record_information dri" +
            " where d.dish_id=dr.dish_id and dr.record_id=dri.record_id" +
            " and order_time>=#{Monday} and order_time<=#{Friday}" +
            " order by order_time desc , dr.dish_id desc")
    List<RecordDishesListVO> selectRecordDishListForWeek(Date Monday, Date Friday);

    /**
     * @author CainMJ
     * 通过orderTime查找recordId
     */
    @Select("SELECT record_id FROM dishes_record_information WHERE order_time=#{orderTime}")
    List<Integer> selectRecordIdByOrderTime(Date orderTime);

    /**
     * @author CainMJ
     * 通过recordId列表在用餐记录表中删除数据
     */
    void deleteDishesRecordInfoList(List<Integer> recordId);

    int updateByPrimaryKeySelective(DishesRecordInformationDO record);

    int updateByPrimaryKey(DishesRecordInformationDO record);
}