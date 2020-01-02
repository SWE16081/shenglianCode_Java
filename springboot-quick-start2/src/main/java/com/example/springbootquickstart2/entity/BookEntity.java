package com.example.springbootquickstart2.entity;

import javax.persistence.*;

@Entity
@Table(name="book")
public class BookEntity {
    @Id//主键
    @GeneratedValue(strategy= GenerationType.IDENTITY)//自增主键
    private Integer id;
    @Column(name="bookName",length = 50)
    private  String bookName;
    @Column
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookName;
    }

    public void setBookname(String bookname) {
        this.bookName = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
