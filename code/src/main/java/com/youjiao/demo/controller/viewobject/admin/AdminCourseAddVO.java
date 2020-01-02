package com.youjiao.demo.controller.viewobject.admin;


import java.util.List;

public class AdminCourseAddVO {
    //course表
    private String courseName;//课程名称
    private Boolean useTextbook;//是否使用教材
    private Integer firstId;//一级分类
    private Integer secondId;//二级分类

    private String bookName;//教材名
    private String publish;//出版社

    private List<AdminChapterAddVO> chapter;

    public List<AdminChapterAddVO> getChapter() {
        return chapter;
    }

    public void setChapter(List<AdminChapterAddVO> chapter) {
        this.chapter = chapter;
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

}
