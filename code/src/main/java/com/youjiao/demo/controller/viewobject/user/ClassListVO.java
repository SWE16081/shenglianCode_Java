package com.youjiao.demo.controller.viewobject.user;

/**
 * @author Ck
 * #Date 2019/03/02 13:57
 * 教师端班级列表VO
 */
public class ClassListVO {
    private Integer classId;

    private String className;

    private Byte grade;

    public ClassListVO(Integer classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }
}
