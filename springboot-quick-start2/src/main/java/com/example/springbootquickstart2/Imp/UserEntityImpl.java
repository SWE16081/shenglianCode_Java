package com.example.springbootquickstart2.Imp;

import com.example.springbootquickstart2.entity.UserEntity;
import com.example.springbootquickstart2.repository.UserRpository;
import com.example.springbootquickstart2.service.UserService;
import com.example.springbootquickstart2.util.ApiReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityImpl  implements UserService {
    @Autowired
    UserRpository userRpository;
    @Override
    public ApiReturnInfo loginCheck(String username, String password) {
        UserEntity userEntity=new UserEntity();
        try {
            userEntity=userRpository.findByuserNameAndPassword(username,password);
        } catch (Exception e) {
            System.out.println("WRONG FORMAT!");
        }
        return ApiReturnInfo.success(userEntity);
    }
}
