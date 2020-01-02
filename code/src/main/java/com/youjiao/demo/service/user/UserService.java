package com.youjiao.demo.service.user;

import com.youjiao.demo.dataobject.ParentDO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Service
public interface UserService {

    /**
     * @author Zjp
     * 验证教师编号
     * 如果编号合法则返回对应的教师姓名
     */
    Map<String, Object> checkTeacherCode(String teacherCode) throws BusinessException;

    /**
     * @author Zjp
     * 教师注册
     * 获取session中的教师id，更新教师手机号和密码
     */
    void teacherRegister(String telephone, String encryptPassword) throws BusinessException;

    /**
     * @author Zjp
     * 家长注册
     * 将生成的家长id存到session中
     */
    void parentRegister(ParentDO parentDO) throws BusinessException;


    /**
     * @author WengWenxin
     * 登录检测
     */
    void validateLogin(String telephone, String encryptPassword, Integer role) throws BusinessException, NoSuchAlgorithmException;
}
