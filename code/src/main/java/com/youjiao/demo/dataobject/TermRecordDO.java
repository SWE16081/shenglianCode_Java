package com.youjiao.demo.dataobject;

public class TermRecordDO {
    private Integer termId;

    private Integer year;

    private Byte termNum;

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