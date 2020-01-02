package com.youjiao.demo.controller.viewobject.admin;

import java.sql.Date;
import java.util.List;
/**
 * @author CainMJ
 */
public class RecordDishesListGetVO {
    private Date orderTime;
    private List<String> breakfast;
    private List<String> lunch;
    private List<String> noon;
    private List<Integer> breakfastId;
    private List<Integer> lunchId;
    private List<Integer> noonId;


    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public List<String> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<String> breakfast) {
        this.breakfast = breakfast;
    }

    public List<String> getLunch() {
        return lunch;
    }

    public void setLunch(List<String> lunch) {
        this.lunch = lunch;
    }

    public List<String> getNoon() {
        return noon;
    }

    public void setNoon(List<String> noon) {
        this.noon = noon;
    }

    public List<Integer> getBreakfastId() {
        return breakfastId;
    }

    public void setBreakfastId(List<Integer> breakfastId) {
        this.breakfastId = breakfastId;
    }

    public List<Integer> getLunchId() {
        return lunchId;
    }

    public void setLunchId(List<Integer> lunchId) {
        this.lunchId = lunchId;
    }

    public List<Integer> getNoonId() {
        return noonId;
    }

    public void setNoonId(List<Integer> noonId) {
        this.noonId = noonId;
    }
}
