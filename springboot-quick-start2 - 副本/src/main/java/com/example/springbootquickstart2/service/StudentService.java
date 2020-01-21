package com.example.springbootquickstart2.service;

import com.example.springbootquickstart2.entity.Student;
import com.example.springbootquickstart2.util.ApiReturnInfo;

import java.util.List;

public interface StudentService {

    //查询
    ApiReturnInfo selectAllStudent();
    //saveall添加
    ApiReturnInfo saveAllStudentData(List data);
    //save添加
    Student saveStudentData(Student student);
    //更新
    ApiReturnInfo updataStudent(String studentname, Integer studentid,long time);
    //删除
    ApiReturnInfo deleteStudentByID(Integer studenid);

    //按双条件查询
    List selectStudentByTwo(String param, Integer param2);
}
