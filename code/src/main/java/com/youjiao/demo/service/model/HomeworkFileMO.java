package com.youjiao.demo.service.model;

import java.sql.Timestamp;

/**
 * @author Ck
 * #date 2019/03/13 14:37
 */
public class HomeworkFileMO {
    private String title;
    private String url;
    private Timestamp commitTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
    }
}
