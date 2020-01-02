package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.AdminTeacherAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTeacherListVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTeacherModifyVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminTeacherService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Kny
 * 2019/04/13
 */
@Controller("adminTeacher")
@RequestMapping("/admin/teacher")
public class AdminTeacherController extends BaseController {

    private final HttpServletRequest httpServletRequest;
    private final AdminTeacherService adminTeacherService;

    public AdminTeacherController(AdminTeacherService adminTeacherService, HttpServletRequest httpServletRequest) {
        this.adminTeacherService = adminTeacherService;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * @author Kny
     * 根据前端传送的信息添加教师信息
     */
    @PostMapping("/addTeacher")
    @ResponseBody
    public CommonReturnType addTeacher(@Valid @RequestBody AdminTeacherAddVO adminTeacherAddVO,
                                       BindingResult result) throws BusinessException {
        //test
        //httpServletRequest.getSession().setAttribute(Constants.SCHOOL_ID_SESSION, 1);
        //从session获取机构id
        Integer schoolId = (Integer) httpServletRequest.getSession().getAttribute(Constants.SCHOOL_ID_SESSION);
        //机构id判空
        MyValidation.checkIntNull(schoolId);

        //判断VO里的值是否为空
        MyValidation.validateObject(EmBusinessErr.TEACHER_ADD_FAILED, result);
        //判断手机号是否合法
        MyValidation.checkTelephone(adminTeacherAddVO.getTelephone());
        //判断身份证号是否合法
        MyValidation.checkIdNumber(adminTeacherAddVO.getIdNumber());

        MyLog.info("Request : /api/admin/teacher/addTeacher\t[ " + adminTeacherAddVO + " ]");

        adminTeacherService.addTeacher(adminTeacherAddVO, schoolId);

        return CommonReturnType.create(null);
    }

    /**
     * @author Kny
     * 根据前端传送的信息修改教师信息
     */
    @PostMapping("/modifyTeacher")
    @ResponseBody
    public CommonReturnType modifyTeacher(@Valid @RequestBody AdminTeacherModifyVO adminTeacherModifyVO,
                                          BindingResult result) throws BusinessException {
        //判断VO里的值是否为空
        MyValidation.validateObject(EmBusinessErr.TEACHER_MODIFY_FAILED, result);
        //判断手机号是否合法
        MyValidation.checkTelephone(adminTeacherModifyVO.getTelephone());
        //判断身份证号是否合法
        MyValidation.checkIdNumber(adminTeacherModifyVO.getIdNumber());

        MyLog.info("Request : /api/admin/teacher/modifyTeacher\t[ " + adminTeacherModifyVO + " ]");

        adminTeacherService.modifyTeacher(adminTeacherModifyVO);

        return CommonReturnType.create(null);
    }

    /**
     * @author Kny
     * 根据前端传送的信息逻辑删除教师
     */
    @PostMapping("/deleteTeacher")
    @ResponseBody
    public CommonReturnType deleteTeacherList(@RequestBody List<Integer> teacherList) throws BusinessException {
        //判断teacherList是否为空
        MyValidation.checkObjectNull(teacherList);

        MyLog.info("Request : /api/admin/teacher/deleteTeacher\t[ " + teacherList + " ]");

        adminTeacherService.deleteTeacher(teacherList);

        return CommonReturnType.create(null);
    }

    /**
     * @author Kny
     * 根据机构id获取教师信息列表
     */
    @GetMapping("/getTeacherList")
    @ResponseBody
    public CommonReturnType getTeacherList() throws BusinessException {
        //test
        //httpServletRequest.getSession().setAttribute(Constants.SCHOOL_ID_SESSION, 1);
        //从session获取机构id
        Integer schoolId = (Integer) httpServletRequest.getSession().getAttribute(Constants.SCHOOL_ID_SESSION);
        //机构id判空
        MyValidation.checkIntNull(schoolId);

        MyLog.info("Request : /api/admin/teacher/getTeacherList");

        List<AdminTeacherListVO> teacherList = adminTeacherService.getTeacherList(schoolId);

        return CommonReturnType.create(teacherList);
    }


}
