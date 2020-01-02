package com.youjiao.demo.controller.viewobject.admin;

/**
 * @author Kny
 * 2019/03/25
 */
public class AdminClassTeacherListVO {
    private Integer teacherId;

    private String teacherCode;

    private String name;

    private Boolean sex;

    public AdminClassTeacherListVO(Integer teacherId, String teacherCode, String name, Boolean sex) {
        this.teacherId = teacherId;
        this.teacherCode = teacherCode;
        this.name = name;
        this.sex = sex;
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
}
