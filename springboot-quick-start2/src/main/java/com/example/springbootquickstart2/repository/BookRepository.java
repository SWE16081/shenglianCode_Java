package com.example.springbootquickstart2.repository;

import com.example.springbootquickstart2.entity.BookEntity;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

//继承JpaRepository完成对数据库的操作 参数：<实体类，主键类型>
public interface BookRepository extends JpaRepository<BookEntity,Integer> {

//       List<BookEntity> findByBookName(String bookname);
       BookEntity findBybookName(String bookname);
       BookEntity findBybookNameAndAuthor(String bookname,String author);

       @Query(value="SELECT * FROM public.book",nativeQuery = true)
        List<BookEntity> queryPar();

       @Query(value="SELECT book_name,author FROM book where id=?1",nativeQuery = true)
       List query2(Object id);
//       @Query(value="SELECT * FROM book where :name=?2",nativeQuery = true)
//       List findByone(Object name, Object val);

       @Query(value="SELECT * FROM book where author=?1",nativeQuery = true)
       List findByone2(Object name);
//       List<BookEntity> query2();报错显示id 字段没有
}

