package com.youjiao.demo.controller.viewobject.admin;

/**
 * @author Ck
 * #date 2019/04/16 13:40
 */
public class AdminChapterAndCourseVO {

    private Integer chapterId;

    private Integer courseId;

    //章节序号
    private Byte number;
    //章节名
    private String name;
    //章节描述
    private String description;
    //课程名
    private String courseName;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
