package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.user.NoticeParentVO;
import com.youjiao.demo.controller.viewobject.user.NoticeTeacherVO;
import com.youjiao.demo.dataobject.NoticeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoticeDOMapper {
    int deleteByPrimaryKey(Integer noticeId);

    /**
     * @author CaiMJ
     * 修改了NoticeDOMapper.xml文件对应语句
     * 在notice(公告)表中插入数据，并且获取主键notice_id
     */
    int insert(NoticeDO noticeDO);

    /**
     * @author CainMJ
     * 通过公告id获得公告标题与内容
     */
    @Select("select notice_id,title,content,class_id from notice where notice_id =#{noticeId}")
    NoticeDO selectaAnnouncementByPrimaryKey(Integer noticeId);

    int insertSelective(NoticeDO record);

    NoticeDO selectByPrimaryKey(Integer noticeId);

    int updateByPrimaryKeySelective(NoticeDO record);

    int updateByPrimaryKey(NoticeDO record);

    /**
     * @author WengWenxin
     * 通过班级id获取公告列表
     */
    @Select("select * from notice where class_id =#{classId} order by create_time desc")
    List<NoticeTeacherVO> selectNoticeListByClassId(Integer classId);

    /**
     * @author Lyy
     * 通过班级id获取公告列表
     */
    @Select("select * from notice where class_id =#{classId} order by create_time desc")
    List<NoticeParentVO> selectNoticeParentListByClassId(Integer classId);
}