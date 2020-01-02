package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminDishesAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminDishesNameVO;
import com.youjiao.demo.controller.viewobject.admin.RecordDishesListGetVO;
import com.youjiao.demo.controller.viewobject.admin.VarietyOfDishesVO;
import com.youjiao.demo.dataobject.DishesDO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author WengWenxin
 * #date 2019/03/23 15:22
 */
@Service
public interface AdminDishesService {

    /**
     * @author WengWenxin
     * 返回菜品列表
     */
    List<VarietyOfDishesVO> getVarietyOfDishesList() throws BusinessException;


    /**
     * @author WengWenxin
     * 添加菜品
     */
    void addVarietyOfDishes(DishesDO dishesDO) throws BusinessException;

    /**
     * @author WengWenxin
     * 修改菜品
     */
    void updateVarietyOfDishes(DishesDO dishesDO) throws BusinessException;

    /**
     * @author WengWenxin
     * 删除菜品
     */
    void deleteVarietyOfDishes(List<Integer> dishesIdList) throws BusinessException;

    /**
     * @author CainMJ
     * 返回用餐记录，根据时间排序(从新到旧)
     */
    List<RecordDishesListGetVO> getRecordDishesListVO();

    /**
     * @author CainMJ
     * 根据时间返回当天的菜谱
     */
    List<RecordDishesListGetVO> getRecordDishesListForDay(Date date);

    /**
     * @author CainMJ
     * 根据时间返回当前周的所有菜谱
     */
    List<RecordDishesListGetVO> getRecordDishesListForWeek(Date date);

    /**
     * @author CainMJ
     * 根据时间与菜品id，在数据库中添加数据
     */
    void addRecordDishes(AdminDishesAddVO adminDishesAddVO) throws BusinessException;

    /**
     * @author CainMJ
     * 根据时间与菜品id，将原本数据删除，然后在数据库中添加数据
     */
    void updateRecordDishes(AdminDishesAddVO adminDishesAddVO) throws BusinessException;

    /**
     * @author CainMJ
     *  返回菜品：dishId与name
     */
    List<AdminDishesNameVO> getDishesNameList();
}
