package com.youjiao.demo.controller;

import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseController {
    protected static final String CONTENT_TYPE_APPLICATION = "application/x-www-form-urlencoded";
    protected static final String CONTENT_TYPE_MULTIPART = "multipart/form-data";

    /**
     * @author unknown
     * 异常处理函数
     * 处理所有Controller类抛出的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(Exception ex) {
        Map<String, Object> responseData = new HashMap<>();
        //将异常转为BusinessException类型
        BusinessException be=(ex instanceof BusinessException)?
                (BusinessException)ex:
                new BusinessException(ex,EmBusinessErr.UNKNOWN_ERROR);

        final String ErrorFormatMsg = "异常抛出 : \n异常简介 : %s\n异常详情 : \n%s";

        //格式化输出异常信息
        MyLog.error(String.format(ErrorFormatMsg,
                be.getErrMsg(),
                //若为业务逻辑异常，输出BusinessException的异常信息，否则输出其中的Exception异常信息
                be.isExceptionNull() ? MyExceptionUtil.getErrorMsg(be) : be.getExceptionMsg()));

        responseData.put("errCode", be.getErrCode());
        responseData.put("errMsg", be.getErrMsg());
        return CommonReturnType.create(responseData, "fail");
    }
}
