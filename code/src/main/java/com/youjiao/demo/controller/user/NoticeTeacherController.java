package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.NoticeTeacherVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.NoticeTeacherService;
import com.youjiao.demo.util.MyLog;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youjiao.demo.validator.MyValidation.checkIntNull;
import static com.youjiao.demo.validator.MyValidation.checkStrNull;

/**
 * @author CaiMJ
 * #date 2019/03/13 19:38
 */
@RestController("noticeTeacher")
@RequestMapping("/noticeTeacher")
@ComponentScan(basePackages = {"com.youjiao.demo.service.menuService"})
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class NoticeTeacherController extends BaseController {

    private final NoticeTeacherService noticeTeacherService;

    public NoticeTeacherController(NoticeTeacherService noticeTeacherService) {
        this.noticeTeacherService = noticeTeacherService;
    }

    /**
     * @author CaiMJ
     * 通过前端发送的classId,title,content 添加新公告
     */
    @PostMapping(value = "addNotice")
    @ResponseBody
    public CommonReturnType addNotice(
            @RequestParam(name = "classId") Integer classId,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content) throws BusinessException {
        //班级id是否合法
        checkIntNull(classId);
        //标题内容是否合法
        checkStrNull(title, content);
        //根据前端传来的classId,title,content进行操作
        noticeTeacherService.addNotice(classId, title, content);

        return CommonReturnType.create(null);
    }

    /**
     * @author CaiMJ
     * 通过前端发送的noticeId,title,content 修改公告
     */
    @PostMapping(value = "updateNotice", consumes = {CONTENT_TYPE_APPLICATION})
    @ResponseBody
    public CommonReturnType updateNotice(
            @RequestParam(name = "noticeId") Integer noticeId,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content) throws BusinessException {
        //公告id是否非法
        checkIntNull(noticeId);
        //标题内容是否非法
        checkStrNull(title, content);
        //根据前端传来的noticeId,title,content在notice(公告)表中进行操作
        noticeTeacherService.updateNotice(noticeId, title, content);
        return CommonReturnType.create(null);
    }

    /**
     * @author WengWenxin
     * 通过班级id获取公告列表
     */
    @PostMapping(value = "getNotice")
    @ResponseBody
    public CommonReturnType getNotice(@RequestParam("classId") Integer classId) throws BusinessException {
        MyLog.info("Request : /api/noticeTeacher/getNotice\t[classId:" + classId + "]");
        //参数classId检验
        checkIntNull(classId);
        //获取公告列表
        List<NoticeTeacherVO> noticeList = noticeTeacherService.getNoticeList(classId);
        return CommonReturnType.create(noticeList);
    }
}
