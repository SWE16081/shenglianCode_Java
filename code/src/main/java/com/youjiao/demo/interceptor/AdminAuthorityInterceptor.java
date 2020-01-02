package com.youjiao.demo.interceptor;

import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.util.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zjp
 * 2019/05/15
 * 超级管理员权限拦截器
 * 只允许超级管理员通过拦截器
 */
@Component
public class AdminAuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Byte jurisdiction = (Byte) request.getSession().getAttribute(Constants.JURISDICTION_SESSION);
        //超级管理员才能通过拦截器
        if (jurisdiction != null && jurisdiction == Constants.ADMIN_JURISDICTION_TOP) {
            return true;
        } else {
            throw new BusinessException(EmBusinessErr.PERMISSION_DENIED);
        }
    }
}
