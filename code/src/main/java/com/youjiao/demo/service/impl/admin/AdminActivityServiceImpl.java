package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminActivityVO;
import com.youjiao.demo.dao.ActivityDOMapper;
import com.youjiao.demo.dao.ScheduleDOMapper;
import com.youjiao.demo.dataobject.ActivityDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminActivityService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 12:14
 */
@Service("adminActivityService")
public class AdminActivityServiceImpl implements AdminActivityService {
    private final ActivityDOMapper activityDOMapper;
    private final ScheduleDOMapper scheduleDOMapper;

    @Autowired
    public AdminActivityServiceImpl(ActivityDOMapper activityDOMapper, ScheduleDOMapper scheduleDOMapper) {
        this.activityDOMapper = activityDOMapper;
        this.scheduleDOMapper = scheduleDOMapper;
    }


    /**
     * @author Ck
     * 根据字段是否为null插入活动
     */
    @Override
    public void addActivity(AdminActivityVO activityVO) throws BusinessException {
        List<ActivityDO> sameList = activityDOMapper.selectBySame(activityVO.getName(), Constants.ALIVE);
        if (!sameList.isEmpty()) {
            throw new BusinessException(EmBusinessErr.ACTIVITY_ADD_FAILED_REPEAT);
        }
        //字段判空,null和空串统一为""
        if (activityVO.getDescription() == null) {
            activityVO.setDescription("");
        }
        ActivityDO activityDO = new ActivityDO();
        BeanUtils.copyProperties(activityVO, activityDO);
        //设置活着...
        activityDO.setActivityId(null);
        activityDO.setAlive(Constants.ALIVE);
        try {
            activityDOMapper.insertSelective(activityDO);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.ACTIVITY_ADD_FAILED);
        }
    }

    /**
     * @author Ck
     * 根据活动id列表删除活动
     */
    @Override
    @Transactional
    public void logicalDeleteActivityById(Integer activityId) throws BusinessException {
        try {
            activityDOMapper.logicalDeleteByActivityId(activityId, !Constants.ALIVE);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.ACTIVITY_DELETE_FAILED);
        }
    }

    /**
     * @author Ck
     * 修改活动
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyActivity(AdminActivityVO activityVO) throws BusinessException {
        Integer oldId = activityVO.getActivityId();
        ActivityDO dbActivityDO = activityDOMapper.selectByPrimaryKey(activityVO.getActivityId());
        if (dbActivityDO == null) {
            throw new BusinessException(EmBusinessErr.ACTIVITY_MODIFY_FAILED);
        }
        if (dbActivityDO.getAlive().equals(!Constants.ALIVE)) {
            throw new BusinessException(EmBusinessErr.ACTIVITY_MODIFY_FAILED);
        }
        List<ActivityDO> sameList = activityDOMapper.selectBySame(activityVO.getName(), Constants.ALIVE);
        if (!dbActivityDO.getName().equals(activityVO.getName()) && !sameList.isEmpty()) {
            throw new BusinessException(EmBusinessErr.ACTIVITY_MODIFY_FAILED_REPEAT);
        }
        //字段判空,null和空串统一为""
        if (dbActivityDO.getDescription() == null) {
            dbActivityDO.setDescription("");
        }

        ActivityDO newActivity = new ActivityDO();
        newActivity.setName(activityVO.getName());
        newActivity.setDescription(activityVO.getDescription());
        try {
            //逻辑删除旧的
            activityDOMapper.logicalDeleteByActivityId(activityVO.getActivityId(), !Constants.ALIVE);
            //插入并获取id
            activityDOMapper.insertActivity(newActivity);
            Integer newId = newActivity.getActivityId();
            Date today = new Date(System.currentTimeMillis());
            //修改日程表
            scheduleDOMapper.updateNewActivityIdByOldActivityId(oldId, newId, today, Constants.SCHEDULE_TYPE_ACTIVITY);
//            activityDOMapper.updateByPrimaryKeySelective(dbActivityDO);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.ACTIVITY_MODIFY_FAILED);
        }

    }

    /**
     * @author Ck
     * 查询所有活动项
     */
    @Override
    public List<AdminActivityVO> getActivityList() {
        List<AdminActivityVO> list = activityDOMapper.selectActivityAll(Constants.ALIVE);
        return list;
    }
}
