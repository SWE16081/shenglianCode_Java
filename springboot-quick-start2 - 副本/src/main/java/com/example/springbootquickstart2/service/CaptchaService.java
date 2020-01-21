package com.example.springbootquickstart2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;



import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Service
public class CaptchaService {
//    @Value("${server.session.timeout}")
//    private Integer timeout;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串的
    private long timeout=5;
    public Map<String,Object> createToken(String captcha){
        //生成一个token
        String cToken = UUID.randomUUID().toString();
        //生成验证码对应的token  以token为key  验证码为value存在redis中
        //单位是秒
        stringRedisTemplate.opsForValue().set(cToken,captcha,timeout,TimeUnit.MINUTES);
        Map<String, Object> map = new HashMap<>();
        map.put("cToken", cToken);
        return map;
    }

}
