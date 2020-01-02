package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.HomeworkParentVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.HomeworkParentService;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zjp
 * 2019/03/10
 * 家长端作业管理controller
 */
@Controller("homeworkParent")
@RequestMapping("/homeworkParent")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class HomeworkParentController extends BaseController {
    private final HomeworkParentService homeworkParentService;

    @Autowired
    public HomeworkParentController(HomeworkParentService homeworkParentService) {
        this.homeworkParentService = homeworkParentService;
    }

    /**
     * @author Zjp
     * 获得班级作业信息列表
     */
    @PostMapping(value = "getParentHomeworkList")
    @ResponseBody
    public CommonReturnType getParentHomeworkList(@RequestParam(name = "classId") Integer classId,
                                                  @RequestParam(name = "studentId") Integer studentId)
            throws BusinessException {
        //数据判空
        MyValidation.checkIntNull(classId, studentId);
        //获得班级作业信息列表
        List<HomeworkParentVO> voList = homeworkParentService.getParentHomeworkList(classId, studentId);

        return CommonReturnType.create(voList);
    }

    /**
     * @author Zjp
     * 提交电子版作业
     * fileId：作业提交记录的id
     * file：base64编码的数据
     */
    @PostMapping(value = "parentCommit")
    @ResponseBody
    public CommonReturnType parentCommitFile(@RequestParam(name = "homeworkId") Integer homeworkId,
                                             @RequestParam(name = "fileId") Integer fileId,
                                             @RequestParam(name = "file") String file)
            throws BusinessException {
        //数据判空
        MyValidation.checkIntNull(homeworkId, fileId);
        MyValidation.checkStrNull(file);

        //提交电子版作业
        homeworkParentService.parentCommitFile(homeworkId, fileId, file);

        return CommonReturnType.create(null);
    }

    /**
     * @author Zjp
     * 确认纸质版作业
     * fileId：作业提交记录的id
     */
    @PostMapping(value = "parentConfirm", consumes = {CONTENT_TYPE_APPLICATION})
    @ResponseBody
    public CommonReturnType parentConfirm(@RequestParam(name = "homeworkId") Integer homeworkId,
                                          @RequestParam(name = "fileId") Integer fileId)
            throws BusinessException {
        //数据判空
        MyValidation.checkIntNull(homeworkId, fileId);

        //确认纸质版作业
        homeworkParentService.parentConfirm(homeworkId, fileId);

        return CommonReturnType.create(null);
    }
}
