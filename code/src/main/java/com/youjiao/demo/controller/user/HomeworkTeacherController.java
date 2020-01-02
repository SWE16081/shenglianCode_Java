package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.*;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.HomeworkTeacherService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.util.MySessionUtil;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ck
 * #date 2019/03/12 23:05
 * 作业相关Controller
 */
@Controller("homeworkTeacher")
@RequestMapping("/homework")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class HomeworkTeacherController extends BaseController {
    private final HomeworkTeacherService homeworkService;

    public HomeworkTeacherController(HomeworkTeacherService homeworkService) {
        this.homeworkService = homeworkService;
    }

    /**
     * @author Ck
     * 根据班级id获取教师端的作业列表
     */
    @GetMapping(value = "/getTeacherHomeworkList")
    @ResponseBody
    public CommonReturnType getTeacherHomeworkList(@RequestParam("classId") Integer classId) throws BusinessException {
        //test
        //MySessionUtil.setAttribute(Constants.TEACHER_ID_SESSION,1);

        //session中获取教师id
        Integer teacherId = (Integer) MySessionUtil.getAttribute(Constants.TEACHER_ID_SESSION);
        //参数检查
        MyValidation.checkIntNull(classId, teacherId);

        MyLog.info("Request : /api/homework/getTeacherHomeworkList\t[teacherId : " + teacherId + ", classId : " + classId + " ]");

        //获取教师端作业列表
        List<HomeworkInfoListVO> list = homeworkService.getHomeworkListByClassIdAndTeacherId(classId, teacherId);
        return CommonReturnType.create(list);
    }

    /**
     * @author Ck
     * 根据作业id获取已经提交作业的学生作业列表
     */
    @GetMapping(value = "/getTeacherCommittedList")
    @ResponseBody
    public CommonReturnType getTeacherCommittedList(@RequestParam("homeworkId") Integer homeworkId) throws BusinessException {
        //参数检查
        MyValidation.checkIntNull(homeworkId);

        MyLog.info("Request : /api/homework/getTeacherCommittedList\t[homeworkId : " + homeworkId + " ]");

        //获取教师端已经提交作业的学生作业列表
        List<HomeworkCommitListVO> list = homeworkService.getCommittedHomeworkListByHomeworkId(homeworkId);
        return CommonReturnType.create(list);
    }

    /**
     * @author Ck
     * 根据作业id获取还没有提交作业的学生名单列表
     */
    @GetMapping(value = "/getTeacherUncommittedList")
    @ResponseBody
    public CommonReturnType getTeacherUncommittedList(@RequestParam("homeworkId") Integer homeworkId) throws BusinessException {
        //参数检查
        MyValidation.checkIntNull(homeworkId);

        MyLog.info("Request : /api/homework/getTeacherUncommittedList\t[homeworkId : " + homeworkId + " ]");

        //获取未提交作业的学生名单列表
        List<HomeworkUncommitListVO> list = homeworkService.getUncommittedHomeworkListByHomeworkId(homeworkId);
        return CommonReturnType.create(list);
    }

    /**
     * @author Ck
     * 根据文件id获取提交的作业的文件信息
     */
    @GetMapping(value = "/getHomeworkInfo")
    @ResponseBody
    public CommonReturnType getHomeworkInfo(@RequestParam("fileId") Integer fileId) throws BusinessException {
        //参数检查
        MyValidation.checkIntNull(fileId);

        MyLog.info("Request : /api/homework/getHomeworkInfo\t[fileId : " + fileId + " ]");

        //获取提交的作业文件信息
        HomeworkFileVO vo = homeworkService.getHomeworkFileByFileId(fileId);
        return CommonReturnType.create(vo);
    }

    /**
     * @author Kny
     * 通过homeworkVO获取作业信息
     */
    @ResponseBody
    @PostMapping(value = "/addHomework")
    public CommonReturnType addHomework(@Valid HomeworkAddVO homeworkAddVO, BindingResult result) throws BusinessException {
        MyLog.info("Request : /api/teacher/homework/addHomework\t[HomeworkAddVO : " + homeworkAddVO + "]");

        //判断VO里各参数是否为空，其中，finishTime必须为将来日期
        MyValidation.validateObject(EmBusinessErr.NOT_FUTURE_DATE, result);

        homeworkService.addHomeworkFromTeacher(homeworkAddVO);

        return CommonReturnType.create(null);
    }

    /**
     * @author Kny
     * 通过addHomeworkVO获取修改后的作业信息
     */
    @ResponseBody
    @PostMapping(value = "/modifyHomework")
    public CommonReturnType modifyHomework(@Valid HomeworkModifyVO homeworkModifyVO, BindingResult result) throws BusinessException {
        MyLog.info("Request : /api/homework/modifyHomework\t[homeworkModifyVO : " + homeworkModifyVO + "]");

        //判断VO里各参数是否为空，其中，finishTime必须为将来日期
        MyValidation.validateObject(EmBusinessErr.NOT_FUTURE_DATE, result);

        homeworkService.modifyHomeworkFromTeacher(homeworkModifyVO);

        return CommonReturnType.create(null);
    }
}
