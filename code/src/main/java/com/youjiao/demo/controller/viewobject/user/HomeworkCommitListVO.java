package com.youjiao.demo.controller.viewobject.user;

import java.sql.Timestamp;

/**
 * @author Ck
 * #date 2019/03/13 13:22
 */
public class HomeworkCommitListVO {
    private Integer fileId;
    private String name;
    private Timestamp commitTime;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
    }
}
