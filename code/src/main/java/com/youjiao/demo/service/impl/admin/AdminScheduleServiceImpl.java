package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.dao.ActivityDOMapper;
import com.youjiao.demo.dao.ChapterDOMapper;
import com.youjiao.demo.dao.FixedCourseDOMapper;
import com.youjiao.demo.dao.ScheduleDOMapper;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminScheduleService;
import com.youjiao.demo.service.model.AdminScheduleListMO;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/16 15:16
 */
@Service
public class AdminScheduleServiceImpl implements AdminScheduleService {
    private final ScheduleDOMapper scheduleDOMapper;
    private final ActivityDOMapper activityDOMapper;
    private final ChapterDOMapper chapterDOMapper;
    private final FixedCourseDOMapper fixedCourseDOMapper;

    public AdminScheduleServiceImpl(ScheduleDOMapper scheduleDOMapper,
                                    ActivityDOMapper activityDOMapper,
                                    ChapterDOMapper chapterDOMapper,
                                    FixedCourseDOMapper fixedCourseDOMapper) {
        this.scheduleDOMapper = scheduleDOMapper;
        this.activityDOMapper = activityDOMapper;
        this.chapterDOMapper = chapterDOMapper;
        this.fixedCourseDOMapper = fixedCourseDOMapper;
    }


    /**
     * @author Ck
     * 根据日期和学期id查询一周的课程计划表
     */
    @Override
    public List<AdminScheduleRowVO> getScheduleWeekList(Date date, Integer termId) {
        //由行组成的list
        List<AdminScheduleRowVO> list = new ArrayList<>();
        //数据库中查询到的日期区间内的所有schedule, 按日期和节次排序
        List<AdminScheduleVO> scheduleList = scheduleDOMapper.selectFromDateOnWeek(date, termId);

        int index = 0;
        AdminScheduleRowVO newRow = new AdminScheduleRowVO();
        for (AdminScheduleVO scheduleVO : scheduleList) {
            switch (index % 5) {
                case 0: {
                    newRow.setMonday(getAdminScheduleList(scheduleVO));
                    break;
                }
                case 1: {
                    newRow.setTuesday(getAdminScheduleList(scheduleVO));
                    break;
                }
                case 2: {
                    newRow.setWednesday(getAdminScheduleList(scheduleVO));
                    break;
                }
                case 3: {
                    newRow.setThursday(getAdminScheduleList(scheduleVO));
                    break;
                }
                case 4: {
                    newRow.setFriday(getAdminScheduleList(scheduleVO));
                    list.add(newRow);
                    newRow = new AdminScheduleRowVO();
                    break;
                }
            }
            index++;
        }
        return list;
    }

    /**
     * @author Ck
     * 添加一周的课程计划表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addScheduleWeek(AdminSchedulePostVO adminSchedulePost, Date date) throws BusinessException {
        Integer termId = adminSchedulePost.getTermId();
        //如果这个日期的一周都没有一条数据
        List<AdminScheduleVO> list = scheduleDOMapper.selectFromDateOnWeek(date, termId);
        if (date != null && list.isEmpty()) {
            try {
                for (AdminScheduleListMO item : adminSchedulePost.getScheduleForm()) {
                    Integer number = item.getNumber();
                    //因为接口设计不好故...
                        scheduleDOMapper.insertSchedule(item.getMonday(), termId, number);
                        scheduleDOMapper.insertSchedule(item.getTuesday(), termId, number);
                        scheduleDOMapper.insertSchedule(item.getWednesday(), termId, number);
                        scheduleDOMapper.insertSchedule(item.getThursday(), termId, number);
                        scheduleDOMapper.insertSchedule(item.getFriday(), termId, number);
                }
            } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.SCHEDULE_ADD_FAILED);
            }
        } else {
            throw new BusinessException(EmBusinessErr.SCHEDULE_ADD_FAILED_THE_WEEK_HAD_COURSE);
        }

    }

    /**
     * @author Ck
     * 删除一周的课程计划表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteScheduleWeek(Date date, Integer termId) throws BusinessException {
        try {
            scheduleDOMapper.deleteFromDateOnWeek(date, termId);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.SCHEDULE_DELETE_FAILED);
        }
    }

    /**
     * @author Ck
     * 修改一周的课程计划表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyScheduleWeek(AdminSchedulePostVO adminSchedulePost) throws BusinessException {
        try {
            for (AdminScheduleListMO item : adminSchedulePost.getScheduleForm()) {
                //因为接口设计不好故...
                scheduleDOMapper.modifySchedule(item.getMonday());
                scheduleDOMapper.modifySchedule(item.getTuesday());
                scheduleDOMapper.modifySchedule(item.getWednesday());
                scheduleDOMapper.modifySchedule(item.getThursday());
                scheduleDOMapper.modifySchedule(item.getFriday());
            }
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.SCHEDULE_MODIFY_FAILED);
        }
    }

    /**
     * @author Ck
     * 根据id查询课程计划表
     */
    @Override
    public AdminScheduleVO getScheduleById(Integer id) {
        return scheduleDOMapper.selectById(id);
    }

    /**
     * @author Ck
     * 设置每行每日的子对象
     */
    private AdminScheduleListVO getAdminScheduleList(AdminScheduleVO scheduleVO) {
        AdminScheduleListVO item = new AdminScheduleListVO(scheduleVO);
        Integer activityId = scheduleVO.getActivityId();
        if(scheduleVO.getType()==null){
            return item;
        }
        switch (scheduleVO.getType()) {
            //假日
            case Constants.SCHEDULE_TYPE_HOLIDAY: {
                break;
            }
            //固定课程
            case Constants.SCHEDULE_TYPE_FIXTURE: {
                AdminFixedCourseVO fixtureVO = fixedCourseDOMapper.selectFixtureById(activityId);
                item.setFixedCourse(fixtureVO);
                break;
            }
            //普通课程
            case Constants.SCHEDULE_TYPE_COURSE: {
                AdminChapterAndCourseVO courseVO = chapterDOMapper.selectChapterAndCourseByChapterId(activityId);
                item.setCourse(courseVO);
                break;
            }
            //活动
            case Constants.SCHEDULE_TYPE_ACTIVITY: {
                AdminActivityVO activityVO = activityDOMapper.selectActivityById(activityId);
                item.setActivity(activityVO);
                break;
            }
        }
        return item;
    }
}
