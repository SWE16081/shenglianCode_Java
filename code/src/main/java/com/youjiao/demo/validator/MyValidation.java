package com.youjiao.demo.validator;

import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.CommonError;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.util.MyLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Objects;
import java.util.regex.Pattern;


/**
 * @author Zjp
 * 2019/03/10
 * 多个类需要使用的校验函数
 */
public final class MyValidation {
    /**
     * @author Zjp
     * 检查字符串是否为null或空字符串
     * 如果是，则抛出参数不合法异常
     */
    public static void checkStrNull(String... array) throws BusinessException {
        for (String var : array) {
            if (var == null || StringUtils.isEmpty(var.trim())) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
            }
        }
    }

    /**
     * @author Zjp
     * 检查Integer是否为null或小于0
     * 如果是，则抛出参数不合法异常
     */
    public static void checkIntNull(Integer... array) throws BusinessException {
        for (Integer var : array) {
            if (var == null || var < 0) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
            }
        }
    }

    /**
     * @author Zjp
     * 检查Object是否为null，用于校验其他类型，例如Boolean
     * 如果是，则抛出参数不合法异常
     */
    public static void checkObjectNull(Object... objects) throws BusinessException {
        for (Object var : objects) {
            if (var == null) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
            }
        }
    }

    /**
     * @author Zjp
     * 检查Object是否为null
     * 如果是，则抛出指定的错误信息
     */
    public static void checkObjectNull(String message, Object... objects) throws BusinessException {
        for (Object var : objects) {
            if (var == null) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, message);
            }
        }
    }

    /**
     * @author Zjp
     * 用于获取BingdingResult校验结果
     */
    public static void validateObject(CommonError error, BindingResult result) throws BusinessException {
        if (result.hasErrors()) {
            MyLog.error(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            throw new BusinessException(error);
        }
    }

    /**
     * @author Zjp
     * 验证手机号的合法性
     * 验证手机号是否有11位，是否都是数字
     */
    public static void checkTelephone(String telephone) throws BusinessException {
        String pattern = "^[1][34578][0-9]{9}$";
        if (!Pattern.matches(pattern, telephone)) {
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "手机号不合法");
        }
    }

    /**
     * @author Lyy
     * 检查身份证号是否合法
     */
    public static void checkIdNumber(String idNumber) throws BusinessException {
        if (idNumber.length() != 18) {//身份证号长度必须为18位
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "身份证号必须为18位");
        }
        //如果身份证号最后一位为X，则前17位都必须为数字，否则18位都应该为数字
        int index;
        if(idNumber.substring(17).equals("X")||idNumber.substring(17).equals("x")){
            index=17;
        }else{
            index=18;
        }
        if (!StringUtils.isNumeric(idNumber.substring(0, index))) {//判断是否是数字
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "身份证号格式不合法");
        }
    }
}
