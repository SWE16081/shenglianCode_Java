package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.NoticeParentVO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.NoticeParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.youjiao.demo.validator.MyValidation.checkIntNull;

/**
 * @author Lyy
 * #date 2019/03/15 9:57
 */
@Controller("noticeParent")
@RequestMapping("/noticeParent")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class NoticeParentController extends BaseController {
    private final NoticeParentService noticeParentService;

    @Autowired
    public NoticeParentController(NoticeParentService noticeParentService) {
        this.noticeParentService = noticeParentService;
    }

    /**
     * @author Lyy
     * 查找公告列表
     * 返回公告表的信息及该学生是否已读
     */
    @GetMapping(value = "/getNoticeList")
    @ResponseBody
    public CommonReturnType getNoticeList(
            @RequestParam(name = "classId") Integer classId,
            @RequestParam(name = "studentId") Integer studentId) throws BusinessException {
        //参数检验
        checkIntNull(classId, studentId);
        List<NoticeParentVO> noticeParentVOList = noticeParentService.getNoticeList(classId, studentId);
        return CommonReturnType.create(noticeParentVOList);
    }

    /**
     * @author Lyy
     * 前端自行获取对应公告信息，后端仅将该条记录从未读表中删除
     */
    @PostMapping(value = "/readNotice")
    @ResponseBody
    public CommonReturnType readNotice(
            @RequestParam(name = "id") Integer id) throws BusinessException {
        //参数检验
        checkIntNull(id);
        noticeParentService.readNotice(id);
        return CommonReturnType.create(null);
    }

}
