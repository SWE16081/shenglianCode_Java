package com.example.springbootquickstart2.util;

import liquibase.pro.packaged.M;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
* 自定义日志类
* */
public class MyLogUtil {
    private static Logger logger=LoggerFactory.getLogger(MyLogUtil.class);

    /*
    * 调试程序代码
    * */
    public static void debug(String msg){
        logger.debug(msg);
    }
    /*
    * 程序代码出错，记录不终止程序运行的错误
    * */
    public static void error(String msg){
        logger.error(msg);
    }
    /*
    * 输出提示信息时使用，标记程序运行位置
    * */
    public static void info(String msg){
        logger.info(msg);
    }
    public static void trace(String msg){
        logger.trace(msg);
    }
    /*
    * 一般不使用。标记潜在会出错的位置
    * */
    public static void warn(String msg){
        logger.warn(msg);
    }
}
