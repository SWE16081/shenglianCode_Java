package com.example.springbootquickstart2.controller;

import com.example.springbootquickstart2.entity.Student;
import com.example.springbootquickstart2.service.StudentService;

import com.example.springbootquickstart2.util.ApiReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @RequestMapping("/saveAllStudent")
    public ApiReturnInfo saveStudent(){
        Student student=new Student();
        ArrayList arrayList=new ArrayList();
        student.setAge(11);
        student.setGender("男");
        student.setStudentClass("2");
        student.setTestcolumn("test字段");
        student.setStudentName("李白");
        student.setCreatedTime(System.currentTimeMillis());
        student.setUpdateTime(System.currentTimeMillis());
        arrayList.add(student);
        return studentService.saveAllStudentData(arrayList);
    }
    @RequestMapping("/saveStudent")

    public Student addStudent(){
        Student student=new Student();
        student.setAge(18);
        student.setGender("女");
        student.setStudentClass("2");
        student.setTestcolumn("test字aaa段");
        student.setStudentName("上官婉儿");
        student.setCreatedTime(System.currentTimeMillis());
        student.setUpdateTime(System.currentTimeMillis());
        return studentService.saveStudentData(student);
    }
    @RequestMapping("/deleteStudent")
    public ApiReturnInfo deleteStudent(){

        Integer studentid=1;
        return studentService.deleteStudentByID(studentid);
    }
    @RequestMapping("/updateStudent")
    public ApiReturnInfo updateStudent(){
        Integer studentid=1;
        String studentname="李白";
        long time=System.currentTimeMillis();
        return studentService.updataStudent(studentname,studentid,time);
    }
    /*
    * @Cacheable讲方法的运行结果进行缓存，以后再要相同的数据就从缓存中获取
    * */

    @RequestMapping("/selStudent")
    public ApiReturnInfo selStudent(){
        return studentService.selectAllStudent();
    }

//    @RequestMapping("/selStudentByTwo")
//    public ApiReturnInfo selStudentByTwo(){
//
//       System.out.println( System.currentTimeMillis());
//        String param="上官婉儿";
//        Integer param2=11;
//        return studentService.selectStudentByTwo(param,param2);
//    }
@RequestMapping("/selStudentByTwo")
public ApiReturnInfo selStudentByTwo(){

    System.out.println( System.currentTimeMillis());
    String param="上官婉儿";
    Integer param2=11;
     List data=studentService.selectStudentByTwo(param,param2);
     return ApiReturnInfo.success(data);
}
}

