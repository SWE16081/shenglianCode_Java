package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ck
 * #date 2019/03/25 23:16
 */
public class AdminClassAddVO {
    @NotNull(message = "班级名称不能为空")
    private String name;

    @NotNull(message = "教师id不能为空")
    private Integer teacherId;

    @NotNull(message = "学期id不能为空")
    private Integer termId;

    @NotNull
    private List<Integer> studentIdList;

    @NotNull
    private List<Integer> teacherIdList;

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

    public List<Integer> getStudentIdList() {
        return studentIdList;
    }

    public void setStudentIdList(List<Integer> studentIdList) {
        this.studentIdList = studentIdList;
    }

    public List<Integer> getTeacherIdList() {
        return teacherIdList;
    }

    public void setTeacherIdList(List<Integer> teacherIdList) {
        this.teacherIdList = teacherIdList;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    @Override
    public String toString() {
        return "AdminClassAddVO{" +
                "name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", termId=" + termId +
                '}';
    }
}
