package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminClassService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.util.MySessionUtil;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ck
 * #date 2019/03/24 15:55
 */
@RequestMapping("/admin/class")
@RestController("adminClass")
public class AdminClassController extends BaseController {

    private final AdminClassService classService;

    public AdminClassController(AdminClassService classService) {
        this.classService = classService;
    }

    /**
     * @author Ck
     * 根据机构id 和 code 获取班级列表(0 全部、1 正常班级、2 归档班级)
     */
    @GetMapping("/getClassesList")
    @ResponseBody
    public CommonReturnType getClassesList(@RequestParam("code") Integer code) throws BusinessException {
        //test
        //MySessionUtil.setAttribute(Constants.SCHOOL_ID_SESSION, 1);
        //从session获取机构id号
        Integer schoolId = (Integer) MySessionUtil.getAttribute(Constants.SCHOOL_ID_SESSION);
        //检查参数字段
        MyValidation.checkIntNull(code, schoolId);

        MyLog.info("Request : /api/admin/class/getClassesList\t[ schoolId : " + schoolId + "\tcode: " + code + "]");

        List<AdminClassListVO> list = classService.getClassesList(code, schoolId);
        return CommonReturnType.create(list);
    }

    /**
     * @author Ck
     * 根据班级id 归档信息设置班级的归档状态
     */
    @PostMapping("/modifyClassArchive")
    @ResponseBody
    public CommonReturnType modifyClassArchive(@Valid @RequestBody AdminClassModifyArchiveVO classModifyArchiveVO) throws BusinessException {
        Integer classId = classModifyArchiveVO.getClassId();
        Boolean isArchive = classModifyArchiveVO.getIsArchive();

        MyValidation.checkIntNull(classId);
        MyValidation.checkObjectNull(isArchive);

        MyLog.info("Request : /api/admin/class/modifyClassArchive\t[ classId : " + classId + "\tisArchive : " + isArchive + "]");

        classService.modifyClassArchive(classId, !isArchive);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 根据前端传送的班级信息创建班级
     */
    @PostMapping("/addClass")
    @ResponseBody
    public CommonReturnType addClass(@Valid @RequestBody AdminClassAddVO classAddVO,
                                     BindingResult result) throws BusinessException {
        //test
        //MySessionUtil.setAttribute(Constants.SCHOOL_ID_SESSION, 1);
        Integer schoolId = (Integer) MySessionUtil.getAttribute(Constants.SCHOOL_ID_SESSION);
        MyValidation.checkIntNull(schoolId);
        MyValidation.validateObject(EmBusinessErr.CLASS_ADD_FAILED, result);
        MyLog.info("Request : /api/admin/class/addClass\t[ " + classAddVO + " ]");

        classService.addClass(classAddVO, schoolId);

        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 根据前端传送的信息修改班级信息
     */
    @PostMapping("/modifyClass")
    @ResponseBody
    public CommonReturnType modifyClass(@Valid @RequestBody AdminClassModifyVO classModifyVO,
                                        BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.CLASS_MODIFY_FAILED, result);
        MyLog.info("Request : /api/admin/class/modifyClass\t[ " + classModifyVO + " ]");

        classService.modifyClass(classModifyVO);

        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 根据前端传送的班级id删除班级
     */
    @PostMapping("/deleteClass")
    @ResponseBody
    public CommonReturnType deleteClass(@Valid @RequestBody List<Integer> classIdList,
                                        BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.CLASS_MODIFY_FAILED, result);
        for (Integer classId : classIdList) {
            MyValidation.checkIntNull(classId);
            MyLog.info("Request : /api/admin/class/deleteClass\t[ classId : " + classId + " ]");

            classService.deleteClass(classId);
        }
        return CommonReturnType.create(null);
    }

    /**
     * @author Kny
     * 根据机构id获取学生信息列表
     */
    @ResponseBody
    @GetMapping(value = "/getStudentList")
    public CommonReturnType getStudentList() throws BusinessException {
        //test
        //MySessionUtil.setAttribute(Constants.SCHOOL_ID_SESSION, 1);
        //从session获取机构id
        Integer schoolId = (Integer) MySessionUtil.getAttribute(Constants.SCHOOL_ID_SESSION);
        //机构id判空
        MyValidation.checkIntNull(schoolId);

        MyLog.info("Request : /class/getStudentList\t[schoolId : " + schoolId + " ]");

        //获取学生信息列表
        List<AdminClassStudentListVO> studentList = classService.getStudentList(schoolId);

        return CommonReturnType.create(studentList);
    }

    /**
     * @author Kny
     * 根据机构id获取教师信息列表
     */
    @ResponseBody
    @GetMapping(value = "/getTeacherList")
    public CommonReturnType getTeacherList() throws BusinessException {
        //test
        //MySessionUtil.setAttribute(Constants.SCHOOL_ID_SESSION, 1);
        //从session获取机构id
        Integer schoolId = (Integer) MySessionUtil.getAttribute(Constants.SCHOOL_ID_SESSION);
        //机构id判空
        MyValidation.checkIntNull(schoolId);

        MyLog.info("Request : /class/getTeacherList\t[schoolId : " + schoolId + " ]");

        //获取教师信息列表
        List<AdminClassTeacherListVO> teacherList = classService.getTeacherList(schoolId);

        return CommonReturnType.create(teacherList);
    }
}