package com.example.springbootquickstart2.service;



import com.example.springbootquickstart2.entity.BookEntity;

import java.util.List;

public interface BookService {
   //查询所有信息
    List<BookEntity> findAllDataSe();
    //按单个条件查询
//    List<?>findByOneSe(Object name,Object value);
    List<BookEntity>findByOneSe2(Object name);
    String test();
    BookEntity select();

}

