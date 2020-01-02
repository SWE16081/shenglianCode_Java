package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminTransactionRecordService;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.youjiao.demo.validator.MyValidation.checkIntNull;
import static com.youjiao.demo.validator.MyValidation.checkObjectNull;

/**
 * @author Lyy
 * #date 2019/04/14 19:56
 */
@Controller("adminTransactionRecord")
@RequestMapping("/adminTransactionRecord")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class AdminTransactionRecordController extends BaseController {
    private final AdminTransactionRecordService adminTransactionRecordService;

    @Autowired
    public AdminTransactionRecordController(AdminTransactionRecordService adminTransactionRecordService) {
        this.adminTransactionRecordService = adminTransactionRecordService;
    }

    /**
     * @author Lyy
     * 根据前端的机构id获取除该机构id对应的机构以外的所有机构列表
     * 仅包括机构id和机构名称
     */
    @GetMapping(value = "/getSchoolList")
    @ResponseBody
    public CommonReturnType getSchoolList() throws BusinessException {
        List<AdminTransactionRecordSchoolListVO> adminTransactionRecordSchoolListVOS = adminTransactionRecordService.getSchoolList();
        return CommonReturnType.create(adminTransactionRecordSchoolListVOS);
    }

    /**
     * @author Lyy
     * 根据前端的机构id获取该机构名下的所有班级列表
     * 仅包括班级名称和id
     */
    @GetMapping(value = "/getClassList")
    @ResponseBody
    public CommonReturnType getClassList(@RequestParam(name = "schoolId") Integer schoolId) throws BusinessException {
        //入参检验
        checkIntNull(schoolId);
        List<AdminStudentClassListVO> adminTransactionRecordClassListVOS = adminTransactionRecordService.getClassList(schoolId);
        return CommonReturnType.create(adminTransactionRecordClassListVOS);
    }

    /**
     * @author Lyy
     * 根据前端的班级id获取该机构名下的所有学生列表
     * 仅包括学生名字和id
     */
    @GetMapping(value = "/getStudentList")
    @ResponseBody
    public CommonReturnType getStudentList(@RequestParam(name = "classId") Integer classId) throws BusinessException {
        //入参检验
        checkIntNull(classId);
        List<AdminTransactionRecordStudentListVO> adminTransactionRecordStudentListVOS = adminTransactionRecordService.getStudentList(classId);
        return CommonReturnType.create(adminTransactionRecordStudentListVOS);
    }

    /**
     * @author Lyy
     * 根据学生id获取该学生的所有交易记录列表
     */
    @GetMapping(value = "/getTransactionRecordList")
    @ResponseBody
    public CommonReturnType getTransactionRecordList(@RequestParam(name = "studentId") Integer studentId) throws BusinessException {
        //入参检验
        checkIntNull(studentId);
        List<AdminTransactionRecordListVO> adminTransactionRecordListVOS = adminTransactionRecordService.getTransactionRecordList(studentId);
        return CommonReturnType.create(adminTransactionRecordListVOS);
    }

    /**
     * @author Lyy
     * 根据前端提交的开始时间和截至时间和当前的班级id获取在时间范围内的所有该班级学生的交易记录列表
     */
    @PostMapping(value = "/selectTransactionRecordList")
    @ResponseBody
    public CommonReturnType selectTransactionRecordList(@Valid @RequestBody AdminTransactionRecordSelectVO adminTransactionRecordSelectVO) throws BusinessException {
        List<AdminTransactionRecordAllVO> adminTransactionRecordListVOS = adminTransactionRecordService.selectTransactionRecordList(adminTransactionRecordSelectVO);
        return CommonReturnType.create(adminTransactionRecordListVOS);
    }

    /**
     * @author Lyy
     * 根据前端提交的当前的班级id获取所有该班级学生的交易记录列表
     */
    @PostMapping(value = "/selectAllTransactionRecordList")
    @ResponseBody
    public CommonReturnType selectAllTransactionRecordList(@RequestParam(name = "classId") Integer classId) throws BusinessException {
        List<AdminTransactionRecordAllVO> adminTransactionRecordListVOS = adminTransactionRecordService.selectAllTransactionRecordList(classId);
        return CommonReturnType.create(adminTransactionRecordListVOS);
    }

    /**
     * @author Lyy
     * 根据前端提交的托管学生交易记录数据添加一条记录到对应的机构id中
     */
    @PostMapping(value = "/addTransactionRecord")
    @ResponseBody
    public CommonReturnType addTransactionRecord(@Valid @RequestBody AdminTransactionRecordAddVO adminTransactionRecordListVO) throws BusinessException {
        adminTransactionRecordService.addTransactionRecord(adminTransactionRecordListVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Lyy
     * 根据前端提交的托管学生交易记录数据及序号修改对应的托管学生交易记录
     */
    @PostMapping(value = "/updateTransactionRecord")
    @ResponseBody
    public CommonReturnType updateTransactionRecord(@Valid @RequestBody AdminTransactionRecordListVO adminTransactionRecordListVO) throws BusinessException {
        adminTransactionRecordService.updateTransactionRecord(adminTransactionRecordListVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Lyy
     * 根据前端提交的序号List删除对应的托管学生交易记录
     */
    @PostMapping(value = "/deleteTransactionRecord")
    @ResponseBody
    public CommonReturnType deleteTransactionRecord(@Valid @RequestBody List<Integer> recordIdList, BindingResult result) throws BusinessException {
        //入参检验
        checkObjectNull(recordIdList);
        System.out.println(1);
        if (result.hasErrors()) {
            throw new BusinessException(EmBusinessErr.DISHES_COMMIT_FAILED);
        }
        adminTransactionRecordService.deleteTransactionRecord(recordIdList);
        return CommonReturnType.create(null);
    }

    /**
     * @author Lyy
     * 根据总园长的机构id查询总的交易记录
     */
    @GetMapping(value = "/getAllTransactionRecord")
    @ResponseBody
    public CommonReturnType getAllTransactionRecord() throws BusinessException {
        return CommonReturnType.create(adminTransactionRecordService.getAllTransactionRecord());
    }

}
