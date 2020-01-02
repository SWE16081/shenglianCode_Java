package com.youjiao.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Zjp
 * 2019/05/15
 * web信息配置类
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Resource
    private UserLoginInterceptor userLoginInterceptor;
    @Resource
    private AdminAuthorityInterceptor adminAuthorityInterceptor;

    /**
     * @author Zjp
     * 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*用户登录拦截器*/
        //需要拦截的用户端路径
        String[] userPath = {
                "/child",
                "/class",
                "/dishes",
                "/homeworkParent",
                "/homework",
                "/noticeParent",
                "/noticeTeacher",
                "/parent",
                "/teacher",};
        //管理员端除外路径
        String[] adminExcludePath = {
                "/admin/adminManage/*",
                "/admin/admin/*"};
        //对于所有用户的拦截器，校验是否登录成功
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns(userPath)//需要拦截的用户端路径
                .addPathPatterns("/admin/**")//拦截管理员端全部路径
                .excludePathPatterns(adminExcludePath);//admin端除外的接口

        /*超级管理员拦截器*/
        //超级管理员权限才能访问的路径
        String[] adminAuthorityPath = {
                "/admin/agent/*",
                "/admin/course/*",
                "/admin/activity/*",
                "/admin/fixedCourse/*",
                "/admin/adminDishes/*",
                "/admin/termRecord/*",
                "/admin/schedule/*",
                "/admin/adminDishes/*",
                "/adminTransactionRecord/getSchoolList",};
        //超级管理员不需要限制的路径
        String[] adminAuthorityExcludePath = {
                "/admin/schedule/getScheduleWeekList",
                "/admin/adminDishes/getRecordDishesList*",
                "/admin/termRecord/getTermRecordList",
        };

        //超级管理员拦截器注册
        registry.addInterceptor(adminAuthorityInterceptor)
                .addPathPatterns(adminAuthorityPath)
                .excludePathPatterns(adminAuthorityExcludePath);
    }
}
