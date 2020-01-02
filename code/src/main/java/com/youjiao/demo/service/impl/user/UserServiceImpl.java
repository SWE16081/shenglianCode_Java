package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.dao.ParentDOMapper;
import com.youjiao.demo.dao.TeacherDOMapper;
import com.youjiao.demo.dataobject.ParentDO;
import com.youjiao.demo.dataobject.TeacherDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.user.UserService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MD5Util;
import com.youjiao.demo.util.MySessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final TeacherDOMapper teacherDOMapper;

    private final ParentDOMapper parentDOMapper;

    @Autowired
    public UserServiceImpl(TeacherDOMapper teacherDOMapper, ParentDOMapper parentDOMapper) {
        this.teacherDOMapper = teacherDOMapper;
        this.parentDOMapper = parentDOMapper;
    }

    /**
     * @author Zjp
     * 验证教师编号
     * 如果编号合法则返回对应的教师姓名
     */
    @Override
    public Map<String, Object> checkTeacherCode(String teacherCode) throws BusinessException {
        // 根据教师编号获取教师对象
        TeacherDO teacherDO = teacherDOMapper.selectByTeacherCode(teacherCode);
        if (teacherDO == null) {// 如果教师对象不存在，抛出异常
            throw new BusinessException(EmBusinessErr.USER_NOT_EXIST, "教师编号不存在");
        }

        // 将教师id存入session
        MySessionUtil.setAttribute(Constants.TEACHER_ID_SESSION, teacherDO.getTeacherId());
        // 返回教师姓名
        Map<String, Object> responseData = new HashMap<>();
        responseData.put(Constants.TEACHER_NAME_MAP, teacherDO.getName());

        return responseData;// 返回教师姓名
    }

    /**
     * @author Zjp
     * 教师注册
     * 获取session中的教师id，更新教师手机号和密码
     */
    @Override
    public void teacherRegister(String telephone, String encryptPassword) throws BusinessException {
        // 获取session中的教师id
        Integer teacherId = (Integer) (MySessionUtil.getAttribute(Constants.TEACHER_ID_SESSION));

        TeacherDO teacherDO=teacherDOMapper.selectByPrimaryKey(teacherId);
        //如果该用户还是初始密码，则允许更新数据，否则抛出“重复注册”异常
        if(!teacherDO.getPassword().equals(MD5Util.getMD5(telephone))){
            throw new BusinessException(EmBusinessErr.USER_REGISTER_TWICE);
        }
        // 根据教师id在教师表中更新该教师的手机号和密码
        try {
            teacherDOMapper.updateByTeacherId(telephone, encryptPassword, teacherId);
        } catch (Exception e) {// 手机号有唯一性约束，如果更新操作失败则提示“手机号已被注册”
            throw new BusinessException(EmBusinessErr.TELEPHONE_EXIST);
        }

        // 将教师id存到session中
        MySessionUtil.setAttribute(Constants.USER_LOGIN_SESSION, true);//用户登录成功标志
        MySessionUtil.setAttribute(Constants.TEACHER_ID_SESSION, teacherId);
    }

    /**
     * @author Zjp
     * 家长注册
     * 将生成的家长id存到session中
     */
    @Override
    public void parentRegister(ParentDO parentDO) throws BusinessException {
        ParentDO parentDO1 = parentDOMapper.selectByTelephone(parentDO.getTelephone());
        if (parentDO1 == null) {//如果没有该家长用户，就创建一个
            // 将信息插入数据库，并取出自增长生成的家长id（需要在mapper.xml中设置参数）
            try {
                parentDOMapper.insertSelective(parentDO);
            } catch (Exception e) {// 手机号有唯一性约束，如果插入操作失败则提示“手机号已被注册”
                throw new BusinessException(EmBusinessErr.TELEPHONE_EXIST);
            }
        } else {//已经由管理员创建了家长用户，就更新数据
            //如果该用户还是初始密码，则允许更新数据，否则抛出“重复注册”异常
            if(!parentDO1.getPassword().equals(MD5Util.getMD5(parentDO.getTelephone()))){
                throw new BusinessException(EmBusinessErr.USER_REGISTER_TWICE);
            }
            parentDO.setParentId(parentDO1.getParentId());
            try {
                parentDOMapper.updateByPrimaryKeySelective(parentDO);
            } catch (Exception e) {// 手机号有唯一性约束，如果更新操作失败则提示“手机号已被注册”
                throw new BusinessException(EmBusinessErr.TELEPHONE_EXIST);
            }
        }

        // 将家长id存到session中
        MySessionUtil.setAttribute(Constants.USER_LOGIN_SESSION, true);//用户登录成功标志
        MySessionUtil.setAttribute(Constants.PARENT_ID_SESSION, parentDO.getParentId());
    }

    /**
     * @author WengWenxin
     * 登录检测
     */
    @Override
    public void validateLogin(String telephone, String password, Integer role) throws BusinessException {
        Integer id;//用户id
        if (role == Constants.TEACHER) {
            TeacherDO teacherDO = teacherDOMapper.selectByTelephoneAndPassword(telephone, password);
            if (teacherDO == null) {
                throw new BusinessException(EmBusinessErr.USER_LOGIN_FAILED);
            }
            id = teacherDO.getTeacherId();
            MySessionUtil.setAttribute(Constants.TEACHER_ID_SESSION, id);
            /*
             * @author Ck
             * 设置教师登录权限
             */
            MySessionUtil.setAttribute(Constants.JURISDICTION_SESSION,Constants.USER_JURISDICTION_TEACHER);
        } else if (role == Constants.PARENT) {
            ParentDO parentDO = parentDOMapper.selectByTelephoneAndPassword(telephone, password);
            if (parentDO == null) {
                throw new BusinessException(EmBusinessErr.USER_LOGIN_FAILED);
            }
            id = parentDO.getParentId();
            MySessionUtil.setAttribute(Constants.PARENT_ID_SESSION, id);
            /*
             * @author Ck
             * 设置用户登录权限
             */
            MySessionUtil.setAttribute(Constants.JURISDICTION_SESSION,Constants.USER_JURISDICTION_PARENT);
        }
        //用户登录成功标志
        MySessionUtil.setAttribute(Constants.USER_LOGIN_SESSION, true);
    }
}
