package com.example.springbootquickstart2.controller;


import com.example.springbootquickstart2.entity.UserEntity;
import com.example.springbootquickstart2.error.BusinessException;
import com.example.springbootquickstart2.repository.UserRpository;
import com.example.springbootquickstart2.service.CaptchaService;
import com.example.springbootquickstart2.service.UserService;
import com.example.springbootquickstart2.util.*;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

//@Configuration
@RestController

//@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders="*")
//@ResponseBody
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRpository userRpository;
    @Autowired
    private DefaultKaptcha producer;
    @Autowired
    private CaptchaService captchaService;/*自动注入 CaptchaService用于生成token并且以token为key存入redis缓存*/

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @PostMapping("/swe")
    public String login2(){
        return "111";

//        return "222";
    }


    @GetMapping("/springget")
    public String  test(){
        return "111";
    }
    @GetMapping("/selectUser")
    public List <UserEntity>  selectall(){
        return userRpository.findAll();
    }
    @PostMapping("/adminRegister")
    public ApiReturnInfo register(@RequestParam("username") String username, @RequestParam("password") String password){
        MyLogUtil.info("提交的账号"+username);
        MyLogUtil.info("提交的密码"+password);
        return userService.register(username,password,System.currentTimeMillis());
    }
    /*
     * 用户登录
     * */
    @PostMapping("/adminLogin")
    public ApiReturnInfo login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) throws BusinessException {
        System.out.println("账号:"+username);
        System.out.println("密码:"+password);
        System.out.println("密码:"+Md5Util.trunkMD5(password));
        UserEntity userEntity= userService.loginCheck(username, Md5Util.trunkMD5(password));
        if(userEntity==null){
            return ApiReturnInfo.fail();
        }else{
            //            //用户登录信息存于session
            request.getSession().setAttribute(ConstantUtil.USER_SESSION_KEY, userEntity.getUserName());
            return ApiReturnInfo.success();
        }
//        String username2= (String)request.getSession().getAttribute(ConstantUtil.USER_SESSION_KEY);
//        System.out.println("登录拦截器2:"+username2);
    }
    @PostMapping("/captcha")
    public ApiReturnInfo captcha(HttpServletResponse response) throws ServletException, IOException {

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        ByteArrayOutputStream outputStream = null;
        BufferedImage image = producer.createImage(text);
        System.out.println("生成的验证码："+text);
        outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 生成captcha的token
        Map<String, Object> map =captchaService.createToken(text);
        map.put("img", "data:image/jpg;base64,"+Base64.getEncoder().encodeToString(outputStream.toByteArray()));

        /*
        * 或者
        * */
//        byte[] bytes = outputStream.toByteArray();//转换成字节
//        // 对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//        String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
//        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
//        // 生成captcha的token
//        Map<String, Object> map =captchaService.createToken(text);
//        map.put("img", "data:image/jpg;base64,"+png_base64);

        return ApiReturnInfo.success(map);


    }
    @PostMapping("loginCheck")
    public ApiReturnInfo loginCheck(@RequestParam("username")String username,@RequestParam("password") String password
            ,@RequestParam("captcha") String captcha,@RequestParam("cToken")String cToken,HttpServletRequest request) throws BusinessException {
        System.out.println("账号:"+username);
        System.out.println("密码:"+password);
        System.out.println("验证码:"+captcha);
        System.out.println("cToken:"+cToken);
        //获取缓存中的验证码
        String captchaRedis=stringRedisTemplate.opsForValue().get(cToken);
        System.out.println("缓存为："+captchaRedis);
        if(!captcha.equals(captchaRedis)){
            return ApiReturnInfo.fail(ApiCode.CAPTCHA_ERROR);
        }else{
            UserEntity userEntity= userService.loginCheck(username, Md5Util.trunkMD5(password));
            if(userEntity==null){
                return ApiReturnInfo.fail(ApiCode.USERNAME_PWD_ERROE);
            }else{
                //            //用户登录信息存于session
//                request.getSession().setAttribute(ConstantUtil.USER_SESSION_KEY, userEntity.getUserName());//前端session
                //查询user信息返回userid用于存储sessionStorage
                return ApiReturnInfo.success(userEntity);
            }
        }

    }
}


