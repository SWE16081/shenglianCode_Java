package com.youjiao.demo.error;


import com.youjiao.demo.util.MyExceptionUtil;

public class BusinessException extends Exception implements CommonError {

        private Exception exception;
        private CommonError commonError;

    public BusinessException(CommonError commonError) {
        this(null, commonError);//调用其他构造函数
    }

    public BusinessException(CommonError commonError, String msg) {

        this(null, commonError, msg);
    }

    public BusinessException(Exception e, CommonError commonError) {
        this(e, commonError, commonError.getErrMsg());
    }

    public BusinessException(Exception e, CommonError commonError, String msg) {
        super();
        this.exception = e;
        this.commonError = commonError;
        this.commonError.setErrMsg(msg);
    }

    public String getExceptionMsg() {
        return MyExceptionUtil.getErrorMsg(this.exception);
    }

    public boolean isExceptionNull() {
        return this.exception == null;
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public void setErrMsg(String msg) {
        this.setErrMsg(msg);
    }
}
