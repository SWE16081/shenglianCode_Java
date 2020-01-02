package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.dataobject.AdminDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminAgentService;
import com.youjiao.demo.service.admin.AdminManageService;
import com.youjiao.demo.util.CaptchaImage;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MD5Util;
import com.youjiao.demo.util.MySessionUtil;
import com.youjiao.demo.validator.MyValidation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin/admin")
@Controller("adminAdmin")
public class AdminController extends BaseController {

    private final AdminManageService adminManageService;
    private final AdminAgentService adminAgentService;

    public AdminController(AdminManageService adminManageService, AdminAgentService adminAgentService) {
        this.adminManageService = adminManageService;
        this.adminAgentService = adminAgentService;
    }

    /**
     * @author Tzj
     * 前端验证码接口
     */
    @ApiOperation("前端验证码接口")
    @ApiImplicitParam(name = "前端验证码", value = "无", required = true, dataType = "无")
    @RequestMapping(value = "/getVerificationCode", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getVerificationCode() {
        //将验证码存入session，并返回验证码图片的base64编码字符串
        String imageFile = CaptchaImage.outputCaptcha();

        Map<String, Object> result = new HashMap<>();
        result.put("captchaImage", imageFile);
        return result;
    }

    /**
     * @author Tzj
     * 管理员登录接口
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonReturnType adminLogin(@RequestParam("userName") String userName,
                                       @RequestParam("password") String password,
                                       @RequestParam("code") String code) throws BusinessException {
        //获取session里的验证码
        MyValidation.checkObjectNull(MySessionUtil.getAttribute(Constants.VERIFICATION_CODE));
        String verificationCode = (String) MySessionUtil.getAttribute(Constants.VERIFICATION_CODE);
        //将session里的验证码和用户上传的验证码作比较
        MyValidation.checkStrNull(code);
        if (!code.equalsIgnoreCase(verificationCode)) {
            throw new BusinessException(EmBusinessErr.CODE_WRONG);
        }
        //根据用户名和密码查询用户实体
        AdminDO adminDO = adminManageService.findByNameAndPassword(userName, MD5Util.getMD5(password));
        MyValidation.checkObjectNull(EmBusinessErr.USER_OR_PASSWORD_WRONG, adminDO);
        //检查合约是否过期
        if(adminDO.getJurisdiction()!=0){
            adminManageService.checkContract(adminDO);
        }
        //将用户名和权限以及机构id存入session
        MySessionUtil.setAttribute(Constants.USER_NAME_SESSION, adminDO.getName());
        MySessionUtil.setAttribute(Constants.JURISDICTION_SESSION, adminDO.getJurisdiction());
        MySessionUtil.setAttribute(Constants.SCHOOL_ID_SESSION, adminDO.getSchoolId());
        String schoolName = adminAgentService.findSchoolNameBySchoolId(adminDO.getSchoolId());
        //用户登录成功标志
        MySessionUtil.setAttribute(Constants.USER_LOGIN_SESSION, true);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put(Constants.SCHOOL_NAME_SESSION, schoolName);
        responseData.put(Constants.JURISDICTION_SESSION, adminDO.getJurisdiction());
        responseData.put(Constants.SCHOOL_ID_SESSION, adminDO.getSchoolId());
        return CommonReturnType.create(responseData);
    }

}