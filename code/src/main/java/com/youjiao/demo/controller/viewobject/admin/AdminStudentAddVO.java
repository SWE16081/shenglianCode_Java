package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Lyy
 * #date 2019/04/17 22:27
 */
public class AdminStudentAddVO {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "出生日期不能为空")
    private Date birthday;
    @NotEmpty(message = "身份证号不能为空")
    private String idNumber;
    @NotNull(message = "孩子性别不能为空")
    private Boolean sSex;
    @NotEmpty(message = "孩子姓名不能为空")
    private String name;
    @NotNull(message = "入学年级不能为空")
    private Byte startGrade;
//    @NotEmpty(message = "手机号不能为空")
//    private String telephone;
//    @NotNull(message = "家长关系不能为空")
//    private Boolean pSex;
//    @NotEmpty(message = "工作不能为空")
//    private String job;
//    @NotEmpty(message = "住址不能为空")
//    private String address;
//    @NotEmpty(message = "父母姓名不能为空")
//    private String parentName;

    private Integer classId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Boolean getsSex() {
        return sSex;
    }

    public void setsSex(Boolean sSex) {
        this.sSex = sSex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStartGrade() {
        return startGrade;
    }

    public void setStartGrade(Byte startGrade) {
        this.startGrade = startGrade;
    }

//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public Boolean getpSex() {
//        return pSex;
//    }
//
//    public void setpSex(Boolean pSex) {
//        this.pSex = pSex;
//    }
//
//    public String getJob() {
//        return job;
//    }
//
//    public void setJob(String job) {
//        this.job = job;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getParentName() {
//        return parentName;
//    }
//
//    public void setParentName(String parentName) {
//        this.parentName = parentName;
//    }


}
