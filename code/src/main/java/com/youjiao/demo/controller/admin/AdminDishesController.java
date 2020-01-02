package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.AdminDishAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminDishesAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTimeVO;
import com.youjiao.demo.controller.viewobject.admin.VarietyOfDishesVO;
import com.youjiao.demo.dataobject.DishesDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminDishesService;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author WengWenxin
 * #date 2019/03/23 12:59
 * 管理员端菜谱controller
 */

@RequestMapping("/admin/adminDishes")
@RestController("adminDishes")
public class AdminDishesController extends BaseController {
    private final AdminDishesService adminDishesService;
    //系统当前时间
    private Date nowDate = new Date(new java.util.Date().getTime());
    //日期格式
    private SimpleDateFormat DateType = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    public AdminDishesController(AdminDishesService adminDishesService) {
        this.adminDishesService = adminDishesService;
    }

    /**
     * @author WengWenxin
     * 获取菜品列表
     */
    @GetMapping(value = "/getVarietyOfDishesList")
    @ResponseBody
    public CommonReturnType getVarietyOfDishesList() throws BusinessException {
        List<VarietyOfDishesVO> dishesDOList = adminDishesService.getVarietyOfDishesList();
        return CommonReturnType.create(dishesDOList);
    }

    /**
     * @author WengWenxin
     * 添加菜品
     */
    @PostMapping(value = "/addVarietyOfDishes")
    @ResponseBody
    public CommonReturnType addVarietyOfDishes(@Valid @RequestBody AdminDishAddVO adminDishAddVO, BindingResult result)
            throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.DISHES_COMMIT_FAILED, result);
        //创建DishesDo用来保存数据
        DishesDO dishesDO = new DishesDO();
        dishesDO.setMaterial(adminDishAddVO.getMaterial());
        dishesDO.setName(adminDishAddVO.getName());
        dishesDO.setAlive(true);
        dishesDO.setImg(adminDishAddVO.getImg());
        //将信息存入数据库
        adminDishesService.addVarietyOfDishes(dishesDO);
        //返回成功
        return CommonReturnType.create(null);
    }

    /**
     * @author WengWenxin
     * 更新菜品信息
     */
    @PostMapping(value = "/updateVarietyOfDishes")
    @ResponseBody
    public CommonReturnType updateVarietyOfDishes(@Valid @RequestBody DishesDO dishesDO, BindingResult result)
            throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.DISHES_COMMIT_FAILED, result);
        //更新数据
        adminDishesService.updateVarietyOfDishes(dishesDO);
        //返回成功信息
        return CommonReturnType.create(null);
    }

    /**
     * @author WengWenxin
     * 批量删除菜品
     */
    @PostMapping(value = "/deleteVarietyOfDishes")
    @ResponseBody
    public CommonReturnType deleteVarietyOfDishes(@Valid @RequestBody List<Integer> dishesIdList, BindingResult result)
            throws BusinessException {
        //入参检验
        MyValidation.checkObjectNull(dishesIdList);
        MyValidation.validateObject(EmBusinessErr.DISHES_COMMIT_FAILED, result);
        //从数据库中删除所选的菜品
        adminDishesService.deleteVarietyOfDishes(dishesIdList);
        //返回成功
        return CommonReturnType.create(null);
    }

    /**
     * @author CainMJ
     * 返回所有菜品ID与Name
     */
    @GetMapping(value = "/getDishesNameList")
    @ResponseBody
    public CommonReturnType getDishesNameList() {
        return CommonReturnType.create(adminDishesService.getDishesNameList());
    }

    /**
     * @author CainMJ
     * 返回菜谱，通过时间排序(从新到旧)
     */
    @GetMapping(value = "getRecordDishesList")
    @ResponseBody
    public CommonReturnType getRecordDishesList() {
        //返回用餐记录，根据时间排序(从新到旧)
        return CommonReturnType.create(adminDishesService.getRecordDishesListVO());
    }

    /**
     * @author CainMJ
     * 根据时间返回当天的菜谱
     */
    @PostMapping(value = "getRecordDishesListForDay")
    @ResponseBody
    public CommonReturnType getRecordDishesListForDay(@Valid @RequestBody AdminTimeVO adminTimeVO, BindingResult result)
            throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.DISHES_DATE_NOT_EMPTY, result);
        //根据时间返回当天的菜谱
        return CommonReturnType.create(adminDishesService.getRecordDishesListForDay(adminTimeVO.getDate()));
    }

    /**
     * @author CainMJ
     * 根据提供的时间返回当前周的菜谱(从旧到新)
     */
    @PostMapping(value = "getRecordDishesListForWeek")
    @ResponseBody
    public CommonReturnType getRecordDishesListForWeek(@Valid @RequestBody AdminTimeVO adminTimeVO, BindingResult result)
            throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.DISHES_DATE_NOT_EMPTY, result);
        //根据时间返回当前周的所有菜谱(从旧到新)
        return CommonReturnType.create(adminDishesService.getRecordDishesListForWeek(adminTimeVO.getDate()));
    }

    /**
     * @author CainMJ
     * 添加用餐信息
     */
    @PostMapping(value = "addRecordDishes")
    @ResponseBody
    public CommonReturnType addRecordDishes(@Valid @RequestBody AdminDishesAddVO adminDishesAddVO,
                                            BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.DISHES_LIST_NOT_EMPTY, result);
        //传入的时间比当前时间晚,抛出异常(禁止插入今天之前的菜谱)
        if (DateType.format(adminDishesAddVO.getDate()).compareTo(DateType.format(nowDate)) < 0)
            throw new BusinessException(EmBusinessErr.INSERT_DISHES_FAILED);
        //根据时间与菜品id，在数据库中添加数据
        adminDishesService.addRecordDishes(adminDishesAddVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author CainMJ
     * 修改用餐信息
     */
    @PostMapping(value = "updateRecordDishes")
    @ResponseBody
    public CommonReturnType updateRecordDishes(@Valid @RequestBody AdminDishesAddVO adminDishesAddVO,
                                               BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.DISHES_LIST_NOT_EMPTY, result);
        //传入的时间比当前时间晚,抛出异常(禁止修改今天之前的菜谱)
        if (DateType.format(adminDishesAddVO.getDate()).compareTo(DateType.format(nowDate)) < 0)
            throw new BusinessException(EmBusinessErr.UPDATE_DISHES_FAILED);
        //根据时间与菜品id，将原本数据删除，然后在数据库中添加数据
        adminDishesService.updateRecordDishes(adminDishesAddVO);
        return CommonReturnType.create(null);
    }
}
