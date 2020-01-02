package com.youjiao.demo.dataobject;

public class FixedCourseDO {
    private Integer fixedCourseId;

    private String name;

    private Boolean alive;

    public Integer getFixedCourseId() {
        return fixedCourseId;
    }

    public void setFixedCourseId(Integer fixedCourseId) {
        this.fixedCourseId = fixedCourseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
}