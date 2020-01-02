package com.youjiao.demo.controller.viewobject.admin;

import java.sql.Date;

/**
 * @author WengWenxin
 * @date 2019/04/14 15:17
 */
public class AdminContractListVO {

    private Integer contractId;
    private Date startTime;
    private Date deadline;
    private Integer payment;
    private Integer paymentType;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
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

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
}
