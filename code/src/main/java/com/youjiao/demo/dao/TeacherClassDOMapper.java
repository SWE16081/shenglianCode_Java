package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.TeacherClassDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeacherClassDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherClassDO record);

    int insertSelective(TeacherClassDO record);

    TeacherClassDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherClassDO record);

    int updateByPrimaryKey(TeacherClassDO record);

    /**
     * @author Kny
     * 通过教师id给教师添加班级id
     */
    void insertListByClassIdAndTeacherId(Integer classId, List<Integer> teacherList);

    /**
     * @author Kny
     * 通过班级id和教师id删除班级教师
     */
    void deleteListByClassIdAndTeacherId(Integer classId, List<Integer> teacherList);

    /**
     * @author Ck
     * 根据班级id删除班级教师联系
     */
    @Update("delete from teacher_class where class_id = #{classId}")
    void deleteTeacherClassByClassId(Integer classId);
}