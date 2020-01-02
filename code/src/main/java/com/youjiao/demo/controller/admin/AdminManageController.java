package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.dataobject.AdminDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminManageService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MySessionUtil;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tzj
 * #date 2019/04/11 8:32
 */

@RequestMapping("/admin/adminManage")
@RestController
public class AdminManageController extends BaseController {

    private final AdminManageService adminManageService;

    public AdminManageController(AdminManageService adminManageService) {
        this.adminManageService = adminManageService;
    }

    /**
     * @author Tzj
     * 总管理员添加其他管理员
     */
    @PostMapping(value = "/addManage")
    public CommonReturnType addManage(@RequestParam("schoolId") Integer schoolId,
                                      @RequestParam("userName") String userName,
                                      @RequestParam("role") Integer role) throws BusinessException {
        //检查参数是否为空
        MyValidation.checkIntNull(schoolId, role);
        MyValidation.checkStrNull(userName);
        //从session里取出当前用户的权限
        byte userRole = (byte) MySessionUtil.getAttribute(Constants.JURISDICTION_SESSION);
        //若为园长或者代理商园长则不让其添加其他机构的管理员
        if ((userRole == Constants.ADMIN_JURISDICTION_MIDDLE || userRole == Constants.AGENTS_JURISDICTION_TOP)
                && !schoolId.equals(MySessionUtil.getAttribute(Constants.SCHOOL_ID_SESSION))) {
            throw new BusinessException(EmBusinessErr.NOT_PERMISSION);
        }
        //执行添加管理员操作
        adminManageService.addManage(schoolId, userName, role);
        return CommonReturnType.create(null);
    }


    /**
     * @author Tzj
     * 总管理员重置其他管理员密码接口
     */
    @PostMapping(value = "/resetPassword")
    public CommonReturnType resetPassword(@RequestParam("name") String name) throws BusinessException {
        //从session里取出当前用户的权限
        byte userRole = (byte) MySessionUtil.getAttribute(Constants.JURISDICTION_SESSION);
        Integer schoolId = (Integer) MySessionUtil.getAttribute(Constants.SCHOOL_ID_SESSION);
        //根据用户名获取用户实体
        AdminDO adminDO = adminManageService.findByName(name);
        if (adminDO == null) {
            throw new BusinessException(EmBusinessErr.USER_NOT_EXIST);
        }
        //执行重置密码操作
        if (userRole == Constants.ADMIN_JURISDICTION_TOP) {
            //若为管理员无条件重置密码
            adminManageService.resetPassword(adminDO.getAdminId());
        } else if (schoolId.equals(adminDO.getSchoolId()) &&
                (userRole == Constants.ADMIN_JURISDICTION_MIDDLE || userRole == Constants.AGENTS_JURISDICTION_TOP)) {
            //代理商只能重置自己机构管理员的密码
            adminManageService.resetPassword(adminDO.getAdminId());
        } else {
            //普通管理员无权限重置密码
            throw new BusinessException(EmBusinessErr.NOT_PERMISSION);
        }
        return CommonReturnType.create(null);
    }

    /**
     * @author Tzj
     * 总管理员删除其他管理员
     */
    @PostMapping(value = "/deleteManage")
    public CommonReturnType deleteManage(@RequestBody List<String> names) throws BusinessException {
        //从session里取出当前用户的权限
        byte userRole = (byte) MySessionUtil.getAttribute(Constants.JURISDICTION_SESSION);
        for (String name : names) {
            //根据姓名取出管理员实体
            AdminDO adminDO = adminManageService.findByName(name);
            MyValidation.checkObjectNull(EmBusinessErr.USER_NOT_EXIST, adminDO);
            //判断是否为超级管理员
            if (userRole == Constants.ADMIN_JURISDICTION_TOP) {
                //删除管理员
                adminManageService.deleteManage(adminDO.getAdminId());
            } else {
                Integer schoolId = (Integer) MySessionUtil.getAttribute(Constants.SCHOOL_ID_SESSION);
                //判断删除的管理员是否为同一机构的
                if (!schoolId.equals(adminDO.getSchoolId())) {
                    throw new BusinessException(EmBusinessErr.NOT_PERMISSION);
                } else {
                    adminManageService.deleteManage(adminDO.getAdminId());
                }
            }
        }
        return CommonReturnType.create(null);
    }

    /**
     * @author Tzj
     * 个人信息修改
     */
    @PostMapping(value = "/modifyInfo")
    public CommonReturnType modifyInfo(@RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("password") String password)
            throws BusinessException {
        //从session里取出当前用户的原本用户名
        String name = MySessionUtil.getAttribute(Constants.USER_NAME_SESSION).toString();
        if (name == null) {
            throw new BusinessException(EmBusinessErr.NOT_PERMISSION);
        }
        //修改个人信息
        adminManageService.modifyInfo(name, oldPassword, password);
        return CommonReturnType.create(null);
    }


    /**
     * @author Tzj
     * 获取管理员列表
     */
    @GetMapping(value = "/getAdmins")
    public CommonReturnType getAdmins() throws BusinessException {
        //从session里取出当前用户的权限
        byte userRole = (byte) MySessionUtil.getAttribute(Constants.JURISDICTION_SESSION);
        if (userRole == Constants.ADMIN_JURISDICTION_TOP) {
            //获取所有非超级管理员信息
            List<AdminDO> adminDOList = adminManageService.getAllAdmins();
            return CommonReturnType.create(adminDOList);
        } else {
            //根据机构id获取管理员列表
            List<AdminDO> adminDOList = adminManageService.getAdminsBySchoolId((Integer) MySessionUtil.getAttribute(Constants.SCHOOL_ID_SESSION),
                    MySessionUtil.getAttribute(Constants.USER_NAME_SESSION).toString().trim());
            return CommonReturnType.create(adminDOList);
        }

    }
}
