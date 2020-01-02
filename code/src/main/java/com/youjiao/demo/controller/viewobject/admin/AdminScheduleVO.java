package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Ck
 * #date 2019/04/16 13:28
 * 单个课程计划表内的数据
 */
public class AdminScheduleVO {
    @NotNull
    private Integer scheduleId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date schooltime;

    @NotNull
    private Byte number;

    private Integer activityId;

    @NotNull
    private Byte type;

    @NotNull
    private Integer termId;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getSchooltime() {
        return schooltime;
    }

    public void setSchooltime(Date schooltime) {
        this.schooltime = schooltime;
    }

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    @Override
    public String toString() {
        return "AdminScheduleVO{" +
                "scheduleId=" + scheduleId +
                ", schooltime=" + schooltime +
                ", number=" + number +
                ", activityId=" + activityId +
                ", type=" + type +
                ", termId=" + termId +
                '}';
    }
}
