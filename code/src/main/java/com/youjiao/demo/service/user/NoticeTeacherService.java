package com.youjiao.demo.service.user;

import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.controller.viewobject.user.NoticeTeacherVO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author CaiMJ
 * #date 2019/03/13 19:43
 */
@Service
public interface NoticeTeacherService {
    /**
     * @author CaiMJ
     * 根据前端传来的classId,title,content在notice(公告)表中插入数据
     * 根据新插入的数据的主键("notice_id")以及学生列表的student_id对notice_unread(人员未读列表)表进行插入
     */
    void addNotice(Integer classId, String title, String content) throws BusinessException;

    /**
     * @author CaiMJ
     * 根据前端传来的noticeId,title,content在notice(公告)表中修改相对应数据
     * 删除人员未读列表中与该公告相对应的数据
     * 根据公告id与学生id再对notice_unread(人员未读列表)表进行插入
     */
    void updateNotice(Integer noticeId, String title, String content) throws BusinessException;

    /**
     * @author WengWenxin
     * 通过班级id获取教师的公告列表
     */
    List<NoticeTeacherVO> getNoticeList(Integer classId) throws BusinessException;
}
