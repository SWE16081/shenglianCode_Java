package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.controller.viewobject.user.ScheduleRowVO;
import com.youjiao.demo.dao.*;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.user.ScheduleService;
import com.youjiao.demo.util.Constants;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Ck
 * #date 2019/07/14 12:50
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleDOMapper scheduleDOMapper;
    private final ActivityDOMapper activityDOMapper;
    private final ChapterDOMapper chapterDOMapper;
    private final FixedCourseDOMapper fixedCourseDOMapper;
    private final TermRecordDOMapper termRecordDOMapper;

    public ScheduleServiceImpl(ScheduleDOMapper scheduleDOMapper, ActivityDOMapper activityDOMapper, ChapterDOMapper chapterDOMapper, FixedCourseDOMapper fixedCourseDOMapper, TermRecordDOMapper termRecordDOMapper) {
        this.scheduleDOMapper = scheduleDOMapper;
        this.activityDOMapper = activityDOMapper;
        this.chapterDOMapper = chapterDOMapper;
        this.fixedCourseDOMapper = fixedCourseDOMapper;
        this.termRecordDOMapper = termRecordDOMapper;
    }

    /**
     * @author Ck
     * 根据日期和学期id查询一周的课程计划表
     */
    @Override
    public List<ScheduleRowVO> getScheduleWeekList(Date date, Byte grade) throws BusinessException {

        //根据grade信息 计算并获取当前学期的学期号
        Integer termId = getNowTermIdByGrade(grade);

        //由行组成的list
        List<ScheduleRowVO> list = new ArrayList<>();
        //数据库中查询到的日期区间内的所有schedule, 按日期和节次排序
        List<AdminScheduleVO> scheduleList = scheduleDOMapper.selectFromDateOnWeek(date, termId);

        int index = 0;
        //按行添加
        ScheduleRowVO newRow = new ScheduleRowVO();
        for (AdminScheduleVO scheduleVO : scheduleList) {
            //模5，一周五天
            int modIndex = index % 5;
            /*
             * 根据模，通过getScheduleList函数根据scheduleVO中的信息填充具体的VO对象(课程，固定课程等详细信息）
             * 再放入row中对应的位置（周几）
             */
            newRow.setWeekArray(modIndex, getScheduleList(scheduleVO));
            if (modIndex == 4) {
                //当模为4 即周五时，将该行加入列表，并新建一行
                list.add(newRow);
                newRow = new ScheduleRowVO();
            }
            index++;
        }
        return list;
    }

    /**
     * @author Ck
     * 填充每行每日子对象具体的课程信息
     */
    private AdminScheduleListVO getScheduleList(AdminScheduleVO scheduleVO) {
        AdminScheduleListVO item = new AdminScheduleListVO(scheduleVO);
        Integer activityId = scheduleVO.getActivityId();
        if(scheduleVO.getType()==null){
            return item;
        }
        // switch居然不能处理null!  =.=
        switch (scheduleVO.getType()) {
            //普通课程
            case Constants.SCHEDULE_TYPE_COURSE: {
                AdminChapterAndCourseVO courseVO = chapterDOMapper.selectChapterAndCourseByChapterId(activityId);
                item.setCourse(courseVO);
                break;
            }
            //固定课程
            case Constants.SCHEDULE_TYPE_FIXTURE: {
                AdminFixedCourseVO fixtureVO = fixedCourseDOMapper.selectFixtureById(activityId);
                item.setFixedCourse(fixtureVO);
                break;
            }
            //活动
            case Constants.SCHEDULE_TYPE_ACTIVITY: {
                AdminActivityVO activityVO = activityDOMapper.selectActivityById(activityId);
                item.setActivity(activityVO);
                break;
            }
            //假日等
            default:{
                break;
            }
        }
        return item;
    }

    /**
     * @author Ck
     * 根据grade获取学期id
     */
    private Integer getNowTermIdByGrade(Byte grade) throws BusinessException {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH)+1;//加一后才是实际的月份
        int year = cal.get(Calendar.YEAR);
        byte termNum;
        int termYear;
        // [二月,七月]为第二学期
        if (month >= Constants.TERM_NUM_EXPLODE_ON_MONTH && month <= Constants.TERM_YEAR_EXPLODE_ON_MONTH) {
            termNum = 1;
            termYear = year - 1;
        } else {
            termNum = 0;
            termYear = year;
        }
        Integer termId = termRecordDOMapper.selectIdBySame(termYear,termNum,grade);
        if(termId == null){
            throw new BusinessException(EmBusinessErr.TERM_RECORD_NOT_EXISTED);
        }
        return termId;
    }
}
