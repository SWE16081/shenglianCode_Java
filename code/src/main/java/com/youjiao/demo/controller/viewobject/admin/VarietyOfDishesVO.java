package com.youjiao.demo.controller.viewobject.admin;

/**
 * @aduthor WengWenxin
 * @date 2019/03/24 14:30
 */
public class VarietyOfDishesVO {

    private Integer dishId;
    private String name;
    private String material;
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

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

}
