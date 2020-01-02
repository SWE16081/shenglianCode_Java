package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminStudentService;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.youjiao.demo.validator.MyValidation.*;

/**
 * @author Lyy
 * #date 2019/03/24 15:03
 */
@Controller("adminStudent")
@RequestMapping("/adminStudent")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class AdminStudentController extends BaseController {

    private final AdminStudentService adminStudentService;

    @Autowired
    public AdminStudentController(AdminStudentService adminStudentService) {
        this.adminStudentService = adminStudentService;
    }

    /**
     * @author Lyy
     * 获取该机构名下的班级列表
     */
    @GetMapping(value = "/getStudentClassList")
    @ResponseBody
    public CommonReturnType getStudentClassList() throws BusinessException {
        List<AdminStudentClassListVO> adminStudentClassListVOS = adminStudentService.getSchoolClassInfoList();
        return CommonReturnType.create(adminStudentClassListVOS);
    }

    /**
     * @author Lyy
     * 添加孩子基本信息
     */
    @PostMapping(value = "/addStudentInfo")
    @ResponseBody
    public CommonReturnType addStudentInfo(@Valid @RequestBody AdminStudentAddVO adminStudentAddVO, BindingResult result)
            throws BusinessException {
        if (result.hasErrors()) {
            throw new BusinessException(EmBusinessErr.CHILD_ADD_FAILED);
        }
        //入参检验
        checkIdNumber(adminStudentAddVO.getIdNumber());
        //checkTelephone(adminStudentAddVO.getTelephone());
        //调用函数添加数据
        adminStudentService.addStudentInfo(adminStudentAddVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Lyy
     * 孩子信息修改
     */
    @PostMapping(value = "/updateStudentInfo")
    @ResponseBody
    public CommonReturnType updateStudentInfo(@Valid @RequestBody AdminStudentModifyVO adminStudentModifyVO, BindingResult result)
            throws BusinessException {
        if (result.hasErrors()) {
            throw new BusinessException(EmBusinessErr.UPDATE_CHILD_FAILED);
        }
        //入参检验
        checkIdNumber(adminStudentModifyVO.getIdNumber());
        //checkTelephone(adminStudentModifyVO.getTelephone());
        adminStudentService.updateStudentInfo(adminStudentModifyVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Lyy
     * 家长信息修改
     */
    @PostMapping(value = "/updateParentInfo")
    @ResponseBody
    public CommonReturnType updateParentInfo(@Valid @RequestBody AdminParentModifyVO adminParentModifyVO, BindingResult result)
            throws BusinessException {
        if (result.hasErrors()) {
            throw new BusinessException(EmBusinessErr.UPDATE_PARENT_FAILED);
        }
        //入参检验
        checkTelephone(adminParentModifyVO.getTelephone());
        adminStudentService.updateParentInfo(adminParentModifyVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Lyy
     * 根据学生id列表删除对应学生信息
     */
    @PostMapping(value = "/deleteStudentInfoList")
    @ResponseBody
    public CommonReturnType deleteStudentInfoList(@Valid @RequestBody List<Integer> studentIdList, BindingResult result)
            throws BusinessException {
        //入参检验
        MyValidation.checkObjectNull(studentIdList);
        MyValidation.validateObject(EmBusinessErr.CHILD_DELETE_FAILED, result);
        adminStudentService.deleteStudentInfoList(studentIdList);
        return CommonReturnType.create(null);
    }

    /**
     * @author Lyy
     * 获得孩子信息列表
     */
    @GetMapping(value = "/getStudentInfoList")
    @ResponseBody
    public CommonReturnType getStudentInfoList(@RequestParam(name = "classId") Integer classId) throws BusinessException {
        List<AdminStudentInfoVO> adminStudentInfoVOList = adminStudentService.getStudentInfoList(classId);
        return CommonReturnType.create(adminStudentInfoVOList);
    }

    /**
     * @author Lyy
     * 根据电话号码重置密码
     */
    @GetMapping(value = "/resetParentPassword")
    @ResponseBody
    public CommonReturnType resetParentPassword(@RequestParam(name = "telephone") String telephone) throws BusinessException {
        //入参检验
        checkTelephone(telephone);
        adminStudentService.resetParentPassword(telephone);
        return CommonReturnType.create(null);
    }
}
