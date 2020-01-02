package com.youjiao.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zjp
 * 2019/03/08
 */
public final class MyLog {
    private static Logger logger = LoggerFactory.getLogger(MyLog.class);

    /**
     * @author Zjp
     * 调试代码时使用，相当于System.out.println();
     */
    public static void debug(String msg) {
        logger.debug(msg);
    }

    /**
     * @author Zjp
     * 输出提示信息时使用，标记程序运行位置
     */
    public static void info(String msg) {
        logger.info(msg);
    }

    /**
     * @author Zjp
     * 一般不使用。标记潜在会出错的位置
     */
    public static void warn(String msg) {
        logger.warn(msg);
    }

    /**
     * @author Zjp
     * 程序出现错误时使用，记录不会终止程序运行的错误
     */
    public static void error(String msg) {
        logger.error(msg);
    }
}
