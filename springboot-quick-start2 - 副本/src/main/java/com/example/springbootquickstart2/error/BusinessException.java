package com.example.springbootquickstart2.error;


/*
* 自定义业务异常类
* */
public class BusinessException extends Exception implements MyErrorInter {
    private Exception exception;
    private MyErrorInter myErrorInter;

    private int code;
    private String msg;
    public BusinessException(MyErrorInter myErrorInter) {
        super();
        this.myErrorInter=myErrorInter;
        this.code=myErrorInter.getCode();
        this.msg=myErrorInter.getMessage();
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
