package com.youjiao.demo.controller.viewobject.admin;

import java.sql.Date;

/**
 * @author Kny
 * 2019/03/24
 */
public class AdminStudentListVO {
    private Integer studentId;

    private String name;

    private Date birthday;

    private Boolean sex;

    public AdminStudentListVO(Integer studentId, String name, Date birthday, Boolean sex) {
        this.studentId = studentId;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
