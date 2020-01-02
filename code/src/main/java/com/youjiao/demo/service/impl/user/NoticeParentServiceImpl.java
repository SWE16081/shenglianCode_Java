package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.NoticeParentVO;
import com.youjiao.demo.dao.NoticeDOMapper;
import com.youjiao.demo.dao.NoticeUnreadDOMapper;
import com.youjiao.demo.dataobject.NoticeUnreadDO;
import com.youjiao.demo.service.user.NoticeParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lyy
 * #date 2019/03/15 10:03
 */
@Service
public class NoticeParentServiceImpl implements NoticeParentService {
    private final NoticeDOMapper noticeDOMapper;
    private final NoticeUnreadDOMapper noticeUnreadDOMapper;

    @Autowired
    public NoticeParentServiceImpl(NoticeDOMapper noticeDOMapper, NoticeUnreadDOMapper noticeUnreadDOMapper) {
        this.noticeDOMapper = noticeDOMapper;
        this.noticeUnreadDOMapper = noticeUnreadDOMapper;
    }

    /**
     * @author Lyy
     * 根据班级id获取公告列表，学生id获取是否已读信息
     */
    @Override
    public List<NoticeParentVO> getNoticeList(Integer classId, Integer studentId) {
        //获取公告列表
        List<NoticeParentVO> noticeParentVOList = noticeDOMapper.selectNoticeParentListByClassId(classId);
        for (NoticeParentVO noticeParentVO : noticeParentVOList) {
            //根据公告id和学生id去公告未读表中查询是否有记录，若DO==null则说明已读
            NoticeUnreadDO noticeUnreadDO = noticeUnreadDOMapper.selectByNoticeIdAndStudentId(noticeParentVO.getNoticeId(), studentId);
            noticeParentVO.setRead(noticeUnreadDO == null);
            //如果未读则给VO中的id（公告未读表中的id）赋值
            if (noticeUnreadDO != null) {
                noticeParentVO.setId(noticeUnreadDO.getId());
            }
        }
        return noticeParentVOList;
    }

    /**
     * @author Lyy
     * 通过公告未读表的主键id来删除对应的记录
     */
    @Override
    public void readNotice(Integer id) {
        if(noticeUnreadDOMapper.selectByPrimaryKey(id)!=null){
            //如果公告未读则从公告未读表中删除
            noticeUnreadDOMapper.deleteByPrimaryKey(id);
        }
    }
}
