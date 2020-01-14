package com.example.springbootquickstart2.Interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    //自动注入自定义的拦截器
    @Autowired
    private MyInterceptor myInterceptor;
    /*
    *拦截器注册
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/adminLogin")
                .excludePathPatterns("/springget")
                .excludePathPatterns("/swe")
                .excludePathPatterns("/selStudent")
                .excludePathPatterns("/findbyone2")
                .excludePathPatterns("/cachettest")
                .excludePathPatterns("/saveStudent")
                 .excludePathPatterns("/adminRegister")
                .excludePathPatterns("/selectUser")
                .excludePathPatterns("/captcha")
                .excludePathPatterns("/loginCheck")
                .excludePathPatterns("/selStudentByTwo");

    }
}
