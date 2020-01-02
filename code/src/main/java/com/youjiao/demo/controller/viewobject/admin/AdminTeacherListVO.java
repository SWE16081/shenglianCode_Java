package com.youjiao.demo.controller.viewobject.admin;

import java.sql.Date;

/**
 * @author Kny
 * 2019/04/13
 */
public class AdminTeacherListVO {
    private Integer teacherId;

    private String teacherCode;

    private String name;

    private Boolean sex;

    private String idNumber;

    private String telephone;

    private Date birthday;

    private Byte position;

    private String education;

    private String major;

    private String email;

    private String schoolName;

    public AdminTeacherListVO(Integer teacherId, String teacherCode, String name, Boolean sex, String idNumber, String telephone, Date birthday, Byte position, String education, String major, String email, String schoolName) {
        this.teacherId = teacherId;
        this.teacherCode = teacherCode;
        this.name = name;
        this.sex = sex;
        this.idNumber = idNumber;
        this.telephone = telephone;
        this.birthday = birthday;
        this.position = position;
        this.education = education;
        this.major = major;
        this.email = email;
        this.schoolName = schoolName;
    }

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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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

    @Override
    public String toString() {
        return "AdminTeacherListVO{" +
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
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
