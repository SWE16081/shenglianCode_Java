package com.youjiao.demo.controller.viewobject.user;

import java.sql.Timestamp;

/**
 * @author Ck
 * #date 2019/03/13 14:37
 */
public class HomeworkFileVO {
    private String title;
    //文件的base64编码
    private String file;
    private Timestamp commitTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
    }
}
