package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotNull;

/**
 * @Author WengWenxin
 * @Date 2019/04/12 19:41
 */
public class AdminDishAddVO {
     @NotNull
     private String name;
    @NotNull
     private String material;
    @NotNull
     private String img;

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
}
