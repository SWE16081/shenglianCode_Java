package com.youjiao.demo.controller.viewobject.admin;

public class AdminChapterAddVO {

    private Byte number;//章节序号
    private String name;
    private String description;

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
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
}
