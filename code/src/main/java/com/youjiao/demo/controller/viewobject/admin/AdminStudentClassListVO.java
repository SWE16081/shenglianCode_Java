package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Lyy
 * #date 2019/04/13 16:53
 */
public class AdminStudentClassListVO {
    @NotNull(message = "�༶id����Ϊ��")
    private Integer classId;
    @NotEmpty(message = "�༶���Ʋ���Ϊ��")
    private String name;
    @NotNull(message = "�Ƿ�鵵����Ϊ��")
    private Boolean isArchive;
    @NotEmpty(message = "�����������������")
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

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
}
