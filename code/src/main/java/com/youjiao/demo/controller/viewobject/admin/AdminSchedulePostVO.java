package com.youjiao.demo.controller.viewobject.admin;

import com.youjiao.demo.service.model.AdminScheduleListMO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ck
 * #date 2019/05/13 12:38
 * post请求使用的表单实体, 用于接收前端post的添加、修改的数据VO
 */
public class AdminSchedulePostVO {
    @NotNull
    private Integer termId;

    @NotNull
    private List<AdminScheduleListMO> scheduleForm;

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public List<AdminScheduleListMO> getScheduleForm() {
        return scheduleForm;
    }

    public void setScheduleForm(List<AdminScheduleListMO> scheduleForm) {
        this.scheduleForm = scheduleForm;
    }
}
