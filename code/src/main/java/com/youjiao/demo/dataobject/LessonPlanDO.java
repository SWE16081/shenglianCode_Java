package com.youjiao.demo.dataobject;

import java.sql.Timestamp;

public class LessonPlanDO {
    private Integer lessonPlanId;

    private Integer chapterId;

    private String fileUrl;

    private Timestamp commitTime;

    private String filename;

    public Integer getLessonPlanId() {
        return lessonPlanId;
    }

    public void setLessonPlanId(Integer lessonPlanId) {
        this.lessonPlanId = lessonPlanId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }
}