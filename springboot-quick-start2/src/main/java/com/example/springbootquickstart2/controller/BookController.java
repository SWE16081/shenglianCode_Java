package com.example.springbootquickstart2.controller;

import com.example.springbootquickstart2.entity.BookEntity;
import com.example.springbootquickstart2.repository.BookRepository;
import com.example.springbootquickstart2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
//    自动注入Repository类
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;
    @GetMapping("/book/{id}")
//    @PathVariable可以用来映射URL中的占位符到目标方法的参数中
    public BookEntity getBook(@PathVariable("id") Integer id){
       BookEntity book= bookRepository.getOne(id);
        return book;
    }
    @GetMapping("/book")
    public BookEntity insertBook(BookEntity bookEntity){
        BookEntity book=bookRepository.save(bookEntity);
        return book;
    }
    @GetMapping("/booksel")
    public List<BookEntity> selectBook(){
        return bookRepository.findAll();
    }

//    @GetMapping("/findbybookname")
//    public List<BookEntity> findbybookname(){
//        return bookRepository.findByBookName("111");
//    }
    @GetMapping("/findbybookname")
    public BookEntity findbybooknamew(){
        return bookRepository.findBybookName ("111");
    }
    @GetMapping("/findbooknameauthor")
    public BookEntity findBybookNameAndauthor(){
        return bookRepository.findBybookNameAndAuthor ("111","swe");
    }
    @GetMapping("/findname")
    public List<BookEntity> test(){
        return bookRepository.queryPar();
    }
    @GetMapping("/findna")
    public List test2(){
        return bookRepository.query2(2);
    }
//    @GetMapping("/findbyone")
//    public List findbyonetest(){
//        return baseService.findByOneSe("author","swe");
//    }
    @GetMapping("/findbyone2")
    public List findbyonetest2(){
        return bookService.findByOneSe2("swe");
    }
    @GetMapping("/servicetest")
    public String servicetest(){
        return bookService.test();
    }

    @GetMapping("/saveallbook")
    public String saveallbook(){
        ArrayList booklist = new ArrayList();
        BookEntity bookEntity=new BookEntity();
        bookEntity.setId(3);
        bookEntity.setAuthor("李白");
        bookEntity.setBookname("唐诗三百首");
        booklist.add(bookEntity);
        bookRepository.saveAll(booklist);
        return "ok";
    }
    @GetMapping("/savebook")
    public String savebook(){
        BookEntity bookEntity=new BookEntity();
        bookEntity.setAuthor("李白11");
        bookEntity.setBookname("唐222诗三百首");
         bookRepository.save(bookEntity);
         return "ww";
    }
    @GetMapping("/cachettest")
    public BookEntity cachetest(){
        return bookService.select();
    }
}
