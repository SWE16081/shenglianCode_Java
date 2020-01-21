package com.example.springbootquickstart2.util;

import com.example.springbootquickstart2.error.BusinessException;
import com.example.springbootquickstart2.error.ExceptionCode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public   final class Md5Util {
    public static  String  trunkMD5(String str) throws BusinessException {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw  new BusinessException(ExceptionCode.MD5_CREATE_ERROR);
        }
    }

}