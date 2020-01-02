package com.youjiao.demo.service.model;


import javax.validation.constraints.NotNull;

/**
 * @author Ck
 * #date 2019/05/13 12:41
 * 日程表row数据，monday - friday
 */
public class AdminScheduleListMO {
    private Integer scheduleId;
    @NotNull
    private Integer number;
    @NotNull
    private ScheduleMO monday;
    @NotNull
    private ScheduleMO tuesday;
    @NotNull
    private ScheduleMO wednesday;
    @NotNull
    private ScheduleMO thursday;
    @NotNull
    private ScheduleMO friday;

    public AdminScheduleListMO(Integer scheduleId,
                               @NotNull Integer number,
                               @NotNull ScheduleMO monday,
                               @NotNull ScheduleMO tuesday,
                               @NotNull ScheduleMO wednesday,
                               @NotNull ScheduleMO thursday,
                               @NotNull ScheduleMO friday) {
        this.scheduleId = scheduleId;
        this.number = number;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ScheduleMO getMonday() {
        return monday;
    }

    public void setMonday(ScheduleMO monday) {
        this.monday = monday;
    }

    public ScheduleMO getTuesday() {
        return tuesday;
    }

    public void setTuesday(ScheduleMO tuesday) {
        this.tuesday = tuesday;
    }

    public ScheduleMO getWednesday() {
        return wednesday;
    }

    public void setWednesday(ScheduleMO wednesday) {
        this.wednesday = wednesday;
    }

    public ScheduleMO getThursday() {
        return thursday;
    }

    public void setThursday(ScheduleMO thursday) {
        this.thursday = thursday;
    }

    public ScheduleMO getFriday() {
        return friday;
    }

    public void setFriday(ScheduleMO friday) {
        this.friday = friday;
    }
}
