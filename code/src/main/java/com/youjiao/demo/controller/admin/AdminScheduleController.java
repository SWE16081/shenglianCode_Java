package com.youjiao.demo.controller.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminScheduleService;
import com.youjiao.demo.service.admin.AdminTermRecordService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/16 15:58
 */
@RequestMapping("/admin/schedule")
@RestController("adminSchedule")
public class AdminScheduleController extends BaseController {
    private final AdminScheduleService scheduleService;
    private final AdminTermRecordService termRecordService;

    public AdminScheduleController(AdminScheduleService scheduleService, AdminTermRecordService termRecordService) {
        this.scheduleService = scheduleService;
        this.termRecordService = termRecordService;
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
                                                @RequestParam(name = "termId")
                                                        Integer termId, HttpServletRequest request) throws BusinessException {
        MyValidation.checkIntNull(termId);
        MyLog.info("Request : /api/admin/schedule/getScheduleWeekList\t[date:" + date + " termId:" + termId + "]");

        //test
        //request.getSession().setAttribute(Constants.JURISDICTION_SESSION_ATTRIBUTE, Constants.ADMIN_JURISDICTION_TOP);

        Byte jurisdiction = (Byte) request.getSession().getAttribute(Constants.JURISDICTION_SESSION);
        MyValidation.checkObjectNull(jurisdiction);
        List<AdminScheduleRowVO> list;
        //日期访问权限检查
        if (checkJurisdiction(jurisdiction, date, termId)) {
            list = scheduleService.getScheduleWeekList(date, termId);
        } else {
            throw new BusinessException(EmBusinessErr.SCHEDULE_JURISDICTION_FAILED);
        }

        return CommonReturnType.create(list);
    }

    /**
     * @author Ck
     * 修改一周的课程计划表
     * 只允许修改type和activity_id字段, 不允许修改日期和学期, 如要修改请手动删除再添加, 修改成本大
     */
    @PostMapping("/modifyScheduleWeek")
    @ResponseBody
    public CommonReturnType modifyScheduleWeek(@Valid @RequestBody AdminSchedulePostVO adminSchedulePost, BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.SCHEDULE_MODIFY_FAILED, result);
        Integer oldId = adminSchedulePost.getScheduleForm().get(0).getMonday().getScheduleId();
        //从DB获取原计划表日期并检查
        Date oldDate = scheduleService.getScheduleById(oldId).getSchooltime();
        if (oldDate == null || checkTimeAfterTheWeek(oldDate)) {
            throw new BusinessException(EmBusinessErr.SCHEDULE_MODIFY_FAILED_MODIFY_TIME_PAST);
        }

        MyLog.info("Request : /api/admin/schedule/modifyScheduleWeek");
        scheduleService.modifyScheduleWeek(adminSchedulePost);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 删除一周的课程计划表
     */
    @PostMapping(value = "/deleteScheduleWeek", consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public CommonReturnType deleteScheduleWeek(@RequestParam Date date,
                                               @RequestParam Integer termId) throws BusinessException {
        MyValidation.checkIntNull(termId);
        //日期检查
        if (date == null || checkTimeAfterTheWeek(date)) {
            throw new BusinessException(EmBusinessErr.SCHEDULE_DELETE_FAILED_TIME_PAST);
        }

        MyLog.info("Request : /api/admin/schedule/deleteScheduleWeek\t[ date : " + date + ", termId : " + termId + "]");
        scheduleService.deleteScheduleWeek(date, termId);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 添加一周的课程计划表
     */
    @PostMapping("/addScheduleWeek")
    @ResponseBody
    public CommonReturnType addScheduleWeek(@Valid @RequestBody AdminSchedulePostVO adminSchedulePost, BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.SCHEDULE_ADD_FAILED, result);
        //日期检查
        Date date = adminSchedulePost.getScheduleForm().get(0).getMonday().getSchooltime();
        if (checkTimeAfterTheWeek(date)) {
            throw new BusinessException(EmBusinessErr.SCHEDULE_ADD_FAILED_TIME_PAST);
        }
        MyLog.info("Request : /api/admin/schedule/addScheduleWeek");
        scheduleService.addScheduleWeek(adminSchedulePost, date);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 检查修改日期是否在这周之后（当前周不允许修改）
     * 与7天之后的所在周周日时间相比
     */
    private boolean checkTimeAfterTheWeek(Date date) {
        if (date == null) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.add(Calendar.DATE, 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return !date.after(cal.getTime());
    }

    /**
     * @author Ck
     * 根据日期或学期id检查课程计划表的查询权限
     */
    private boolean checkJurisdiction(Byte jurisdiction, Date date, Integer termId) {
        if (jurisdiction == null || jurisdiction < 0) {
            return false;
        }
        //定义一个月的时间是三周 3*7
        final int SCHEDULE_TIME_MONTH = 3 * 7;
        //学年查询的分隔点 在此月份之后分支园长可以查询新一学年的课程计划表
        final int SCHEDULE_TIME_TERM_NUM_EXPLODE_ON_MONTH = 8;


        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        if (date == null || date.getTime() <= cal.getTimeInMillis()) {
            //System.out.println("查看本周或以前的记录");
            return true;
        }

        cal.setTimeInMillis(System.currentTimeMillis());
        switch (jurisdiction) {
            //超级管理员查看所有记录
            case Constants.ADMIN_JURISDICTION_TOP: {
                //System.out.println("超级管理员查看所有记录");
                return true;
            }
            //普通管理员一个月
            default: {
                //System.out.print("普通管理员和教师看一个月 ");
                cal.add(Calendar.DATE, SCHEDULE_TIME_MONTH);
                cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                if (date.getTime() <= cal.getTimeInMillis()) {
                    //System.out.println("通过");
                    return true;
                } else {
                    //System.out.println("不通过");
                    return false;
                }
            }
        }
    }
}
