package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.dataobject.TermRecordDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminTermRecordService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 20:59
 */
@RequestMapping("/admin/termRecord")
@RestController("adminTermRecord")
public class AdminTermRecordController extends BaseController {
    private final AdminTermRecordService termRecordService;

    public AdminTermRecordController(AdminTermRecordService termRecordService) {
        this.termRecordService = termRecordService;
    }

    /**
     * @author Ck
     * 添加学期记录
     */
    @PostMapping("/addTermRecord")
    @ResponseBody
    public CommonReturnType addTermRecord(@Valid @RequestBody TermRecordDO termRecordDO, BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.TERM_RECORD_ADD_FAILED, result);
        MyLog.info("Request : /api/admin/termRecord/addTermRecord\t[" + termRecordDO.toString() + "]");
        termRecordService.addTermRecord(termRecordDO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 修改学期记录
     */
    @PostMapping("/modifyTermRecord")
    @ResponseBody
    public CommonReturnType modifyTermRecord(@Valid @RequestBody TermRecordDO termRecordDO, BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.TERM_RECORD_MODIFY_FAILED, result);
        MyLog.info("Request : /api/admin/termRecord/modifyTermRecord\t[" + termRecordDO.toString() + "]");
        termRecordService.modifyTermRecord(termRecordDO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 获取所有学期记录
     */
    @GetMapping("/getTermRecordList")
    @ResponseBody
    public CommonReturnType getTermRecord(HttpServletRequest httpServletRequest) {
        List<TermRecordDO> list;
        list = termRecordService.getTermRecordAll((Byte) httpServletRequest.getSession().getAttribute(Constants.JURISDICTION_SESSION));
        MyLog.info("Request : /api/admin/termRecord/getTermRecordList");
        return CommonReturnType.create(list);
    }


}
