package com.youjiao.demo.controller.viewobject.user;

/**
 * @author Kny
 * 2019/03/07 22:17
 * 父母列表信息
 */
public class ParentListVO {
    private String name;
    private Boolean sex;
    private String telephone;

    public ParentListVO(String name, Boolean sex, String telephone) {
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
