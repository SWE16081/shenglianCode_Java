package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.ClassListVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.ClassService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Ck
 * #date 2019-03-02 14:23
 * 班级相关Controller
 */
@RequestMapping("/class")
@RestController("classListVO")
public class ClassController extends BaseController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    /**
     * @author Ck
     * 根据int teacherId返回对应的正常班级列表
     */
    @GetMapping(value = "/getClassList")
    public CommonReturnType getClassList(HttpServletRequest httpServletRequest) throws BusinessException {
        Integer teacherId = (Integer) httpServletRequest.getSession().getAttribute(Constants.TEACHER_ID_SESSION);
        //函数中检查teacherId合法性并决定是否抛出异常
        checkTeacherId(teacherId, false);

        //System.out.println("Request : /api/class/getClassList\t[teacherId:" + teacherId + "]");
        MyLog.info("Request : /api/class/getClassList\t[teacherId:" + teacherId + "]");

        List<ClassListVO> list = classService.getClassListByTeacherIdAndIsArchive(teacherId, false);

        return CommonReturnType.create(list);
    }

    /**
     * @author Ck
     * 根据int teacherId返回对应的归档班级列表
     */
    @GetMapping(value = "/getHistoryClassList")
    public CommonReturnType getHistoryClassList(HttpServletRequest httpServletRequest) throws BusinessException {
        Integer teacherId = (Integer) httpServletRequest.getSession().getAttribute(Constants.TEACHER_ID_SESSION);

        //函数中检查teacherId合法性并决定是否抛出异常
        checkTeacherId(teacherId, true);

        //System.out.println("Request : /api/class/getHistoryClassList\t[teacherId:" + teacherId + "]");
        MyLog.info("Request : /api/class/getHistoryClassList\t[teacherId:" + teacherId + "]");

        List<ClassListVO> list = classService.getClassListByTeacherIdAndIsArchive(teacherId, true);

        return CommonReturnType.create(list);
    }


    private void checkTeacherId(Integer teacherId, boolean isArchive) throws BusinessException {
        if (teacherId == null || teacherId < 0) {
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
        }
    }
}
