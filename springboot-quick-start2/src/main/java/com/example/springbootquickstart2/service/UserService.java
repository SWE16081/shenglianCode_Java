package com.example.springbootquickstart2.service;


import com.example.springbootquickstart2.entity.UserEntity;
import com.example.springbootquickstart2.util.ApiReturnInfo;

import java.util.List;

public interface UserService {
    //用户登录验证
    ApiReturnInfo loginCheck(String username, String password);
}
