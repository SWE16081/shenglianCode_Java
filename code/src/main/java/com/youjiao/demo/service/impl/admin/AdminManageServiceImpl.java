package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.dao.AdminDOMapper;
import com.youjiao.demo.dao.AgencyContractDOMapper;
import com.youjiao.demo.dao.AgentDOMapper;
import com.youjiao.demo.dataobject.AdminDO;
import com.youjiao.demo.dataobject.AgentDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminManageService;
import com.youjiao.demo.util.MD5Util;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AdminManageServiceImpl implements AdminManageService {

    private final AdminDOMapper adminDOMapper;
    private final AgentDOMapper agentDOMapper;


    @Autowired
    public AdminManageServiceImpl(AdminDOMapper adminDOMapper, AgentDOMapper agentDOMapper) {
        this.adminDOMapper = adminDOMapper;
        this.agentDOMapper = agentDOMapper;
    }

    /**
     * @author Tzj
     * 根据管理员的姓名和密码查询管理员
     */
    @Override
    public AdminDO findByNameAndPassword(String name, String password) {
        return adminDOMapper.findByNameAndPassword(name, password);
    }

    /**
     * @author Tzj
     * 根据用户名查找管理员实体
     */
    @Override
    public AdminDO findByName(String name) {
        return adminDOMapper.findByName(name);
    }


    /**
     * @author Tzj
     * 添加管理员实体
     */
    @Override
    public void addManage(Integer schoolId, String name, Integer role) throws BusinessException {
        //根据用户名查找管理员实体
        AdminDO adminDO = adminDOMapper.findByName(name);
        if (adminDO != null) {
            throw new BusinessException(EmBusinessErr.USER_ALREADY_EXIST);
        }
        adminDOMapper.myInsert(name, MD5Util.getMD5(name), schoolId, role);
    }

    /**
     * @author Tzj
     * 重置管理员密码
     */
    @Override
    public void resetPassword(Integer adminId) throws BusinessException {
        //判断该管理员是否存在
        AdminDO adminDO = adminDOMapper.selectById(adminId);
        MyValidation.checkObjectNull(EmBusinessErr.USER_NOT_EXIST, adminDO);
        adminDOMapper.resetPassword(MD5Util.getMD5(adminDO.getName()), adminId);
    }


    /**
     * @author Tzj
     * 删除管理员
     */
    @Override
    public void deleteManage(Integer userName) throws BusinessException {
        //根据用户名查找管理员实体
        AdminDO adminDO = adminDOMapper.selectById(userName);
        MyValidation.checkObjectNull(EmBusinessErr.USER_NOT_EXIST, adminDO);
        //删除该管理员
        adminDOMapper.deleteByAdminId(adminDO.getAdminId());
    }

    /**
     * @author Tzj
     * 修改密码
     */
    @Override
    public void modifyInfo(String name, String oldPassword, String password) throws BusinessException {
        //根据用户名查找管理员实体
        AdminDO adminDO = adminDOMapper.findByName(name);
        MyValidation.checkObjectNull(EmBusinessErr.USER_NOT_EXIST, adminDO);
        if (!MD5Util.getMD5(oldPassword).equals(adminDO.getPassword())) {
            throw new BusinessException(EmBusinessErr.USER_OR_PASSWORD_WRONG);
        }
        //修改个人信息
        adminDOMapper.modifyInfo(adminDO.getAdminId(), MD5Util.getMD5(password));
    }


    /**
     * @author Tzj
     * 获取所有管理员
     */
    @Override
    public List<AdminDO> getAllAdmins() {
        return adminDOMapper.getAllAdmin();
    }

    /**
     * @author Tzj
     * 根据机构id获取管理员列表
     */
    @Override
    public List<AdminDO> getAdminsBySchoolId(Integer schoolId, String userName) {
        return adminDOMapper.getAdminBySchoolId(schoolId, userName);
    }

    /**
     * @author Tzj
     * 检查该机构合约是否到期
     */
    @Override
    public void checkContract(AdminDO adminDO) throws BusinessException {
        AgentDO agentDO =agentDOMapper.selectByPrimaryKey(adminDO.getSchoolId());
        Date now = new Date();
        if(now.after(agentDO.getDeadline())){
            throw new BusinessException(EmBusinessErr.CONTRACT_EXPIRED);
        }
    }


}
