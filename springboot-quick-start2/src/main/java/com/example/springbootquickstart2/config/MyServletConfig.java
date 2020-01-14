package com.example.springbootquickstart2.config;


import com.example.springbootquickstart2.filter.MyFilter;
import com.example.springbootquickstart2.listener.MyListener;
import com.example.springbootquickstart2.servlet.MyServlet;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class MyServletConfig implements WebMvcConfigurer {

    //配置嵌入式servlet容器
    @Bean
    public ConfigurableServletWebServerFactory configurableServletWebServerFactory(){
        //定制嵌入式servlet容器的相关规则
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(8080);
        return factory;
    }

    //注册三大组件
    //servlet组件
    @Bean
    public ServletRegistrationBean myServlet(){
        //映射/myservlet请求
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return servletRegistrationBean;
    }

    //filter组件
    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean(){
        FilterRegistrationBean<MyFilter> myFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        myFilterFilterRegistrationBean.setFilter(new MyFilter());
//        myFilterFilterRegistrationBean.addUrlPatterns("/hello", "/myServlet");
            myFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return myFilterFilterRegistrationBean;
    }

    //listener组件
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        JettyServletWebServerFactory tomcat = new JettyServletWebServerFactory();
//        return tomcat;
//    }
}


