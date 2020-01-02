package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.DishesVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.DishesService;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * @author CaiMJ
 * #date 2019/03/09 22:07
 */
@RestController("dishes")
@RequestMapping("/dishes")
@ComponentScan(basePackages = {"com.youjiao.demo.service.menuService"})
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class DishesController extends BaseController {
    private final DishesService dishesService;

    public DishesController(DishesService dishesService) {
        this.dishesService = dishesService;
    }

    /**
     * @author CaiMJ
     * 通过前端的date数据返回当天菜单
     */
    @PostMapping(value = "getDishes")
    @ResponseBody
    public CommonReturnType getDishes(@RequestParam(name = "date") Date date) throws BusinessException {

        //日期是否为空
        MyValidation.checkObjectNull(date);
        //通过 date 查找所需信息并且返回前端
        List<DishesVO> dishesVOList = dishesService.getDishesRecordInfo(date);
        return CommonReturnType.create(dishesVOList);
    }
}
