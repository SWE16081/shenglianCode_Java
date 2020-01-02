package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.user.HomeworkCommitListVO;
import com.youjiao.demo.controller.viewobject.user.HomeworkInfoListVO;
import com.youjiao.demo.controller.viewobject.user.HomeworkParentVO;
import com.youjiao.demo.controller.viewobject.user.HomeworkUncommitListVO;
import com.youjiao.demo.dataobject.HomeworkDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@Component
public interface HomeworkDOMapper {
    /**
     * @author Kny
     * 通过作业id查询作业内容，作比较用
     */
    @Select("select type,title,content,finish_time from homework where homework_id = #{classId}")
    HomeworkDO selectHomeworkByHomeworkId(Integer homeworkId);

    /**
     * @author Zjp
     * 获取班级作业信息列表
     */
    @Select("select * from homework where class_id=#{classId} order by modify_time desc")
    List<HomeworkParentVO> getParentHomeworkList(Integer classId);

    /**
     * @author Zjp
     * 获得作业的截止时间
     */
    @Select("select finish_time from homework where homework_id=#{homeworkId}")
    Timestamp getDeadline(Integer homeworkId);

    int deleteByPrimaryKey(Integer homeworkId);

    int insert(HomeworkDO record);

    /**
     * @author Kny
     * 插入作业的同时返回作业id（主键）
     */
    int insertSelective(HomeworkDO record);

    HomeworkDO selectByPrimaryKey(Integer homeworkId);

    int updateByPrimaryKeySelective(HomeworkDO record);

    int updateByPrimaryKey(HomeworkDO record);

    /**
     * @author Ck
     * 根据班级id 查询该班级所有的作业列表
     */
    @Select("SELECT\n" +
            "	homework.homework_id,\n" +
            "	homework.teacher_id,\n" +
            "	homework.type,\n" +
            "	homework.title,\n" +
            "   homework.content,\n" +
            "	homework.create_time,\n" +
            "	homework.finish_time,\n" +
            "	homework.modify_time,\n" +
            "	(\n" +
            "		SELECT\n" +
            "			COUNT(DISTINCT student_id)\n" +
            "		FROM\n" +
            "			homework_file\n" +
            "		WHERE\n" +
            "			homework.homework_id = homework_file.homework_id\n" +
            "			AND commit_time is not null\n" +
            "	) AS committed_num,\n" +
            "	(\n" +
            "		SELECT\n" +
            "			COUNT(DISTINCT student_id)\n" +
            "		FROM\n" +
            "			homework_file\n" +
            "		WHERE\n" +
            "			homework.homework_id = homework_file.homework_id\n" +
            "			AND commit_time is null\n" +
            "	) AS uncommitted_num\n" +
            "FROM\n" +
            "	homework\n" +
            "where\n" +
            "class_id = #{classId}\n" +
            "order by finish_time desc")
    List<HomeworkInfoListVO> selectHomeworkListByClassId(Integer classId);

    /**
     * @author Ck
     * 根据作业id查询 已经交 的名单
     */
    @Select("SELECT\n" +
            "	t2.file_id,\n" +
            "	t3.`name`,\n" +
            "	t2.commit_time,\n" +
            "	t1.type\n" +
            "FROM\n" +
            "	(\n" +
            "		SELECT\n" +
            "		homework_file.file_id,\n" +
            "		homework_file.commit_time,\n" +
            "		homework_file.homework_id,\n" +
            "		student_id\n" +
            "	FROM\n" +
            "		homework_file\n" +
            "	WHERE\n" +
            "		homework_file.commit_time IS NOT NULL\n" +
            "	AND homework_file.homework_id = #{homeworkId}\n" +
            "	) AS t2\n" +
            "LEFT JOIN (\n" +
            "           SELECT\n" +
            "			homework.type,\n" +
            "			homework.homework_id\n" +
            "		FROM\n" +
            "			homework\n" +
            "		WHERE\n" +
            "			homework.homework_id = #{homeworkId}\n" +
            ") AS t1 ON t1.homework_id = t2.homework_id\n" +
            "LEFT JOIN (\n" +
            "	SELECT\n" +
            "		student.`name`,\n" +
            "		student.student_id\n" +
            "	FROM\n" +
            "		student\n" +
            ") AS t3 ON t2.student_id = t3.student_id;")
    List<HomeworkCommitListVO> selectCommittedListByHomeworkId(Integer homeworkId);

    /**
     * @author Ck
     * 根据作业id查询 没有交 的名单
     */
    @Select("SELECT\n" +
            "	student. NAME\n" +
            "FROM\n" +
            "homework_file\n" +
            "INNER JOIN student ON homework_file.student_id = student.student_id\n" +
            "WHERE\n" +
            "commit_time is null and\n" +
            "homework_file.homework_id = #{homeworkId}")
    List<HomeworkUncommitListVO> selectUncommittedListByHomeworkId(Integer homeworkId);
}