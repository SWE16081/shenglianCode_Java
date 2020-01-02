package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotNull;

/**
 * @author Ck
 * #date 2019/04/16 13:36
 * 课程计划表最小子项
 */
public class AdminScheduleListVO {
    //课程计划表
    @NotNull
    private AdminScheduleVO schedule;

    //固定课程
    @NotNull
    private AdminFixedCourseVO fixedCourse;
    //课程
    @NotNull
    private AdminChapterAndCourseVO course;
    //活动
    @NotNull
    private AdminActivityVO activity;

    //默认构造函数
    public AdminScheduleListVO() {
        this.schedule = null;
        this.fixedCourse = null;
        this.course = null;
        this.activity = null;
    }
    //初始化课程计划VO （放假）
    public AdminScheduleListVO(AdminScheduleVO schedule) {
        this.schedule = schedule;
        this.fixedCourse = null;
        this.course = null;
        this.activity = null;
    }
    //初始化课程计划和固定课程VO
    public AdminScheduleListVO(AdminScheduleVO schedule, AdminFixedCourseVO fixedCourse) {
        this.schedule = schedule;
        this.fixedCourse = fixedCourse;
        this.course = null;
        activity = null;
    }
    //初始化课程计划和课程VO
    public AdminScheduleListVO(AdminScheduleVO schedule, AdminChapterAndCourseVO course) {
        this.schedule = schedule;
        this.course = course;
        this.fixedCourse = null;
        this.activity = null;
    }
    //初始化课程计划和活动VO
    public AdminScheduleListVO(AdminScheduleVO schedule, AdminActivityVO activity) {
        this.schedule = schedule;
        this.activity = activity;
        this.fixedCourse = null;
        this.course = null;
    }

    public AdminScheduleVO getSchedule() {
        return schedule;
    }

    public void setSchedule(AdminScheduleVO schedule) {
        this.schedule = schedule;
    }

    public AdminFixedCourseVO getFixedCourse() {
        return fixedCourse;
    }

    public void setFixedCourse(AdminFixedCourseVO fixedCourse) {
        this.fixedCourse = fixedCourse;
    }

    public AdminChapterAndCourseVO getCourse() {
        return course;
    }

    public void setCourse(AdminChapterAndCourseVO course) {
        this.course = course;
    }

    public AdminActivityVO getActivity() {
        return activity;
    }

    public void setActivity(AdminActivityVO activity) {
        this.activity = activity;
    }


}
