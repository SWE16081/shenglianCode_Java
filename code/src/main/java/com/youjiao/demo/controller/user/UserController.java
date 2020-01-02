package com.youjiao.demo.controller.user;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.dataobject.ParentDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.user.UserService;
import com.youjiao.demo.util.CaptchaImage;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MD5Util;
import com.youjiao.demo.util.MySessionUtil;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.youjiao.demo.validator.MyValidation.*;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @author Zjp
     * 验证教师编号
     * 将教师id存入session，并返回教师姓名
     */
    @PostMapping(value = "checkTeacherCode", consumes = {CONTENT_TYPE_APPLICATION})
    @ResponseBody
    public CommonReturnType checkTeacherCode(@RequestParam(name = "teacherCode") String teacherCode) throws BusinessException {
        // 字符串判空
        checkStrNull(teacherCode);

        // 验证教师编号，获取教师姓名
        Map<String, Object> responseData = userService.checkTeacherCode(teacherCode);

        // 返回数据
        return CommonReturnType.create(responseData);
    }

    /**
     * @author Zjp
     * 获得验证码，并生成验证码图片
     * 将验证码字符串存入session，返回验证码图片的路径
     */
    @GetMapping(value = "getVerificationCode")
    @ResponseBody
    public CommonReturnType getVerificationCode() {
        // 生成验证码，并存入session
        // 生成验证码图片，并返回图片base64编码字符串
        String imageFile = CaptchaImage.outputCaptcha();
        // 返回图片路径
        Map<String, Object> result = new HashMap<>();
        result.put("captchaImage", imageFile);

        return CommonReturnType.create(result);
    }

    /**
     * @author Zjp
     * 教师注册
     * 注册成功后调用登录函数，确保登录函数会将id等信息存入session
     */
    @PostMapping(value = "teacherRegister", consumes = {CONTENT_TYPE_APPLICATION})
    @ResponseBody
    public CommonReturnType teacherRegister(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "verificationCode") String postVerificationCode
    ) throws BusinessException {
        // 校验用户数据
        checkUserInfo(telephone, password, postVerificationCode);

        // 教师注册，传入加密后的密码
        userService.teacherRegister(telephone, MD5Util.getMD5(password));

        // 注册成功，调用登录函数
        return CommonReturnType.create(null);//return login(telephone,password,role)
    }

    /**
     * @author Zjp
     * 家长注册
     * 注册成功后调用登录函数，确保登录函数会将id等信息存入session
     */
    @PostMapping(value = "parentRegister", consumes = {CONTENT_TYPE_APPLICATION})
    @ResponseBody
    public CommonReturnType parentRegister(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "sex") Boolean sex,
            @RequestParam(name = "verificationCode") String postVerificationCode
    ) throws BusinessException {
        // 校验用户数据
        MyValidation.checkStrNull(name);
        MyValidation.checkObjectNull(sex);
        checkUserInfo(telephone, password, postVerificationCode);
        // 创建家长对象
        ParentDO parentDO = new ParentDO();
        parentDO.setName(name);
        parentDO.setTelephone(telephone);
        parentDO.setPassword(MD5Util.getMD5(password));
        parentDO.setSex(sex);
        // 家长注册
        userService.parentRegister(parentDO);
        // 注册成功，调用登录函数
        return CommonReturnType.create(null);//return login(telephone,password,role)
    }

    /**
     * @author Zjp
     * 校验手机号，密码和验证码的有效性
     */
    private void checkUserInfo(String telephone, String password, String postVerificationCode) throws BusinessException {
        // 字符串判空
        checkStrNull(telephone, password, postVerificationCode);
        // 校验用户输入的验证码是否一致
        checkVerificationCode(postVerificationCode);
        // 验证手机号
        checkTelephone(telephone);
    }

    /**
     * @author Zjp
     * 校验用户输入的验证码是否一致
     * 校验成功后清除session中的验证码
     */
    private void checkVerificationCode(String postVerificationCode) throws BusinessException {
        // 对参数进行判空处理
        checkStrNull(postVerificationCode);
        // 获取session中的验证码
        String code = (String) MySessionUtil.getAttribute(Constants.VERIFICATION_CODE);
        // 清除session中的验证码
        //测试阶段，为了方便，不需要清除验证码
        //MySessionUtil.removeAttribute(Constants.VERIFICATION_CODE);
        // 如果验证码不一致，抛出异常
        if (!code.equalsIgnoreCase(postVerificationCode)) {
            throw new BusinessException(EmBusinessErr.VERIFICATION_CODE_WRONG);
        }
    }

    /**
     * @author WengWenxin
     * 验证教师登录信息，若成功则反馈正确信息，否则抛出异常
     */
    @PostMapping(value = "login")
    @ResponseBody
    public CommonReturnType loginTeacher(@RequestParam(name = "telephone") String telephone,
                                         @RequestParam(name = "password") String password,
                                         @RequestParam(name = Constants.VERIFICATION_CODE) String postVerificationCode,
                                         @RequestParam(name = "role") Integer role
    ) throws BusinessException, NoSuchAlgorithmException {
        // 校验用户数据
        checkUserInfo(telephone, password, postVerificationCode);
        //校验角色参数
        if (role == null || (role != Constants.PARENT && role != Constants.TEACHER)) {
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
        }
        //用户登录服务
        userService.validateLogin(telephone, MD5Util.getMD5(password), role);
        return CommonReturnType.create(null);
    }
}
