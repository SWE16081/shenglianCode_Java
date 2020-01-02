package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.AdminFixedCourseVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminFixedCourseService;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 18:11
 */
@RequestMapping("/admin/fixedCourse")
@RestController("adminFixedCourse")
public class AdminFixedCourseController extends BaseController {
    private final AdminFixedCourseService fixedCourseService;

    public AdminFixedCourseController(AdminFixedCourseService fixedCourseService) {
        this.fixedCourseService = fixedCourseService;
    }

    /**
     * @author Ck
     * 增加固定课程
     */
    @PostMapping("/addFixedCourse")
    @ResponseBody
    public CommonReturnType addFixedCourse(@RequestParam String name) throws BusinessException {
        MyValidation.checkStrNull(name);
        MyLog.info("Request : /api/admin/fixedCourse/addFixedCourse\t[ name: " + name + "]");
        fixedCourseService.addFixedCourse(name);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 修改固定课程
     */
    @PostMapping("/modifyFixedCourse")
    @ResponseBody
    public CommonReturnType modifyFixedCourse(@Valid @RequestBody AdminFixedCourseVO fixedCourseVO, BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.ACTIVITY_MODIFY_FAILED, result);
        MyLog.info("Request : /api/admin/fixedCourse/modifyFixedCourse\t[" + fixedCourseVO.toString() + "]");
        fixedCourseService.modifyFixedCourse(fixedCourseVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 查询所有固定课程
     */
    @GetMapping("getFixedCourseList")
    @ResponseBody
    public CommonReturnType getFixedCourseList() {
        List<AdminFixedCourseVO> list;

        MyLog.info("Request : /api/admin/fixedCourse/getFixedCourseList");

        list = fixedCourseService.getFixedCourseList();
        return CommonReturnType.create(list);
    }
}
