package com.youjiao.demo.controller.viewobject.user;

import java.sql.Date;

/**
 * @author CaiMJ
 * #date 2019/03/09 21:37
 */
public class DishesVO {
    private String name;
    private String material;
    private String img;
    private String type;
    private Date orderTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
