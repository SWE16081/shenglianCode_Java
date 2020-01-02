package com.youjiao.demo.controller.viewobject.admin;

import java.util.List;

/**
 * @author Ck
 * #date 2019/03/24 16:11
 */
public class AdminClassListVO {
    private Integer classId;

    private String className;

    private Integer teacherId;

    private String teacherName;

    private List<AdminClassTeacherListVO> teacherList;

    private List<AdminClassStudentListVO> studentList;

    private Boolean isArchive;

    private Integer termId;

    private Integer year;

    private Byte termNum;

    private Byte grade;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Boolean getArchive() {
        return isArchive;
    }

    public void setArchive(Boolean archive) {
        isArchive = archive;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<AdminClassTeacherListVO> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<AdminClassTeacherListVO> teacherList) {
        this.teacherList = teacherList;
    }

    public List<AdminClassStudentListVO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<AdminClassStudentListVO> studentList) {
        this.studentList = studentList;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Byte getTermNum() {
        return termNum;
    }

    public void setTermNum(Byte termNum) {
        this.termNum = termNum;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "AdminClassListVO{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherList=" + teacherList +
                ", studentList=" + studentList +
                ", isArchive=" + isArchive +
                '}';
    }
}
