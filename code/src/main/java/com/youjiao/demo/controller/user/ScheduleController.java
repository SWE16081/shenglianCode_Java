package com.youjiao.demo.controller.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.ScheduleRowVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.ScheduleService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.util.MySessionUtil;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * @author Ck
 * #date 2019/07/14 12:45
 */
@RequestMapping("/user/schedule")
@RestController("Schedule")
public class ScheduleController extends BaseController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * @author Ck
     * 查询一周的课程计划表
     */
    @GetMapping("/getScheduleWeekList")
    @ResponseBody
    public CommonReturnType getScheduleWeekList(@RequestParam(name = "date")
                                                @JsonFormat(pattern = "yyyy-MM-dd")
                                                        Date date,
                                                @RequestParam(name = "grade")
                                                        Byte grade) throws BusinessException {
        MyValidation.checkObjectNull(grade,date);
        MyLog.info("Request : /user/schedule/getScheduleWeekList\t[date:" + date + " grade:" + grade + "]");
        //检查请求权限，教师查询一个月、家长查询一周（周五开始可查询下周）
        checkJurisdiction(date);
        List<ScheduleRowVO> list = scheduleService.getScheduleWeekList(date, grade);

        return CommonReturnType.create(list);
    }

    private void checkJurisdiction(Date date) throws BusinessException {
        Byte jurisdiction = (Byte) MySessionUtil.getAttribute(Constants.JURISDICTION_SESSION);
        //如果不是教师也不是家长
        if (jurisdiction == null ||
                (!jurisdiction.equals(Constants.USER_JURISDICTION_PARENT) && !jurisdiction.equals(Constants.USER_JURISDICTION_TEACHER))) {
            throw new BusinessException(EmBusinessErr.PERMISSION_DENIED);

        }

        //定义一个月的时间是三周 3*7
        final int SCHEDULE_TIME_MONTH = 3 * 7;

        Calendar cal = Calendar.getInstance();

        //设置每周的第一天是周日
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        //是否可以查看下周 -> 周五/周六时为true，（当日期为周日时实际上是查看本周）
        boolean isNextWeek = (cal.get(Calendar.DAY_OF_WEEK) >= Calendar.FRIDAY);
        //将时间设为这周的周六（本周的最后一天）
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

        //教师拥有一个月的查询权限
        if (jurisdiction.equals(Constants.USER_JURISDICTION_TEACHER)) {
            cal.add(Calendar.DATE, SCHEDULE_TIME_MONTH);
//            System.out.println("teacher: "+cal.getTime());
        }
        //家长在周五周六可查看下周，其余查看本周（周起始日为周日）
        else {
            //如果可以查看下周
            if (isNextWeek) {
                cal.add(Calendar.DATE, 7);
//                System.out.println("parent: "+cal.getTime());
            }
        }
        if(!(date.getTime() <= cal.getTimeInMillis())){
            throw new BusinessException(EmBusinessErr.PERMISSION_DENIED);
        }

    }
}
