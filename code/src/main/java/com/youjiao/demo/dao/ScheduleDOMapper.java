package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminScheduleVO;
import com.youjiao.demo.dataobject.ScheduleDO;
import com.youjiao.demo.service.model.ScheduleMO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;


@Mapper
@Component
public interface ScheduleDOMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(ScheduleDO record);

    int insertSelective(ScheduleDO record);

    ScheduleDO selectByPrimaryKey(Integer scheduleId);

    int updateByPrimaryKeySelective(ScheduleDO record);

    int updateByPrimaryKey(ScheduleDO record);

    /**
     * @author Ck
     * 根据新旧id和时间及类型更新id
     */
    @Update("UPDATE schedule\n" +
            "SET activity_id = #{newId}\n" +
            "WHERE\n" +
            "	schooltime >= #{today}\n" +
            "AND type = #{type}\n" +
            "AND activity_id = #{oldId}")
    void updateNewActivityIdByOldActivityId(Integer oldId, Integer newId, Date today, Byte type);

    /**
     * @author Ck
     * 根据学期id和日期获取日期所在周的课程计划表记录
     */
    @Select("SELECT\n" +
            "	schedule_id,\n" +
            "	schooltime,\n" +
            "	number,\n" +
            "	activity_id,\n" +
            "	type,\n" +
            "	term_id\n" +
            "FROM\n" +
            "	schedule\n" +
            "WHERE\n" +
            "   term_id = #{termId}\n" +
            "AND YEARWEEK(\n" +
            "		date_format(schooltime, '%Y-%m-%d')\n" +
            "	) = YEARWEEK( #{dates} )\n" +
            "ORDER BY\n" +
            "	number,schooltime")
    List<AdminScheduleVO> selectFromDateOnWeek(Date dates, Integer termId);

    /**
     * @author Ck
     * 根据日期获取课程计划表
     */
    @Select("SELECT\n" +
            "	schedule_id,\n" +
            "	schooltime,\n" +
            "	number,\n" +
            "	activity_id,\n" +
            "	type,\n" +
            "	term_id\n" +
            "FROM\n" +
            "	schedule\n" +
            "WHERE\n" +
            "   schedule_id = #{id}")
    AdminScheduleVO selectById(Integer id);

    /**
     * @author Ck
     * 根据学期id和日期删除日期所在周的课程计划表记录
     */
    @Delete("DELETE FROM\n" +
            "	schedule\n" +
            "WHERE\n" +
            "   term_id = #{termId}\n" +
            "AND YEARWEEK(\n" +
            "		date_format(schooltime, '%Y-%m-%d')\n" +
            "	) = YEARWEEK( #{date} )")
    void deleteFromDateOnWeek(Date date, Integer termId);

    /**
     * @author Ck
     * 根据三个参数插入课程计划表记录
     */
    @Insert("INSERT INTO schedule (\n" +
            "	schooltime,\n" +
            "	number,\n" +
            "	activity_id,\n" +
            "	type,\n" +
            "	term_id\n" +
            ")\n" +
            "VALUES\n" +
            "	(\n" +
            "		#{mo.schooltime},\n" +
            "		#{number},\n" +
            "		#{mo.activityId},\n" +
            "		#{mo.type},\n" +
            "		#{termId}\n" +
            "	);")
    void insertSchedule(ScheduleMO mo, Integer termId, Integer number);

    /**
     * @author Ck
     * 根据三个参数修改课程计划表记录
     */
    @Select("UPDATE schedule\n" +
            "SET activity_id = #{activityId},\n" +
            " type = #{type}\n" +
            "WHERE\n" +
            "	schedule_id = #{scheduleId}")
    void modifySchedule(ScheduleMO mo);
}