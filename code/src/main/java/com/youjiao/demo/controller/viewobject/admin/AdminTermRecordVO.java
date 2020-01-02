package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotNull;

/**
 * @author Ck
 * #date 2019/04/14 21:19
 */
public class AdminTermRecordVO {

    private Integer termId;
    @NotNull
    private Integer year;

    @NotNull
    private Byte termNum;

    @NotNull
    private Byte grade;

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Byte getTermNum() {
        return termNum;
    }

    public void setTermNum(Byte termNum) {
        this.termNum = termNum;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "TermRecordDO{" +
                "termId=" + termId +
                ", year=" + year +
                ", termNum=" + termNum +
                ", grade=" + grade +
                '}';
    }
}
