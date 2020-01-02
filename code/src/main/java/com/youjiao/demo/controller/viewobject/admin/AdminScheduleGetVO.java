package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Ck
 * #date 2019/05/13 22:53
 */
public class AdminScheduleGetVO {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotNull
    private Integer termId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    @Override
    public String toString() {
        return "date=" + date + ", termId=" + termId;
    }
}
