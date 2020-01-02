package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AdminCourseAlterVO {

    private Integer courseId;//课程id
    private String courseName;//课程名称
    private Boolean useTextbook;//是否使用教材
    private Integer firstId;//一级分类
    private Integer secondId;//二级分类

    private Integer id;//教材id
    private String bookName;//教材名
    private String publish;//出版社

    private List<AdminChapterVO> chapter;

    public List<AdminChapterVO> getChapter() {
        return chapter;
    }

    public void setChapter(List<AdminChapterVO> chapter) {
        this.chapter = chapter;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

}
