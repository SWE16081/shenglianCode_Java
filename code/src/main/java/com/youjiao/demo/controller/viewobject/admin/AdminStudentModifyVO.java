package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Lyy
 * #date 2019/04/14 13:39
 */
public class AdminStudentModifyVO {
    @NotNull(message = "入学年级不能为空")
    private Byte startGrade;
    @NotEmpty(message = "学生姓名不能为空")
    private String name;
    //    @NotEmpty(message = "父母姓名不能为空")
//    private String parentName;
    @NotNull(message = "生日不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    //    @NotNull(message = "父亲性别不能为空")
//    private Boolean pSex;
    @NotEmpty(message = "身份证号不能为空")
    private String idNumber;
    //    @NotEmpty(message = "父母工作不能为空")
//    private String job;
//    @NotEmpty(message = "地址不能为空")
//    private String address;
//    @NotEmpty(message = "手机号不能为空")
//    private String telephone;
    @NotNull(message = "学生性别不能为空")
    private Boolean sSex;
    @NotNull(message = "学生id不能为空")
    private Integer studentId;

//    public String getParentName() {
//        return parentName;
//    }
//
//    public void setParentName(String parentName) {
//        this.parentName = parentName;
//    }

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

//    public Boolean getpSex() {
//        return pSex;
//    }
//
//    public void setpSex(Boolean pSex) {
//        this.pSex = pSex;
//    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
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
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }

    public Boolean getsSex() {
        return sSex;
    }

    public void setsSex(Boolean sSex) {
        this.sSex = sSex;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
