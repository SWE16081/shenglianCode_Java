package com.youjiao.demo.controller.viewobject.user;

import com.youjiao.demo.controller.viewobject.admin.AdminScheduleListVO;

/**
 * @author Ck
 * #date 2019/07/14 13:56
 */
public class ScheduleRowVO {
    private AdminScheduleListVO[] week;

    public ScheduleRowVO() {
        week = new AdminScheduleListVO[5];
    }

    public AdminScheduleListVO[] getWeek() {
        return week;
    }

    public void setWeek(AdminScheduleListVO[] week) {
        this.week = week;
    }

    public void setWeekArray(int i, AdminScheduleListVO vo) {
        week[i] = vo;
    }
}