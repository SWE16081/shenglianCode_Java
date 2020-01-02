package com.youjiao.demo.controller.viewobject.user;


import java.sql.Timestamp;

/**
 * @author Ck
 * #date 2019/03/12 22:20
 */
public class HomeworkInfoListVO {
    private Integer homeworkId;
    private Integer teacherId;
    private boolean type;
    private String title;
    private String content;
    private Timestamp createTime;
    private Timestamp finishTime;
    private Timestamp modifyTime;
    private Integer committedNum;
    private Integer uncommittedNum;
    private boolean isEditable;
    private boolean isEnd;

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getCommittedNum() {
        return committedNum;
    }

    public void setCommittedNum(Integer committedNum) {
        this.committedNum = committedNum;
    }

    public Integer getUncommittedNum() {
        return uncommittedNum;
    }

    public void setUncommittedNum(Integer uncommittedNum) {
        this.uncommittedNum = uncommittedNum;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HomeworkInfoListVO{" +
                "homeworkId=" + homeworkId +
                ", teacherId=" + teacherId +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", modifyTime=" + modifyTime +
                ", committedNum=" + committedNum +
                ", uncommittedNum=" + uncommittedNum +
                ", isEditable=" + isEditable +
                ", isEnd=" + isEnd +
                '}';
    }
}
