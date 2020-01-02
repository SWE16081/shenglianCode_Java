package com.youjiao.demo.controller.viewobject.user;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Kny
 * 2019/03/14
 */
public class HomeworkAddVO {
    @NotNull(message = "classId不能为null")
    private Integer classId;

    @NotNull(message = "type不能为null")
    private Boolean type;

    @NotBlank(message = "title不能为null")
    private String title;

    @NotBlank(message = "content不能为null")
    private String content;

    @Future(message = "finishTime必须为将来日期")
    @NotNull(message = "finishTime不能为null")
    private Timestamp finishTime;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }
}
