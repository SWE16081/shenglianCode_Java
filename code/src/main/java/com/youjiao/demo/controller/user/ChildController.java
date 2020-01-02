package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.user.ChildVO;
import com.youjiao.demo.dataobject.ParentDO;
import com.youjiao.demo.dataobject.StudentDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.ParentService;
import com.youjiao.demo.service.user.StudentService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MySessionUtil;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tzj
 * 2019/03/07 20:16
 */
@RestController("child")
@RequestMapping("/child")
public class ChildController extends BaseController {

    private final StudentService studentService;
    private final ParentService parentService;

    @Autowired
    public ChildController(StudentService studentService, ParentService parentService) {
        this.studentService = studentService;
        this.parentService = parentService;
    }

    /**
     * @author Tzj
     * 通过家长id获取该家长已添加的孩子列表
     */
    @GetMapping(value = "/getChildList")
    public CommonReturnType getChildList() throws BusinessException {
        //在session中获取父母id
        Integer parentId=(Integer) MySessionUtil.getAttribute(Constants.PARENT_ID_SESSION);
        // 检查父母id是否为非空
        MyValidation.checkIntNull(parentId);
        // 通过父母id查询孩子
        List<ChildVO> childVOList = studentService.getStudentsByParentId(parentId);
        return CommonReturnType.create(childVOList);
    }

    @PostMapping(value = "/addChild")
    public CommonReturnType addChild(@RequestParam("idNumber") String idNumber) throws BusinessException {
        //在session中获取父母id
        Integer parentId=(Integer) MySessionUtil.getAttribute(Constants.PARENT_ID_SESSION);
        ParentDO parentDO = parentService.getParentByParentId(parentId);
        //判断该父母是否存在
        MyValidation.checkObjectNull(parentDO);

        // 检查孩子身份证是否为空
        MyValidation.checkStrNull(idNumber);
        // 通过孩子身份证获得孩子实体
        StudentDO studentDO = studentService.getStudentByIdNumber(idNumber);
        MyValidation.checkObjectNull(studentDO);
        // 判断该孩子是否已有父母
        if (studentDO.getParentId() != null) {
            throw new BusinessException(EmBusinessErr.PARENT_ALREADY_EXIST);
        }
        // 孩子添加操作
        studentService.addChild(parentId, idNumber);

        return CommonReturnType.create(null);
    }
}
