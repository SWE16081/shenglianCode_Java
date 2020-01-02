package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminActivityVO;
import com.youjiao.demo.error.BusinessException;

import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 12:06
 */
public interface AdminActivityService {
    /**
     * @author Ck
     * 根据字段是否为null插入活动
     */
    void addActivity(AdminActivityVO activityVO) throws BusinessException;

    /**
     * @author Ck
     * 根据活动id列表删除活动
     */
    void logicalDeleteActivityById(Integer activityId) throws BusinessException;

    /**
     * @author Ck
     * 修改活动
     */
    void modifyActivity(AdminActivityVO activityVO) throws BusinessException;

    /**
     * @author Ck
     * 查询所有活动项
     */
    List<AdminActivityVO> getActivityList();
}
