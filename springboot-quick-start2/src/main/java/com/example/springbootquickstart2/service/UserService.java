package com.example.springbootquickstart2.service;


import com.example.springbootquickstart2.entity.UserEntity;
import com.example.springbootquickstart2.error.BusinessException;
import com.example.springbootquickstart2.util.ApiReturnInfo;

public interface UserService {
    //用户登录验证
    UserEntity loginCheck(String username, String password) throws BusinessException;
    //用户注册
    ApiReturnInfo register(String username, String password, long time);

}
