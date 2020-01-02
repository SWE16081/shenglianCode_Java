package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminClassListVO;
import com.youjiao.demo.controller.viewobject.admin.AdminStudentClassListVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTransactionRecordClassListVO;
import com.youjiao.demo.controller.viewobject.user.ClassListVO;
import com.youjiao.demo.dataobject.ClassDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Ck
 * #Date 2019-03-03 14:55
 */
@Mapper
@Component
public interface ClassDOMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(ClassDO record);

    int insertSelective(ClassDO record);

    ClassDO selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(ClassDO record);

    int updateByPrimaryKey(ClassDO record);

    /**
     * @author Ck
     * 根据teacherId 和isArchive 信息筛选出对应的班级列表
     */
    @Select("select class_id,name,grade from class,term_record " +
            "where class_id in (select class_id from teacher_class where teacher_id = #{teacherId}) " +
            "and class.term_id = term_record.term_id " +
            "and is_archive = #{isArchive} " +
            "and alive = true " +
            "order by create_time desc")
    List<ClassListVO> selectByTeacherIdAndIsArchive(Integer teacherId, boolean isArchive);

    /**
     * @author Ck
     * 根据机构id查询班级列表
     */
    @Select("SELECT\n" +
            "	class_id,\n" +
            "	class. NAME class_name,\n" +
            "	class.teacher_id,\n" +
            "	teacher. NAME teacher_name,\n" +
            "	is_archive,\n" +
            "   term_record.term_id,\n" +
            "   year,\n" +
            "   term_num,\n" +
            "   grade\n" +
            "FROM\n" +
            "	class,\n" +
            "	teacher,\n" +
            "   term_record\n" +
            "WHERE\n" +
            "	class.teacher_id = teacher.teacher_id\n" +
            "AND class.term_id = term_record.term_id\n" +
            "AND class.school_id = #{schoolId}\n" +
            "AND class.alive = true "+
            "order by create_time desc")
    List<AdminClassListVO> selectBySchoolId(Integer schoolId);

    /**
     * @author Ck
     * 根据机构id和归档信息查询班级列表
     */
    @Select("SELECT\n" +
            "	class_id,\n" +
            "	class. NAME class_name,\n" +
            "	class.teacher_id,\n" +
            "	teacher. NAME teacher_name,\n" +
            "	is_archive,\n" +
            "   term_record.term_id,\n" +
            "   year,\n" +
            "   term_num,\n" +
            "   grade\n" +
            "FROM\n" +
            "	class,\n" +
            "	teacher,\n" +
            "   term_record\n" +
            "WHERE\n" +
            "	class.teacher_id = teacher.teacher_id\n" +
            "AND class.term_id = term_record.term_id\n" +
            "AND class.is_archive = #{isArchive}\n" +
            "AND class.school_id = #{schoolId}\n" +
            "AND class.alive = true "+
            "order by create_time desc")
    List<AdminClassListVO> selectBySchoolIdAndIsArchive(Integer schoolId, Boolean isArchive);

    /**
     * @author Ck
     * 设置班级归档状态
     */
    @Update("UPDATE class\n" +
            "SET is_archive = #{isArchive}\n" +
            "WHERE\n" +
            "	class_id = #{classId}")
    void modifyClassArchive(Integer classId, Boolean isArchive);

    /**
     * @author Ck
     * 逻辑删除班级
     */
    @Update("update class set alive = #{alive} where class_id = #{classId}")
    void logicalDeleteClassById(Integer classId,boolean alive);

    /**
     * @author Lyy
     * 通过机构id获取该机构名下的所有班级
     * 包括班级id，班级名称，是否已归档（alive=true,未删除）
     */
    @Select("select class_id,name,is_archive from class where school_id = #{schoolId} and alive = true")
    List<AdminStudentClassListVO> getClassListBySchoolId(Integer schoolId);

    /**
     * @author Lyy
     * 通过机构id获取该机构名下的所有班级
     * 包括班级id，班级名称
     */
    @Select("select class_id,name from class where school_id = #{schoolId} and alive = true")
    List<AdminTransactionRecordClassListVO> getTransactionRecordClassListBySchoolId(Integer schoolId);
}