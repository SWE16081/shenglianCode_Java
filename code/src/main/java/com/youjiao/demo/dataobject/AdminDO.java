package com.youjiao.demo.dataobject;

public class AdminDO {
    private Integer adminId;

    private Integer schoolId;

    private String name;

    private String password;

    private Byte jurisdiction;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Byte jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
}