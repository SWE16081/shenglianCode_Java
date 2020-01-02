package com.example.springbootquickstart2;

import com.example.springbootquickstart2.entity.Student;
import com.example.springbootquickstart2.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

@SpringBootTest
class SpringbootQuickStart2ApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的
    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象的
    @Autowired
    RedisTemplate<Object, Student> studentRedisTemplate;
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
//        System.out.println(dataSource.getClass());
//        Connection connection=dataSource.getConnection();
//        System.out.println("111");
//        System.out.println(connection);
//        connection.close();
        Logger logger= LoggerFactory.getLogger(getClass());
        logger.trace("这是trace日志");
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
    }
    @Test
    public void test(){
//        stringRedisTemplate.opsForValue().append("msg","helloasd");
//        String msg =stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(msg);
//          stringRedisTemplate.opsForList().leftPush("mylist","1");
//          stringRedisTemplate.opsForList().leftPush("mylist","2");


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

}
