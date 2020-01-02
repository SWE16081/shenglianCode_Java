package com.youjiao.demo.service.model;

public class CourseModel {

    //course表
    private Integer courseId;//课程id
    private String courseName;//课程名称
    private Boolean useTextbook;//是否使用教材
    private Integer firstId;//一级分类
    private Integer secondId;//二级分类

    //一级分类表和二级分类表
    private String first_name;//一级分类名称
    private String second_name;//二级分类名称

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
        this.courseName = courseName;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }
}
