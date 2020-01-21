package com.example.springbootquickstart2.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 生成kaptcha验证码 配置文件
 */
@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "250");
        // 图片高
        properties.setProperty("kaptcha.image.height", "80");
        // 字体大小
        properties.setProperty("kaptcha.textproducer.font.size", "60");
        // session key
        properties.setProperty("kaptcha.session.key", "code");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //文本集合
        properties.setProperty(" kaptcha.textproducer.char.string","abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //图片样式
        properties.setProperty("com.google.code.kaptcha.impl.ShadowGimpy", "com.google.code.kaptcha.impl.WaterRipple ");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "8");
        //背景颜色渐变，开始颜色
//        properties.setProperty("kaptcha.background.clear.from", "219, 208, 208");
////        //背景颜色渐变， 结束颜色
//        properties.setProperty("kaptcha.background.clear.to", "146, 141, 141");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
