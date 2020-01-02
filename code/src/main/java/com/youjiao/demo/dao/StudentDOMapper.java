package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.controller.viewobject.user.ChildVO;
import com.youjiao.demo.dataobject.StudentDO;
import com.youjiao.demo.service.model.StudentModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Mapper
@Component
public interface StudentDOMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(StudentDO record);

    int insertSelective(StudentDO record);

    StudentDO selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(StudentDO record);

    int updateByPrimaryKey(StudentDO record);

    /**
     * @author Tzj
     * 通过家长id查找该家长已添加的孩子
     */
    @Select("select student.student_id,student.name,student.class_id,grade\n" +
            "from student,class,term_record\n" +
            "where student.alive=true and student.parent_id = #{parentId} and student.class_id=class.class_id and class.term_id=term_record.term_id")
    List<ChildVO> getStudentsByParentId(@Param("parentId") Integer parentId);

    /**
     * @author Tzj
     * 通过孩子身份证查找孩子
     */
    @Select("select * from student where id_number=#{idNumber} and alive=true ")
    StudentDO getStudentByIdNumber(@Param("idNumber") String idNumber);

    /**
     * @author Tzj
     * 通过家长输入的孩子身份证将孩子的父母Id设置为该家长
     */
    @Update("update student set parent_id = #{parentId} where id_number = #{idNumber} and alive=true ")
    Integer addChild(@Param("parentId") Integer parentId, @Param("idNumber") String idNumber);

    /**
     * @author CaiMJ
     * 通过班级id查找学生id
     */
    @Select("select student_id from student where class_id=#{classId} and alive=true ")
    List<StudentModel> getStudentIdByClassId(Integer classId);

    /**
     * @author Kny
     * 判断班级id是否为空，若为空，则根据机构id获取学生信息列表
     */
    @Select("select student_id,name,birthday,sex from student where school_id = #{schoolId} and class_id is null and alive=true ")
    List<StudentDO> selectStudentListBySchoolId(Integer schoolId);

    /**
     * @author Kny
     * 通过学生id给学生添加班级id
     */
    void updateStudentListByStudentId(Integer classId, List<Integer> studentList);

    /**
     * @author Kny
     * 通过学生id将学生表对应的班级id置为null
     */
    void updateClassIdByStudentId(List<Integer> list);

    /**
     * @author Lyy
     * 添加学生信息(无班级）
     */
    @Insert("insert into student(name,sex,birthday,id_number,start_grade,school_id,alive) values(#{name},#{sSex},#{birthday},#{idNumber},#{startGrade},#{schoolId},true)")
    void addStudentInfo(String name, Boolean sSex, Date birthday, String idNumber, Byte startGrade, Integer schoolId);

    /**
     * @author Lyy
     * 添加学生信息（有班级）
     */
    @Insert("insert into student(name,sex,birthday,id_number,start_grade,school_id,alive,class_id) values(#{name},#{sSex},#{birthday},#{idNumber},#{startGrade},#{schoolId},true,#{classId})")
    void addAllStudentInfo(String name, Boolean sSex, Date birthday, String idNumber, Byte startGrade, Integer schoolId, Integer classId);

    /**
     * @author Lyy
     * 通过身份证号查找学生
     */
    @Select("select * from student where id_number=#{idNumber} and alive=true ")
    StudentDO selectByIdNumber(String idNumber);

    /**
     * @author Lyy
     * 根据班级id获得孩子信息列表
     */
    @Select("select * from student where class_id = #{classId} and alive=true ")
    List<AdminStudentInfoVO> getStudentInfoListByClassId(Integer classId);

    /**
     * @author Ck
     * 根据班级id获取学生信息
     */
    @Select("select student_id,name,birthday,sex from student where class_id=#{classId} and alive=true ")
    List<AdminClassStudentListVO> selectAdminStudentListByClassId(Integer classId);

    /**
     * @author Lyy
     * 统计学生表中家长id为xxx的个数
     */
    @Select("select count(*) from student where parent_id = #{parentId} and alive=true ")
    Integer getCountByParentId(Integer parentId);

    /**
     * @author Ck
     * 根据班级id将学生表中对应学生的班级id置空
     */
    @Update("update student set class_id = NULL where class_id = #{classId} and alive=true ")
    void updateStudentClassIdToNull(Integer classId);

    /**
     * @author Lyy
     * 通过班级id获取学生信息列表
     */
    @Select("select student_id,name,birthday from student where class_id = #{classId} and alive=true ")
    List<AdminTransactionRecordStudentListVO> getStudentList(Integer classId);

    /**
     * @author Lyy
     * 通过班级id获取班级学生id列表
     */
    @Select("select student_id from student where class_id=#{classId} and alive=true ")
    List<Integer> selectStudentIdListByClassId(Integer classId);

    /**
     * @author Lyy
     * 根据学生id获取学生姓名和生日
     */
    @Select("select name,birthday from student where student_id = #{studentId}")
    AdminTransactionRecordStudentVO selectStudentByStudentId(Integer studentId);

    /**
     * @author Lyy
     * 根据学生id逻辑删除
     */
    @Update("update student set alive = false where student_id = #{studentId}")
    void updateStudentAliveByStudentId(Integer studentId);

    /**
     * @author Lyy
     * 根据学生id查询学生名字
     */
    @Select("select name from student where student_id = #{studentId}")
    String selectName(Integer studentId);
}