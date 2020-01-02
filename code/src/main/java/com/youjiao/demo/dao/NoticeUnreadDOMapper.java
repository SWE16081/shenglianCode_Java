package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.NoticeUnreadDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NoticeUnreadDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoticeUnreadDO record);

    int insertSelective(NoticeUnreadDO record);

    NoticeUnreadDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeUnreadDO record);

    int updateByPrimaryKey(NoticeUnreadDO record);

    /**
     * @author CaiMJ
     * 通过公告id 删除相关的所有数据
     */
    @Delete("DELETE FROM notice_unread WHERE notice_id = #{noticeId}")
    void deleteByNoticeId(Integer noticeId);

    /**
     * @author Lyy
     * 通过学生id和公告id查询是否有该条记录以确定该学生是否未读该条公告
     */
    @Select("select * from notice_unread where notice_id = #{noticeId} and student_id = #{studentId}")
    NoticeUnreadDO selectByNoticeIdAndStudentId(Integer noticeId, Integer studentId);

}