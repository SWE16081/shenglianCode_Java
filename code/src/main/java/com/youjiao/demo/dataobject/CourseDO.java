package com.youjiao.demo.dataobject;

import javax.validation.constraints.NotNull;

public class CourseDO {
    @NotNull
    private Integer courseId;
    private String courseName;
    private Boolean useTextbook;
    private Integer firstId;
    private Integer secondId;
    private Boolean alive;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Boolean getUseTextbook() {
        return useTextbook;
    }

    public void setUseTextbook(Boolean useTextbook) {
        this.useTextbook = useTextbook;
    }

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public Integer getSecondId() {
        return secondId;
    }

    public void setSecondId(Integer secondId) {
        this.secondId = secondId;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }
}