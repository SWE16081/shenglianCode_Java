package com.youjiao.demo.controller.viewobject.user;

import java.sql.Timestamp;

/**
 * @author Zjp
 * 2019/03/10
 * 返回给前端的作业信息
 */
public class HomeworkParentVO {
    private Integer homeworkId;//作业id

    private Boolean type;//作业类型

    private String title;//作业标题

    private String content;//作业内容

    private Timestamp modifyTime;//修改时间

    private Timestamp finishTime;//更新时间

    private Integer fileId;//指定作业id和学生id时，作业提交记录的id，用于后续提交作业功能

    private Boolean isCommitted;//该学生是否已经提交了该作业

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
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
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Boolean getCommitted() {
        return isCommitted;
    }

    public void setCommitted(Boolean committed) {
        isCommitted = committed;
    }
}
