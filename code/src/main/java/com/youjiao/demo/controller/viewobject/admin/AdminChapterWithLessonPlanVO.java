package com.youjiao.demo.controller.viewobject.admin;

public class AdminChapterWithLessonPlanVO {
    private Integer chapterId;//章节id
    private Byte number;//章节序号
    private String name;
    private String description;
    private Integer lessonPlanId;
    private String fileUrl;
    private String filename;

    public Integer getLessonPlanId() {
        return lessonPlanId;
    }

    public void setLessonPlanId(Integer lessonPlanId) {
        this.lessonPlanId = lessonPlanId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Byte getNumber() {
        return number;
    }

    public void setNumber(Byte number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
