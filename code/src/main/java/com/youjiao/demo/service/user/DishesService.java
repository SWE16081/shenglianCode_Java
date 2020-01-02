package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.DishesVO;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author CaiMJ
 * #date 2019/03/09 22:09
 */
@Service
public interface DishesService {
    /**
     * @author CaiMJ
     * 通过 date 查找并且返回DishesVO
     */
    List<DishesVO> getDishesRecordInfo(Date date);
}
