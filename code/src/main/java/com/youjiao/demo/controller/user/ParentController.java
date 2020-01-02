package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.ParentVO;
import com.youjiao.demo.controller.viewobject.user.TeacherListVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.ParentService;
import com.youjiao.demo.service.user.TeacherService;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.youjiao.demo.validator.MyValidation.*;

/**
 * @author Kny
 * 2019/03/04 10:37
 */
@Controller("parent")
@RequestMapping("/parent")
public class ParentController extends BaseController {

    private final ParentService parentService;

    private final TeacherService teacherService;

    @Autowired
    public ParentController(TeacherService teacherService, ParentService parentService) {
        this.teacherService = teacherService;
        this.parentService = parentService;
    }

    /**
     * @author Kny
     * 通过classId获取教师列表并返回
     */
    @ResponseBody
    @PostMapping(value = "/getTeacherList")
    public CommonReturnType getTeacherList(@RequestParam("classId") Integer classId) throws BusinessException {
        MyLog.info("Request : /api/parent/get-teacher-list\t[classId:" + classId + "]");

        checkIntNull(classId);
        //将教师信息存入teacherList，班主任id存入headTeacher
        List<TeacherListVO> teacherList = teacherService.getTeacherList(classId);
        Integer headTeacher = teacherService.getHeadTeacher(classId);
        List<TeacherListVO> teacherList2=new ArrayList<>();
        for (TeacherListVO teacher : teacherList) {
            if (teacher.getTeacherId().equals(headTeacher)) {
                teacherList2.add(teacher);
                break;
            }
        }
        for (TeacherListVO teacher : teacherList) {
            if (teacher.getTeacherId() != headTeacher) {
                teacherList2.add(teacher);
            }
        }
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("teacherList", teacherList2);
        responseData.put("headTeacher", headTeacher);

        return CommonReturnType.create(responseData);
    }

    /**
     * @author Lyy
     * 显示家长的个人信息页
     */
    @GetMapping(value = "/getParentInfo")
    @ResponseBody
    public CommonReturnType getParentInfo() throws BusinessException {
        ParentVO parentVO = parentService.showParentInfo();
        checkObjectNull(parentVO);
        return CommonReturnType.create(parentVO);
    }

    /**
     * @author Lyy
     * 修改家长个人信息页
     */
    @PostMapping(value = "/updateParentInfo")
    @ResponseBody
    public CommonReturnType updateParentInfo(@RequestParam(name = "avatar") String avatar,
                                             @RequestParam(name = "telephone") String telephone
    ) throws BusinessException {
        // 校验用户数据
        checkStrNull(avatar, telephone);
        checkTelephone(telephone);
        parentService.checkEditInfo(telephone, avatar);
        // 修改成功
        return CommonReturnType.create(null);
    }
}
