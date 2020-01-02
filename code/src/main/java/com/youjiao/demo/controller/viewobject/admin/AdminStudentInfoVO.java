package com.youjiao.demo.controller.viewobject.admin;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Lyy
 * #date 2019/03/24 15:22
 */
public class AdminStudentInfoVO {
    @NotNull(message = "��ѧ�꼶����Ϊ��")
    private Byte startGrade;
    @NotEmpty(message = "ѧ����������Ϊ��")
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "�������ڲ���Ϊ��")
    private Date birthday;
    //@NotNull(message = "�ҳ���ϵ����Ϊ��")
    private Boolean pSex;
    @NotEmpty(message = "ѧ�����֤�Ų���Ϊ��")
    private String idNumber;
    //@NotEmpty(message = "��������Ϊ��")
    private String job;
    //@NotEmpty(message = "סַ����Ϊ��")
    private String address;
    //@NotEmpty(message = "�ֻ��Ų���Ϊ��")
    private String telephone;
    @NotNull(message = "ѧ���Ա���Ϊ��")
    private Boolean sSex;
    @NotNull(message = "ѧ��id����Ϊ��")
    private Integer studentId;
    //@NotNull(message = "�ҳ�id����Ϊ��")
    private Integer parentId;
    @NotEmpty(message = "�༶���Ʋ���Ϊ��")
    private String className;
    //@NotEmpty(message = "�ҳ���������Ϊ��")
    private String pname;
    @NotEmpty(message = "�������Ʋ���Ϊ��")
    private String agentName;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Byte getStartGrade() {
        return startGrade;
    }

    public void setStartGrade(Byte startGrade) {
        this.startGrade = startGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getpSex() {
        return pSex;
    }

    public void setpSex(Boolean pSex) {
        this.pSex = pSex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getsSex() {
        return sSex;
    }

    public void setsSex(Boolean sSex) {
        this.sSex = sSex;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
