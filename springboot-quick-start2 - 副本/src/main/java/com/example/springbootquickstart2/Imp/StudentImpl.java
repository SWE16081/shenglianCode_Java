package com.example.springbootquickstart2.Imp;

import com.example.springbootquickstart2.entity.Student;
import com.example.springbootquickstart2.repository.StudentRepository;
import com.example.springbootquickstart2.service.StudentService;
//import com.example.springbootquickstart2.tools.ApiReturnInfo;
import com.example.springbootquickstart2.util.ApiReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentImpl implements StudentService {

    //注入bookrepository
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public ApiReturnInfo selectAllStudent() {
        List result;

        try {
            System.out.println("查询所有学生");
          result=studentRepository.findAll();
        } catch (Exception e) {
            return ApiReturnInfo.fail();
        }

        return ApiReturnInfo.success(result);
    }

    @Override
    public ApiReturnInfo saveAllStudentData(List data) {
          List result= studentRepository.saveAll(data);
          return  ApiReturnInfo.success(result);
           //日志
//        Logger.trace("这是trace日志");
//        Logger.info("");
//        System.out.println(studentRepository.saveAll(data));
//         return "true";
    }

    @Override
    @Cacheable(value="cachetest",key="'studentid'")
    public Student saveStudentData(Student student) {

        System.out.println("查询学生");
        Student student2= studentRepository.save(student);
        return student2;
    }

    @Override
    public ApiReturnInfo updataStudent(String studentname, Integer studentid,long time) {
        try {
            studentRepository.updataStudent(studentname,studentid,time);
        } catch (Exception e) {
            System.out.println("WRONG FORMAT!");
            return ApiReturnInfo.fail();
        }

        return ApiReturnInfo.success();
    }

    @Override
    public ApiReturnInfo deleteStudentByID(Integer studenid) {
        try {
            studentRepository.deleteById(studenid);
        } catch (Exception e) {
            System.out.println("WRONG FORMAT!");
//            return ApiReturnInfo.fail();
        }

        return ApiReturnInfo.success();
    }

//    @Override
//    public ApiReturnInfo selectStudentByTwo(String param, Integer param2) {
//        List result;
//
//        try {
//            result=studentRepository.selectStudentByTwo(param,param2);
//        } catch (Exception e) {
//            return ApiReturnInfo.fail();
//        }
//
//        return ApiReturnInfo.success(result);
//    }
        @Override
        @Cacheable(value="cachetest2",key="'01'")
        public List selectStudentByTwo(String param, Integer param2) {
        System.out.println("查询  ");
                return studentRepository.selectStudentByTwo(param,param2);

        }

}
