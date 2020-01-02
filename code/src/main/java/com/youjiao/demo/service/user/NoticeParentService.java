package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.NoticeParentVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lyy
 * #date 2019/03/15 10:02
 */
@Service
public interface NoticeParentService {
    /**
     * @author Lyy
     * 根据班级id获取公告列表，学生id获取是否已读信息
     */
    List<NoticeParentVO> getNoticeList(Integer classId, Integer studentId);

    /**
     * @author Lyy
     * 通过公告未读表的主键id来删除对应的记录
     */
    void readNotice(Integer id);
}
