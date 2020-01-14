package com.example.springbootquickstart2.util;

public enum ApiCode {


    CAPTCHA_ERROR("10001","验证码错误"),
    USERNAME_PWD_ERROE("10002","账号密码错误"),

    /*
     * 数据操作状态码
     * */
    DATABASE_SEL_ERROR("20001","数据库查询失败"),

    /*
     *
     * */
    USER_LOGIN_ERROR("30001","user_loginin_error");
    private String code;
    private String msg;

    /**
     *
     * @param code
     * @param msg
     */
    ApiCode(String  code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }


    public String getMessage() {
        return this.msg;
    }
}
