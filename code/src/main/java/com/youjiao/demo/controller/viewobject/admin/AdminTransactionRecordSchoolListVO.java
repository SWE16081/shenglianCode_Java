package com.youjiao.demo.controller.viewobject.admin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Lyy
 * #date 2019/04/14 20:10
 * 包括机构id和机构名称
 */
public class AdminTransactionRecordSchoolListVO {
    @NotNull(message = "机构id不允许空")
    private Integer agentId;
    @NotEmpty(message = "机构名称不允许空")
    private String agentName;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
