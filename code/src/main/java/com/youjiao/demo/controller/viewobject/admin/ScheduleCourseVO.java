package com.youjiao.demo.controller.viewobject.admin;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/14 22:53
 */
public class ScheduleCourseVO {

    private int id;
    private String name;
    private List<ScheduleChapterVO> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduleChapterVO> getChildren() {
        return children;
    }

    public void setChildren(List<ScheduleChapterVO> children) {
        this.children = children;
    }
}