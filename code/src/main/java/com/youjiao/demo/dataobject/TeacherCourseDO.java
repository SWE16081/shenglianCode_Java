package com.youjiao.demo.dataobject;

public class TeacherCourseDO {
    private Integer id;

    private Integer teacherClassId;

    private Integer courseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherClassId() {
        return teacherClassId;
    }

    public void setTeacherClassId(Integer teacherClassId) {
        this.teacherClassId = teacherClassId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}