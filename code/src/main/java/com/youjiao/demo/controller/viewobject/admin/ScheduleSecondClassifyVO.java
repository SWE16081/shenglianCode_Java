package com.youjiao.demo.controller.viewobject.admin;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/14 22:53
 */
public class ScheduleSecondClassifyVO {

    private int id;
    private String name;
    private List<ScheduleCourseVO> children;

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

    public List<ScheduleCourseVO> getChildren() {
        return children;
    }

    public void setChildren(List<ScheduleCourseVO> children) {
        this.children = children;
    }


}