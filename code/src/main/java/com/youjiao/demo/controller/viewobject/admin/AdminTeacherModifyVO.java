package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Kny
 * 2019/04/13
 */
public class AdminTeacherModifyVO {
    @NotNull(message = "教师id不能为空")
    private Integer teacherId;

    @NotNull(message = "教师编号不能为空")
	@NotEmpty(message = "教师编号不能为空")
    private String teacherCode;

    @NotNull(message = "姓名不能为空")
	@NotEmpty(message = "姓名不能为空")
    private String name;

    @NotNull(message = "性别不能为空")
    private Boolean sex;

    @NotNull(message = "身份证号不能为空")
    @NotEmpty(message = "身份证号不能为空")
    private String idNumber;

    @NotNull(message = "手机号不能为空")
    @NotEmpty(message = "手机号不能为空")
    private String telephone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private Byte position;

    private String education;
	
    private String major;

    private String email;

    @NotNull(message = "密码重置不能为空")
    private Boolean passwordReset;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Byte getPosition() {
        return position;
    }

    public void setPosition(Byte position) {
        this.position = position;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(Boolean passwordReset) {
        this.passwordReset = passwordReset;
    }

    @Override
    public String toString() {
        return "AdminTeacherModifyVO{" +
                "teacherId=" + teacherId +
                ", teacherCode='" + teacherCode + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", idNumber='" + idNumber + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", position=" + position +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", email='" + email + '\'' +
                ", passwordReset=" + passwordReset +
                '}';
    }
}
