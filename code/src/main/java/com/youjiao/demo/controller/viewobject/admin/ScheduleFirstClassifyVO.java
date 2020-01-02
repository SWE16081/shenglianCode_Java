package com.youjiao.demo.controller.viewobject.admin;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/14 13:01
 */
public class ScheduleFirstClassifyVO {

    private int id;
    private String name;
    private List<ScheduleSecondClassifyVO> children;

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

    public List<ScheduleSecondClassifyVO> getChildren() {
        return children;
    }

    public void setChildren(List<ScheduleSecondClassifyVO> children) {
        this.children = children;
    }
}
