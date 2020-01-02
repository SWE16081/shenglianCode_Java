package com.youjiao.demo.controller.viewobject.user;

/**
 * @author Kny
 * 2019/03/07 22:39
 */
public class TeacherListVO {
    private Integer teacherId;
    private String name;
    private String telephone;
    private Boolean leader;

    public TeacherListVO(Integer teacherId, String name, String telephone) {
        this.teacherId = teacherId;
        this.name = name;
        this.telephone = telephone;
        this.leader = false;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getLeader() {
        return leader;
    }

    public void setLeader(Boolean leader) {
        this.leader = leader;
    }
}
