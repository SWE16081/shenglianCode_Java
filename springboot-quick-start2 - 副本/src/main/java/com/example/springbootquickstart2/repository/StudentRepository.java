package com.example.springbootquickstart2.repository;

        import com.example.springbootquickstart2.entity.Student;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.relational.core.sql.Update;
        import org.springframework.data.repository.query.Param;

        import javax.transaction.Transactional;
        import java.sql.Date;
        import java.util.Calendar;
        import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {


    @Query(value="SELECT * FROM book where author=?1",nativeQuery = true)
    List findByone2(Object name);
    //数据更新
//    @Query(value="update student set student_name=:studentname where studentid=:studentid",nativeQuery = true)
//    List updataStudent(@Param("studentname") String studentname, @Param("studentid") Integer studentid);

    //数据更新

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update student set student_name=?1 ,created_time=?3,update_time=?3 where studentid=?2",nativeQuery = true)
    void updataStudent(String studentname, Integer studentid,long time);

    //重载双条件查询

    @Query(value="select * from student where student_name=?1 and studentid=?2 ",nativeQuery = true)
    List selectStudentByTwo(String studentname, Integer studentid);
}
