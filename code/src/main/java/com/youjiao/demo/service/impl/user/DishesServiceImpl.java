package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.DishesVO;
import com.youjiao.demo.dao.DishesRecordInformationDOMapper;
import com.youjiao.demo.service.user.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author CaiMJ
 * #date 2019/03/09 22:10
 */
@Service
public class DishesServiceImpl implements DishesService {
    private final DishesRecordInformationDOMapper dishesRecordInformationDOMapper;

    @Autowired
    public DishesServiceImpl(DishesRecordInformationDOMapper dishesRecordInformationDOMapper) {
        this.dishesRecordInformationDOMapper = dishesRecordInformationDOMapper;
    }

    /**
     * @author CaiMJ
     * 通过date 查找并且返回DishesVO
     */
    @Override
    public List<DishesVO> getDishesRecordInfo(Date date) {
        List<DishesVO> dishesVOList = dishesRecordInformationDOMapper.selectByDatetime(date);
        for (DishesVO dishesVO : dishesVOList) {
            dishesVO.setImg("/static/images/upload/dishesImage/" + dishesVO.getImg());
        }
        return dishesVOList;
    }
}
