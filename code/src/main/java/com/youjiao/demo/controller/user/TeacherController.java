package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.ParentListVO;
import com.youjiao.demo.controller.viewobject.user.TeacherListVO;
import com.youjiao.demo.controller.viewobject.user.TeacherVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.ParentService;
import com.youjiao.demo.service.user.TeacherService;
import com.youjiao.demo.util.MyLog;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youjiao.demo.validator.MyValidation.*;

/**
 * @author Kny
 * 2019/03/04 11:01
 */
@RestController("teacherListVO")
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    private final TeacherService teacherService;
    private final ParentService parentService;

    public TeacherController(TeacherService teacherService, ParentService parentService) {
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
        MyLog.info("Request : /api/teacher/get-teacher-list\t[classId:" + classId + "]");

        checkIntNull(classId);
        //将教师信息存入teacherList
        List<TeacherListVO> teacherList = teacherService.getTeacherList(classId);
        Integer leaderId=teacherService.getHeadTeacher(classId);
        for(int i=0;i<teacherList.size();i++){
            if(leaderId==teacherList.get(i).getTeacherId()){
                teacherList.get(i).setLeader(true);
                break;
            }
        }
        return CommonReturnType.create(teacherList);
    }

    /**
     * @author Kny
     * 通过classId获取父母列表信息
     */
    @ResponseBody
    @PostMapping(value = "/getParentList")
    public CommonReturnType getParentList(@RequestParam("classId") Integer classId) throws BusinessException {
        MyLog.info("Request : /api/teacher/get-parent-list\t[classId:" + classId + "]");

        checkIntNull(classId);
        //将父母信息存入parentList
        List<ParentListVO> parentList = parentService.getParentList(classId);

        return CommonReturnType.create(parentList);
    }

    /**
     * @author Lyy
     * 显示教师的个人信息页
     */
    @GetMapping(value = "/getTeacherInfo")
    @ResponseBody
    public CommonReturnType getTeacherInfo() throws BusinessException {
        TeacherVO teacherVO = teacherService.showTeacherInfo();

        checkObjectNull(teacherVO);

        return CommonReturnType.create(teacherVO);
    }

    /**
     * @author Lyy
     * 修改教师个人信息页
     */
    @PostMapping(value = "/updateTeacherInfo", consumes = {CONTENT_TYPE_APPLICATION})
    @ResponseBody
    public CommonReturnType updateTeacherInfo(@RequestParam(name = "avatar") String avatar,
                                              @RequestParam(name = "telephone") String telephone
    ) throws BusinessException {
        // 校验用户数据
        checkStrNull(avatar, telephone);
        checkTelephone(telephone);
        teacherService.checkEditInfo(telephone, avatar);
        // 修改成功
        return CommonReturnType.create(null);
    }
}
