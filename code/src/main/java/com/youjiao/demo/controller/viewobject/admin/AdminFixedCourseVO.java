package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotNull;

/**
 * @author Ck
 * #date 2019/04/14 16:37
 */
public class AdminFixedCourseVO {
    @NotNull
    private Integer fixedCourseId;

    @NotNull
    private String name;

    public Integer getFixedCourseId() {
        return fixedCourseId;
    }

    public void setFixedCourseId(Integer fixedCourseId) {
        this.fixedCourseId = fixedCourseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdminFixedCourseVO{" +
                "fixedClassId=" + fixedCourseId +
                ", name='" + name + '\'' +
                '}';
    }
}
