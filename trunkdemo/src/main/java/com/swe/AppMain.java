package com.swe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@SpringBootApplication标注表明这个类是SpringBoot的主配置类，SpingBoot会运行这个类的main方法启动Spingboot应用
public class AppMain {

//    @RequestMapping("/test")
//    public String test(){
//        return "111";
//    }
    public static void main(String[] args){
        SpringApplication.run(AppMain.class,args);
    }
}
