package com.example.springbootquickstart2.error;


/**
 * 枚举类型异常信息，状态码
 */
public enum ExceptionCode implements MyErrorInter{

    MD5_CREATE_ERROR(10001,"MD5加密错误"),


    /*
    * 数据操作状态码
    * */
    DATABASE_SEL_ERROR(20001,"数据库查询失败"),

    /*
    *
    * */
    USER_LOGIN_ERROR(30001,"user_loginin_error");
    private int code;
    private String msg;

    /**
     *
     * @param code
     * @param msg
     */
    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
