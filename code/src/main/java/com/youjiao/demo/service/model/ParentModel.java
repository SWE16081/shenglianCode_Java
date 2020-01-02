package com.youjiao.demo.service.model;

/**
 * @Author Kny
 * @Date 2019/03/04 10:20
 * 父母列表信息
 */
public class ParentModel {
    private String studentName;
    private String sex;
    private String telephone;

    public ParentModel(String studentName, String sex, String telephone) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
