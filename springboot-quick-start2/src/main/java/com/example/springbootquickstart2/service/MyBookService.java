package com.example.springbootquickstart2.service;

import com.example.springbootquickstart2.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/*
* 监听消息队列中来自于book的相关内容
* */
@Service
public class MyBookService {
    @RabbitListener(queues ="swe.news")
     public void receive(Book book){
        System.out.println("收到消息："+book);
    }

    @RabbitListener(queues ="demo.news")
    public void receive2(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
