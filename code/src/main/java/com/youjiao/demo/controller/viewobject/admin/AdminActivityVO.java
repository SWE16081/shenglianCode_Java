package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotNull;

/**
 * @author Ck
 * #date 2019/04/14 12:11
 */
public class AdminActivityVO {

    private Integer activityId;

    @NotNull
    private String name;

    private String description;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AdminActivityVO{" +
                "activityId=" + activityId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
