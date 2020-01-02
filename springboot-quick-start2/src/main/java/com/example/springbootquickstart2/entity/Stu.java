package com.example.springbootquickstart2.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="Stu")
public class Stu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentid;
    @Column
    private String gender;
    @Column
    private String studentName;
//        @ColumnDefault("测试字段")
    @Column(nullable = true,name="test_column")
    private String testcolumn;

//    @ColumnDefault("学生班级")
    @Column
    private String studentClass;
    @Column
    private Integer age;
    //@Generated(GenerationTime.INSERT)使用GenerationTime.INSERT，只会在SQL INSERT的时候出现，来获取数据库的默认值
//    @Temporal(TemporalType.TIMESTAMP)

    @CreationTimestamp
    private Date createdTime;

    @Column
    private  long updateTime=System.currentTimeMillis();

    public Integer getStudent() {
        return studentid;
    }

    public void setStudent(Integer student) {
        this.studentid = studentid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTestcolumn() {
        return testcolumn;
    }

    public void setTestcolumn(String testcolumn) {
        this.testcolumn = testcolumn;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
