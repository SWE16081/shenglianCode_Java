package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.AdminActivityVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminActivityService;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 14:02
 */
@RequestMapping("/admin/activity")
@RestController("adminActivity")
public class AdminActivityController extends BaseController {
    private final AdminActivityService activityService;

    public AdminActivityController(AdminActivityService activityService) {
        this.activityService = activityService;
    }

    /**
     * @author Ck
     * 添加活动
     */
    @PostMapping("/addActivity")
    @ResponseBody
    public CommonReturnType addActivity(@Valid @RequestBody AdminActivityVO activityVO,
                                        BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.ACTIVITY_ADD_FAILED, result);
        MyLog.info("Request : /api/admin/activity/addActivity\t[" + activityVO.toString() + "]");
        activityService.addActivity(activityVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 删除活动
     */
    @PostMapping("/deleteActivity")
    @ResponseBody
    public CommonReturnType deleteActivity(@Valid @RequestBody List<Integer> list,
                                           BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.ACTIVITY_DELETE_FAILED, result);

        for (Integer activityId : list) {
            MyValidation.checkIntNull(activityId);
            MyLog.info("Request : /api/admin/activity/deleteActivity\t[" + activityId + "]");

            activityService.logicalDeleteActivityById(activityId);
        }

        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 修改活动
     */
    @PostMapping("/modifyActivity")
    @ResponseBody
    public CommonReturnType modifyActivity(@Valid @RequestBody AdminActivityVO activityVO,
                                           BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.ACTIVITY_MODIFY_FAILED, result);
        MyLog.info("Request : /api/admin/activity/modifyActivity\t[" + activityVO.toString() + "]");

        activityService.modifyActivity(activityVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 查找所有活动
     */
    @GetMapping("getActivityList")
    @ResponseBody
    public CommonReturnType getActivityList() {
        List<AdminActivityVO> list;
        MyLog.info("Request : /api/admin/activity/getActivityList");
        list = activityService.getActivityList();
        return CommonReturnType.create(list);
    }
}
