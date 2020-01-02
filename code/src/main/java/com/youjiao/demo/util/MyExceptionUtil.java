package com.youjiao.demo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Zjp
 * 2019/03/21
 */
public final class MyExceptionUtil {
    /**
     * @author Zjp
     * ·µ»ØÏêÏ¸´íÎóÐÅÏ¢
     */
    public static String getErrorMsg(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
