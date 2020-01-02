package com.youjiao.demo.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

/**
 * @author Ck
 * #date 2019/05/13 13:31
 */
public class ScheduleMO {
    private Integer scheduleId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date schooltime;

    private Byte number;

    private Integer activityId;

    private Byte type;

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
        return "ScheduleDO{" +
                "scheduleId=" + scheduleId +
                ", schooltime=" + schooltime +
                ", number=" + number +
                ", activityId=" + activityId +
                ", type=" + type +
                ", termId=" + termId +
                '}';
    }
}
