package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Lyy
 * #date 2019/03/24 15:22
 */
public class AdminStudentInfoVO {
    @NotNull(message = "入学年级不能为空")
    private Byte startGrade;
    @NotEmpty(message = "学生姓名不能为空")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "出生日期不能为空")
    private Date birthday;
    //@NotNull(message = "家长关系不能为空")
    private Boolean pSex;
    @NotEmpty(message = "学生身份证号不能为空")
    private String idNumber;
    //@NotEmpty(message = "工作不能为空")
    private String job;
    //@NotEmpty(message = "住址不能为空")
    private String address;
    //@NotEmpty(message = "手机号不能为空")
    private String telephone;
    @NotNull(message = "学生性别不能为空")
    private Boolean sSex;
    @NotNull(message = "学生id不能为空")
    private Integer studentId;
    //@NotNull(message = "家长id不能为空")
    private Integer parentId;
    @NotEmpty(message = "班级名称不能为空")
    private String className;
    //@NotEmpty(message = "家长姓名不能为空")
    private String pname;
    @NotEmpty(message = "机构名称不能为空")
    private String agentName;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Byte getStartGrade() {
        return startGrade;
    }

    public void setStartGrade(Byte startGrade) {
        this.startGrade = startGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getpSex() {
        return pSex;
    }

    public void setpSex(Boolean pSex) {
        this.pSex = pSex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public Boolean getsSex() {
        return sSex;
    }

    public void setsSex(Boolean sSex) {
        this.sSex = sSex;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
