package com.example.springbootquickstart2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

/*
* @EnableCaching开启基于注解的缓存
* */
@SpringBootApplication
@EnableCaching
//@EntityScan("swe")
public class SpringbootQuickStart2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuickStart2Application.class, args);
    }

}
