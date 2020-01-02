package com.example.springbootquickstart2.entity;

import javax.persistence.*;

@Entity
@Table(name="pic")
public class picEntity {
    @Id//主键
    @GeneratedValue(strategy= GenerationType.IDENTITY)//自增主键
    private Integer id;
    @Column(name="picname",length = 50)
    private  String picname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column
    private String size;


}
