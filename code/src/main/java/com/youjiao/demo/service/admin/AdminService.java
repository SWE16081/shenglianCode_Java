package com.youjiao.demo.service.admin;

import com.youjiao.demo.dataobject.AdminDO;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    /**
     * @author Tzj
     * 按姓名查找管理员
     */
    AdminDO findByName(String name, String password);
}
