package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class AdminAgentAddVO {

    @NotEmpty(message = "机构名称不能为空")
    private String agentName;
    @NotEmpty(message = "代理人姓名不能为空")
    private String name;
    @NotNull(message = "代理级别不能为空")
    private Boolean level;
    @NotEmpty(message = "手机号不能为空")
    private String telephone;
    @NotEmpty(message = "机构所在地不能为空")
    private String location;
    @NotNull(message = "首次加入时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date firstTime;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLevel() {
        return level;
    }

    public void setLevel(Boolean level) {
        this.level = level;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }
}
