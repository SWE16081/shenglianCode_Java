package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

public class AdminDishesAddVO {
    @NotNull(message = "时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    @NotEmpty(message = "早餐列表不能为空")
    private List<Integer> breakDishId;
    @NotEmpty(message = "午餐列表不能为空")
    private List<Integer> lunchDishId;
    @NotEmpty(message = "午点列表不能为空")
    private List<Integer> noonDishId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Integer> getBreakDishId() {
        return breakDishId;
    }

    public void setBreakDishId(List<Integer> breakDishId) {
        this.breakDishId = breakDishId;
    }

    public List<Integer> getLunchDishId() {
        return lunchDishId;
    }

    public void setLunchDishId(List<Integer> lunchDishId) {
        this.lunchDishId = lunchDishId;
    }

    public List<Integer> getNoonDishId() {
        return noonDishId;
    }

    public void setNoonDishId(List<Integer> noonDishId) {
        this.noonDishId = noonDishId;
    }
}
