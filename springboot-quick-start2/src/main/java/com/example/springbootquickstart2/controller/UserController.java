package com.example.springbootquickstart2.controller;


import com.example.springbootquickstart2.Imp.UserEntityImpl;

import com.example.springbootquickstart2.entity.UserEntity;
import com.example.springbootquickstart2.service.UserService;
import com.example.springbootquickstart2.util.ApiReturnInfo;
import com.example.springbootquickstart2.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//@Configuration
@RestController

//@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders="*")
//@ResponseBody
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/swe")
    public String login2(){
        return "111";

//        return "222";
    }
    /*
    * 用户登录
    * */
    @PostMapping("/adminLogin")
    public ApiReturnInfo login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request){
        System.out.println("username"+username);
        System.out.println("password"+password);
        ApiReturnInfo apiReturnInfo=userService.loginCheck(username,password);
        UserEntity userEntity= (UserEntity) apiReturnInfo.getData();
        System.out.println(userEntity.getUserName());
        //用户登录信息存于session
        request.getSession().setAttribute(ConstantUtil.USER_SESSION_KEY, userEntity.getUserName());
//        String username2= (String)request.getSession().getAttribute(ConstantUtil.USER_SESSION_KEY);
//        System.out.println("登录拦截器2:"+username2);
        return ApiReturnInfo.success();
    }
    @GetMapping("/springget")
    public String  test(){
        return "111";
    }

}


