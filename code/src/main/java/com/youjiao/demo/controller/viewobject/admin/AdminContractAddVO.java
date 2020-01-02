package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author WengWenxin
 * @date 2019/04/14 16:58
 */
public class AdminContractAddVO {
    @NotNull(message = "代理商不能为空")
    private Integer agentId;
    @NotNull(message = "起始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "截止时间不能为空")
    private Date deadline;
    @NotNull(message = "付款金额不能为空")
    private Integer payment;
    @NotNull(message = "付款类型不能为空")
    private Byte paymentType;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Byte getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Byte paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "AdminContractAddVO{" +
                "agentId='" + agentId + '\'' +
                ", startTime=" + startTime +
                ", deadline=" + deadline +
                ", payment=" + payment +
                ", paymentType=" + paymentType +
                '}';
    }
}
