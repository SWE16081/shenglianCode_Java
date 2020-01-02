package com.youjiao.demo.dataobject;

import java.sql.Date;

public class StudentTransactionRecordDO {
    private Integer recordId;

    private Date payTime;

    private Boolean isNew;

    private String remark;

    private Byte protocolType;

    private String protocolId;

    private String receiptId;

    private String service;

    private Integer payment;

    private Date startTime;

    private Date deadline;

    private Byte paymentType;

    private Double capitationReceivable;

    private Double monthFee;

    private Byte monthReceive;

    private Byte paymentMode;

    private String sale;

    private Double receivablePerMonth;

    private Double receivedPerMonth;

    private Double receivableMonth;

    private Double receivedMonth;

    private Integer schoolId;

    private Integer studentId;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

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
        this.remark = remark == null ? null : remark.trim();
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
        this.protocolId = protocolId == null ? null : protocolId.trim();
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId == null ? null : receiptId.trim();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service == null ? null : service.trim();
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

    public Double getCapitationReceivable() {
        return capitationReceivable;
    }

    public void setCapitationReceivable(Double capitationReceivable) {
        this.capitationReceivable = capitationReceivable;
    }

    public Double getMonthFee() {
        return monthFee;
    }

    public void setMonthFee(Double monthFee) {
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
        this.sale = sale == null ? null : sale.trim();
    }

    public Double getReceivablePerMonth() {
        return receivablePerMonth;
    }

    public void setReceivablePerMonth(Double receivablePerMonth) {
        this.receivablePerMonth = receivablePerMonth;
    }

    public Double getReceivedPerMonth() {
        return receivedPerMonth;
    }

    public void setReceivedPerMonth(Double receivedPerMonth) {
        this.receivedPerMonth = receivedPerMonth;
    }

    public Double getReceivableMonth() {
        return receivableMonth;
    }

    public void setReceivableMonth(Double receivableMonth) {
        this.receivableMonth = receivableMonth;
    }

    public Double getReceivedMonth() {
        return receivedMonth;
    }

    public void setReceivedMonth(Double receivedMonth) {
        this.receivedMonth = receivedMonth;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}