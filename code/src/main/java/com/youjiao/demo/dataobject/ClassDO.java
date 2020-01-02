package com.youjiao.demo.dataobject;

import java.sql.Timestamp;

public class ClassDO {
    private Integer classId;

    private Integer teacherId;

    private Integer schoolId;

    private String name;

    private Timestamp createTime;

    private Boolean isArchive;

    private Boolean alive;

    private Integer termId;

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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
	}

    @Override
    public String toString() {
        return "ClassDO{" +
                "classId=" + classId +
                ", teacherId=" + teacherId +
                ", schoolId=" + schoolId +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", isArchive=" + isArchive +
                '}';
    }
}