package com.youjiao.demo.service.model;

/**
 * @author Kny
 * 2019/03/04 10:20
 * 父母列表信息
 */
public class ParentListModel {
    private String studentName;
    private Boolean sex;
    private String telephone;

    public ParentListModel(String studentName, Boolean sex, String telephone) {
        this.studentName = studentName;
        this.sex = sex;
        this.telephone = telephone;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Boolean getSex() { return sex; }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
