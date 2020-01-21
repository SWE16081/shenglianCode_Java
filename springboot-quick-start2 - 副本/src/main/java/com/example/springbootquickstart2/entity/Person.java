package com.example.springbootquickstart2.entity;

import javax.persistence.*;

//使用jpa注解配置映射关系
@Entity//告诉jpa这是一个实体类(和数据表映射的类)
@Table(name = "person")//@Table指定数据数据表的名称,如果省略默认表明就是类名小写
public class Person {
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    private  Long id;

    @Column(length =50,name = "name")//和数据表对应的列
    private  String  name;
    @Column//省略默认列名就是属性名
    private  String email;

    private  String  gender;

    private  String  address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}



