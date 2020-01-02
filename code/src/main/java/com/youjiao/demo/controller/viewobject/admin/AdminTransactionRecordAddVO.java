package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Lyy
 * #date 2019/04/14 20:17
 */
public class AdminTransactionRecordAddVO {
    @NotNull(message = "交费时间不允许空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date payTime;
    @NotNull(message = "新增/续费状态不允许空")
    private Boolean isNew;
    //@NotEmpty(message = "备注不允许空")
    private String remark;
   // @NotNull(message = "协议类型不允许空")
    private Byte protocolType;
    //@NotEmpty(message = "协议编号不允许空")
    private String protocolId;
    //@NotEmpty(message = "收据编号不允许空")
    private String receiptId;
    @NotEmpty(message = "服务内容不允许空")
    private String service;
    @NotNull(message = "金额不允许空")
    private Integer payment;
    @NotNull(message = "开始时间不允许空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;
    @NotNull(message = "截止时间不允许空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deadline;
    @NotNull(message = "付费方式不允许空")
    private Byte paymentType;
    @NotNull(message = "月均摊应收不允许空")
    private double capitationReceivable;
    @NotNull(message = "本月消课费不允许空")
    private double monthFee;
    @NotNull(message = "几月收不允许空")
    private Byte monthReceive;
    @NotNull(message = "支付方式不允许空")
    private Byte paymentMode;
    //@NotEmpty(message = "销售人员不允许空")
    private String sale;
    //@NotNull(message = "本月均摊应收不允许空")
    private double receivablePerMonth;
    //@NotNull(message = "本月均摊已收不允许空")
    private double receivedPerMonth;
    //@NotNull(message = "本月应收不允许空")
    private double receivableMonth;
    //@NotNull(message = "本月已收不允许空")
    private double receivedMonth;
    @NotNull(message = "学生id不允许空")
    private Integer studentId;

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Byte protocolType) {
        this.protocolType = protocolType;
    }

    public String getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(String protocolId) {
        this.protocolId = protocolId;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
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

    public Byte getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Byte paymentType) {
        this.paymentType = paymentType;
    }

    public double getCapitationReceivable() {
        return capitationReceivable;
    }

    public void setCapitationReceivable(double capitationReceivable) {
        this.capitationReceivable = capitationReceivable;
    }

    public double getMonthFee() {
        return monthFee;
    }

    public void setMonthFee(double monthFee) {
        this.monthFee = monthFee;
    }

    public Byte getMonthReceive() {
        return monthReceive;
    }

    public void setMonthReceive(Byte monthReceive) {
        this.monthReceive = monthReceive;
    }

    public Byte getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Byte paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public double getReceivablePerMonth() {
        return receivablePerMonth;
    }

    public void setReceivablePerMonth(double receivablePerMonth) {
        this.receivablePerMonth = receivablePerMonth;
    }

    public double getReceivedPerMonth() {
        return receivedPerMonth;
    }

    public void setReceivedPerMonth(double receivedPerMonth) {
        this.receivedPerMonth = receivedPerMonth;
    }

    public double getReceivableMonth() {
        return receivableMonth;
    }

    public void setReceivableMonth(double receivableMonth) {
        this.receivableMonth = receivableMonth;
    }

    public double getReceivedMonth() {
        return receivedMonth;
    }

    public void setReceivedMonth(double receivedMonth) {
        this.receivedMonth = receivedMonth;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
