package com.youjiao.demo.service.admin;

import com.youjiao.demo.dataobject.AdminDO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminManageService {
    /**
     * @author Tzj
     * 按用户名和密码查找管理员
     */
    AdminDO findByNameAndPassword(String name, String password);

    /**
     * @author Tzj
     * 按用户名查找管理员
     */
    AdminDO findByName(String name);

    /**
     * @author Tzj
     * 添加管理员
     */
    void addManage(Integer schoolId, String name, Integer role) throws BusinessException;

    /**
     * @author Tzj
     * 重置密码
     */
    void resetPassword(Integer adminId) throws BusinessException;

    /**
     * @author Tzj
     * 删除管理员
     */
    void deleteManage(Integer userName) throws BusinessException;

    /**
     * @author Tzj
     * 个人信息修改
     */
    void modifyInfo(String name, String oldPassword, String password) throws BusinessException;


    /**
     * @author Tzj
     * 获取所有管理员
     */
    List<AdminDO> getAllAdmins();

    /**
     * @author Tzj
     * 根据机构ID获取管理员列表
     */
    List<AdminDO> getAdminsBySchoolId(Integer schoolId, String userName);

    /**
     * @author Tzj
     * 检查当前管理员合约是否到期
     */
    void checkContract(AdminDO adminDO) throws BusinessException;
}
