package com.example.springbootquickstart2;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
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
@EnableRabbit  //开启基于注解的rabbit模式

public class SpringbootQuickStart2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuickStart2Application.class, args);
    }

}
/*
* RabbitAutoConfiguration为rabbitmq的自动配置类
*有自动配置了连接工厂connectionfactory
* RabbitProperties封装了rabbitmq的所有配置
*RabbitTemplate给rabbitmq发送和接收消息
* AmqpAdmin：rabbitmq系统管理功能组件
* */
