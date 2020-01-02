package com.youjiao.demo.controller.viewobject.admin;

import com.youjiao.demo.dataobject.SecondClassificationDO;

import java.util.List;

public class AdminFirstClassifyVO {

    private Integer firstId;//一级分类id
    private String firstName;
    private List<SecondClassificationDO> secondClassificationDOList;

    public Integer getFirstId() {
        return firstId;
    }

    public void setFirstId(Integer firstId) {
        this.firstId = firstId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<SecondClassificationDO> getSecondClassificationDOList() {
        return secondClassificationDOList;
    }

    public void setSecondClassificationDOList(List<SecondClassificationDO> secondClassificationDOList) {
        this.secondClassificationDOList = secondClassificationDOList;
    }
}
