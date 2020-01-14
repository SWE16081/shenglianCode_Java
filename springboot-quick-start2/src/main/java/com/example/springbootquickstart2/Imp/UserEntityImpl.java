package com.example.springbootquickstart2.Imp;

import com.example.springbootquickstart2.entity.UserEntity;
import com.example.springbootquickstart2.error.BusinessException;
import com.example.springbootquickstart2.error.ExceptionCode;
import com.example.springbootquickstart2.repository.UserRpository;
import com.example.springbootquickstart2.service.UserService;
import com.example.springbootquickstart2.util.ApiReturnInfo;
import com.example.springbootquickstart2.util.MyLogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntityImpl  implements UserService {

    @Autowired
    UserRpository userRpository;

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws BusinessException
     * 登录验证
     */
    @Override
    public UserEntity loginCheck(String username, String password) throws BusinessException {
        UserEntity userEntity=new UserEntity();
        try{
            userEntity=userRpository.findByuserNameAndPassword(username,password);
        }catch(Exception e){
            MyLogUtil.info("查询失败缓存"+userEntity);
            throw new BusinessException(ExceptionCode.USER_LOGIN_ERROR);
        }
       return userEntity;
    }

    @Override
    public ApiReturnInfo register(String username, String password, long time) {
        try{
            userRpository.register(username,password,time);
//            System.out.println(userRpository.register(username,password,time));
        }catch(Exception e){
            System.out.println("WRONG FORMAT!");
            return ApiReturnInfo.fail();
        }
        return ApiReturnInfo.success();
    }
}

