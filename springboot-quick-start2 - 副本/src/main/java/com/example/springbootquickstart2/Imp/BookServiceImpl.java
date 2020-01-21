package com.example.springbootquickstart2.Imp;

import com.example.springbootquickstart2.entity.BookEntity;
import com.example.springbootquickstart2.repository.BookRepository;
import com.example.springbootquickstart2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    //自动注入dao
    @Autowired
    BookRepository bookRepository;
    @Override
    public List<BookEntity> findAllDataSe() {
        return bookRepository.findAll();
    }

//    @Override
//    public List<?> findByOneSe(Object name, Object value) {
////        return bookRepository.findByone(name,value);
//    }

    @Override

    public List<BookEntity> findByOneSe2(Object name) {
        return bookRepository.findByone2(name);
    }

    @Override
    public String test() {
        return "this is service test";
    }


    @Override
    public BookEntity select() {
        return (BookEntity) bookRepository.findAll();
    }
}
