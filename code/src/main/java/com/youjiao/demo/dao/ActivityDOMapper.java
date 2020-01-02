package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminActivityVO;
import com.youjiao.demo.dataobject.ActivityDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ActivityDOMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(ActivityDO record);

    int insertSelective(ActivityDO record);

    ActivityDO selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(ActivityDO record);

    int updateByPrimaryKey(ActivityDO record);

    /**
     * @author Ck
     * 逻辑删除活动, alive字段应在service中传递 !Constants.ALIVE 常量表示逻辑删除
     */
    @Delete("UPDATE activity SET alive = #{alive} WHERE activity_id = #{activityId}")
    void logicalDeleteByActivityId(Integer activityId, Boolean alive);

    /**
     * @author Ck
     * 根据id选取活动项
     */
    @Select("SELECT\n" +
            "	activity_id,\n" +
            "	NAME,\n" +
            "	description\n" +
            "FROM\n" +
            "	activity\n" +
            "WHERE\n" +
            "	activity_id = #{activityId}")
    AdminActivityVO selectActivityById(Integer activityId);

    /**
     * @author Ck
     * 获取所有未删除的活动项
     */
    @Select("SELECT\n" +
            "	activity_id,\n" +
            "	NAME,\n" +
            "	description\n" +
            "FROM\n" +
            "	activity\n" +
            "WHERE\n" +
            "	alive = #{alive}")
    List<AdminActivityVO> selectActivityAll(Boolean alive);

    /**
     * @author Ck
     * 根据活动名称查找
     */
    @Select("select * from activity where name = #{name} and alive = #{alive}")
    List<ActivityDO> selectBySame(String name, boolean alive);

    /**
     * @author Ck
     * 插入活动并获得id
     */
    @Insert("insert into activity (name,description,alive) values(#{name},#{description},true)")
    @Options(useGeneratedKeys = true, keyProperty = "activityId", keyColumn = "activity_id")
    int insertActivity(ActivityDO activityDO);
}