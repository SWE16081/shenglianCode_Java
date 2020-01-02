package com.example.springbootquickstart2.Interceptor;

import com.example.springbootquickstart2.entity.UserEntity;
import com.example.springbootquickstart2.util.ConstantUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
       String username= (String)request.getSession().getAttribute(ConstantUtil.USER_SESSION_KEY);
       System.out.println("登录拦截器:"+username);
       if(username!=null){
           return true;
       }else{
           return false;
       }
    }


}
