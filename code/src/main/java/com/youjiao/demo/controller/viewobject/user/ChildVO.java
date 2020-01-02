package com.youjiao.demo.controller.viewobject.user;

/**
 * @author Tzj
 * 2019/03/07 20:27
 */

public class ChildVO {
    private Integer studentId;
    private String name;
    private Integer classId;
    private Byte grade;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }
}
