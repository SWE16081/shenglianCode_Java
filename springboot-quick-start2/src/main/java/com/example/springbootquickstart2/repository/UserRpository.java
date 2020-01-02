package com.example.springbootquickstart2.repository;


import com.example.springbootquickstart2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRpository extends JpaRepository<UserEntity,Integer> {
   UserEntity findByuserNameAndPassword(String username,String password);

//   字段第一个大写会自动帮你转化成小写

   @Query(value="SELECT * FROM admin where user_name=?1 and password=?2",nativeQuery = true)
   List UsernamePsd(String username,String password);
//   @Query(value="SELECT * FROM admin",nativeQuery = true)
//   List findUsernamePsd(String usernmae,String password);
}
