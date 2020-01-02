package com.youjiao.demo.controller.viewobject.admin;

/**
 * @author Ck
 * #date 2019/05/13 23:08
 * 课程计划表 对应一行 number相同的一行
 */
public class AdminScheduleRowVO {
    private AdminScheduleListVO monday;
    private AdminScheduleListVO tuesday;
    private AdminScheduleListVO wednesday;
    private AdminScheduleListVO thursday;
    private AdminScheduleListVO friday;

    public AdminScheduleListVO getMonday() {
        return monday;
    }

    public void setMonday(AdminScheduleListVO monday) {
        this.monday = monday;
    }

    public AdminScheduleListVO getTuesday() {
        return tuesday;
    }

    public void setTuesday(AdminScheduleListVO tuesday) {
        this.tuesday = tuesday;
    }

    public AdminScheduleListVO getWednesday() {
        return wednesday;
    }

    public void setWednesday(AdminScheduleListVO wednesday) {
        this.wednesday = wednesday;
    }

    public AdminScheduleListVO getThursday() {
        return thursday;
    }

    public void setThursday(AdminScheduleListVO thursday) {
        this.thursday = thursday;
    }

    public AdminScheduleListVO getFriday() {
        return friday;
    }

    public void setFriday(AdminScheduleListVO friday) {
        this.friday = friday;
    }
}
