package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Lyy
 * #Date 2019/05/31 12:52
 */
public class AdminParentModifyVO {
    @NotNull(message = "家长id不能为空")
    private Integer parentId;
    @NotEmpty(message = "父母姓名不能为空")
    private String parentName;
    @NotNull(message = "父母性别不能为空")
    private Boolean pSex;
    @NotEmpty(message = "父母工作不能为空")
    private String job;
    @NotEmpty(message = "地址不能为空")
    private String address;
    @NotEmpty(message = "手机号不能为空")
    private String telephone;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getpSex() {
        return pSex;
    }

    public void setpSex(Boolean pSex) {
        this.pSex = pSex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
