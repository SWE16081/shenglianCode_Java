package com.example.springbootquickstart2;

import com.example.springbootquickstart2.bean.Book;
import com.example.springbootquickstart2.entity.Student;
import com.example.springbootquickstart2.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@SpringBootTest
class SpringbootQuickStart2ApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的
    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象的
//    @Autowired
//    RedisTemplate<Object, Student> studentRedisTemplate;
    /*
    * Redis常见的五大类型数据
    * String,List(列表),Set(集合),Hash(散列),zSet(有序集合)
    * stringRedisTemplate.opsForValue()[String(字符串)]
    * stringRedisTemplate.opsForList()[List(列表)]
    * stringRedisTemplate.opsForSet()[Set(集合)]
    * stringRedisTemplate.opsForHash()[Hash(散列)]
    * stringRedisTemplate.opsForZSet()[ZSet(有序集合)]
    * */
    @Test
    void contextLoads() throws SQLException {

        Logger logger= LoggerFactory.getLogger(getClass());
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }
    /*
    *
    * */
    @Test
    public void test(){
        stringRedisTemplate.opsForValue().append("msg","helloasd");
        String msg =stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
          stringRedisTemplate.opsForList().leftPush("mylist","1");
          stringRedisTemplate.opsForList().leftPush("mylist","2");



    }
    @Test
    public void test2(){

        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
//        redisTemplate.opsForValue().set("");
        // 创建一个HashSet
        HashSet<String> set = new HashSet<>();
        set.add("a11");
        set.add("7687sss6");
        set.add("b11");
        set.add("11");
        redisTemplate.opsForValue().set("asdasd",set);
//        studentRedisTemplate.opsForValue().set("set3",set);


    }
    @Test
    public void databasetest() throws SQLException {
                System.out.println(dataSource.getClass());
        Connection connection=dataSource.getConnection();
        System.out.println("111");
        System.out.println(connection);
        connection.close();
    }

    @Autowired
    RabbitTemplate rabbitTemplate;
    /*
    * 1.单播(点对点)
    * 发送消息测试
    * */
    @Test
    public void rabbitmqTest(){
        //message需要自己构造
        //rabbitTemplate.send(exchage,routekey,message);
        //object默认当做消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
       //rabbitTemplate.convertAndSend(exchage,routekey,object);
        Map<String,Object> map=new HashMap<>();
        map.put("msg","这是第一个消息");
//        map.put("data", Arrays.asList("helloword",123,true));
        rabbitTemplate.convertAndSend("exchange.direct","swe.news",map);

    }

    /*
    * 接收消息
    * */
    /*
     * 接收消息并转化为json格式
     * rabbitTemplate中 MessageConverter默认使用SimpleMessageConverter
     * 我们自定义MessageConverter用来转化消息格式
     * */
    @Test
    public void rabbitmqReceive(){
        Object object=rabbitTemplate.receiveAndConvert("swe.news");
        System.out.println(object.getClass());//获取收到消息的数据类型
        System.out.println(object);
    }
     /*
     * 发送消息数据为book类对象
     * */
    @Test
    public void sendBook(){
        rabbitTemplate.convertAndSend("exchange.direct","swe.news",new Book("唐诗三百首","李白"));

    }

    /*
    *
    * 广播*/
    @Test
    public void fanoutSend(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("宋词","苏轼"));
    }
    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));//创建交换机
//        System.out.println("创建direct交换机");
//        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
//        System.out.println("创建队列");
        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",
                Binding.DestinationType.QUEUE,"amqpAdmin.exchange","amqp.test",null));
        System.out.println("创建绑定规则");
        //删除
//        amqpAdmin.deleteQueue()

    }

 }
