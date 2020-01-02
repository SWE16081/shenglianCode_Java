package com.youjiao.demo.controller.viewobject.admin;

import com.youjiao.demo.dataobject.SecondClassificationDO;

import java.util.List;

public class AdminClassifyVO1 {

    private Integer firstId;//一级分类id
    private String name;
    private List<SecondClassificationDO> secondClassificationDOList;

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecondClassificationDO> getSecondClassificationDOList() {
        return secondClassificationDOList;
    }

    public void setSecondClassificationDOList(List<SecondClassificationDO> secondClassificationDOList) {
        this.secondClassificationDOList = secondClassificationDOList;
    }
}
