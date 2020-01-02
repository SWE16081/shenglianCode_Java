package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.HomeworkFileDO;
import com.youjiao.demo.service.model.HomeworkFileMO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface HomeworkFileDOMapper {
    /**
     * @author Kny
     * 通过作业id查询班上学生的学生id
     */
    @Select("select student_id from student where class_id = #{classId}")
    List<HomeworkFileDO> selectStudentIdByClassId(Integer classId);

    /**
     * @author Kny
     * 通过作业id修改对应的学生作业文件，将url和提交时间置为null
     */
    @Update("UPDATE homework_file \n" +
            "SET url = NULL,\n" +
            "commit_time = NULL \n" +
            "WHERE\n" +
            "	homework_id = #{homeworkId};")
    void updateHomeworkFileByHomeworkId(Integer homeworkId);

    /**
     * @author Zjp
     * 根据作业id和学生id，获得作业提交记录id和提交时间
     */
    @Select("select file_id,commit_time from homework_file " +
            "where homework_id=#{homeworkId} " +
            "and student_id=#{studentId} ")
    HomeworkFileDO selectByHomeworkIdAndStudentId(Integer homeworkId,Integer studentId);

    int deleteByPrimaryKey(Integer fileId);

    int insert(HomeworkFileDO record);

    int insertSelective(HomeworkFileDO record);

    HomeworkFileDO selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(HomeworkFileDO record);

    int updateByPrimaryKey(HomeworkFileDO record);

    /**
     * @author Ck
     * 根据文件id 查询 提交的作业信息
     */
    @Select("SELECT\n" +
            "	homework.title,\n" +
            "	homework_file.url,\n" +
            "	homework_file.commit_time\n" +
            "FROM\n" +
            "	homework\n" +
            "INNER JOIN homework_file ON homework.homework_id = homework_file.homework_id\n" +
            "WHERE\n" +
            "	file_id = #{fileId}")
    HomeworkFileMO selectByFileId(Integer fileId);
}