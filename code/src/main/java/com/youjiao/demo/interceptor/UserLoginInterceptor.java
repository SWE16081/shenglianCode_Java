package com.youjiao.demo.interceptor;

import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MySessionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zjp
 * 2019/05/15
 * 用户登录拦截器
 * 只允许登录成功的用户通过拦截器
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Boolean login;
        try{
            login = (Boolean) MySessionUtil.getAttribute(Constants.USER_LOGIN_SESSION);
        }catch (Exception e){
            throw new BusinessException(EmBusinessErr.NOT_LOGIN_USER);
        }

        //只允许登录成功的用户通过拦截器
        if (login != null && login) {
            return true;
        } else {
            System.out.println("错误来自："+handler);
            throw new BusinessException(EmBusinessErr.NOT_LOGIN_USER);
        }
    }
}
