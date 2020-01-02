package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ck
 * #date 2019/03/26 13:14
 */
public class AdminClassModifyVO {
    @NotNull
    private AdminClassInfoVO classInfo;

    @NotNull
    private List<Integer> deleteStudentIdList;

    @NotNull
    private List<Integer> addStudentIdList;

    @NotNull
    private List<Integer> deleteTeacherIdList;

    @NotNull
    private List<Integer> addTeacherIdList;

    public AdminClassInfoVO getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(AdminClassInfoVO classInfo) {
        this.classInfo = classInfo;
    }

    public List<Integer> getDeleteStudentIdList() {
        return deleteStudentIdList;
    }

    public void setDeleteStudentIdList(List<Integer> deleteStudentIdList) {
        this.deleteStudentIdList = deleteStudentIdList;
    }

    public List<Integer> getAddStudentIdList() {
        return addStudentIdList;
    }

    public void setAddStudentIdList(List<Integer> addStudentIdList) {
        this.addStudentIdList = addStudentIdList;
    }

    public List<Integer> getDeleteTeacherIdList() {
        return deleteTeacherIdList;
    }

    public void setDeleteTeacherIdList(List<Integer> deleteTeacherIdList) {
        this.deleteTeacherIdList = deleteTeacherIdList;
    }

    public List<Integer> getAddTeacherIdList() {
        return addTeacherIdList;
    }

    public void setAddTeacherIdList(List<Integer> addTeacherIdList) {
        this.addTeacherIdList = addTeacherIdList;
    }

    @Override
    public String toString() {
        return "AdminClassModifyVO{" +
                "classInfo=" + classInfo +
                ", deleteStudentIdList=" + deleteStudentIdList +
                ", addStudentIdList=" + addStudentIdList +
                ", deleteTeacherIdList=" + deleteTeacherIdList +
                ", addTeacherIdList=" + addTeacherIdList +
                '}';
    }
}
