package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Lyy
 * #date 2019/04/14 20:03
 * 获取对应机构名下的所有班级的列表，包括班级id和班级名称
 */
public class AdminTransactionRecordClassListVO {
    @NotNull(message = "班级id不允许空")
    private Integer classId;
    @NotEmpty(message = "班级名称不允许空")
    private String name;


    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
