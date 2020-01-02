package com.youjiao.demo.controller.viewobject.admin;

/**
 * @author Ck
 * #date 2019/04/04 12:25
 */
public class AdminClassModifyArchiveVO {
    private Integer classId;
    private Boolean isArchive;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    @Override
    public String toString() {
        return "AdminClassModifyArchiveVO{" +
                "classId=" + classId +
                ", isArchive=" + isArchive +
                '}';
    }
}
