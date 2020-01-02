package com.youjiao.demo.controller.viewobject.admin;

/**
 * @author Kny
 * 2019/04/03
 */
public class AdminClassInfoVO {
    private Integer classId;

    private String name;

    private Integer teacherId;

    private Integer termId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    @Override
    public String toString() {
        return "AdminClassInfoVO{" +
                "classId=" + classId +
                ", name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", termId=" + termId +
                '}';
    }
}
