package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.NoticeTeacherVO;
import com.youjiao.demo.dao.NoticeDOMapper;
import com.youjiao.demo.dao.NoticeUnreadDOMapper;
import com.youjiao.demo.dao.StudentDOMapper;
import com.youjiao.demo.dataobject.NoticeDO;
import com.youjiao.demo.dataobject.NoticeUnreadDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.model.StudentModel;
import com.youjiao.demo.service.user.NoticeTeacherService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.util.MySessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static com.youjiao.demo.validator.MyValidation.checkIntNull;
import static com.youjiao.demo.validator.MyValidation.checkObjectNull;

/**
 * @author CaiMJ
 * #date 2019/03/13 19:43
 */
@Service
public class NoticeTeacherServiceImpl implements NoticeTeacherService {

    private final NoticeDOMapper noticeDOMapper;
    private final StudentDOMapper studentDOMapper;
    private final NoticeUnreadDOMapper noticeUnreadDOMapper;

    @Autowired
    public NoticeTeacherServiceImpl(NoticeDOMapper noticeDOMapper, StudentDOMapper studentDOMapper, NoticeUnreadDOMapper noticeUnreadDOMapper) {
        this.noticeDOMapper = noticeDOMapper;
        this.studentDOMapper = studentDOMapper;
        this.noticeUnreadDOMapper = noticeUnreadDOMapper;
    }

    /**
     * @author CaiMJ
     * 根据前端传来的classId,title,content在notice(公告)表中插入数据
     * 根据新插入的数据的主键("notice_id")以及学生列表的student_id对人员未读列表进行插入
     */
    @Override
    @Transactional
    public void addNotice(Integer classId, String title, String content) throws BusinessException {
        //从Session获取teacherId
        Integer teacherId = (Integer) (MySessionUtil.getSession().getAttribute(Constants.TEACHER_ID_SESSION));
        //Session是否存在教师id
        checkIntNull(teacherId);
        //新建noticeDO 存储数据
        NoticeDO noticeDO = new NoticeDO();

        noticeDO.setClassId(classId);
        noticeDO.setTeacherId(teacherId);
        noticeDO.setTitle(title);
        noticeDO.setContent(content);
        noticeDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //通过公告id对公告表进行插入
        try {
            noticeDOMapper.insert(noticeDO);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.INSERT_NOTICE_FAILED);
        }
        //根据班级id和公告id对notice_unread(公告未读成员列表)表进行插入
        insertNoticeUnread(classId, noticeDO.getNoticeId());
    }

    /**
     * @author CaiMJ
     * 根据前端传来的noticeId,title,content在notice(公告)表中修改相对应数据
     * 删除人员未读列表中与该公告相对应的数据
     * 根据公告id与学生id再对未读人员列表进行插入
     */
    @Override
    @Transactional
    public void updateNotice(Integer noticeId, String title, String content) throws BusinessException {
        //通过公告id获得标题与内容
        NoticeDO noticeDO = noticeDOMapper.selectaAnnouncementByPrimaryKey(noticeId);
        checkObjectNull(noticeDO);

        //修改时间
        noticeDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //判断与原公告是否相同,如果不相同,则修改相对应的数据,相同则设为null
        if (noticeDO.getTitle().equals(title)) {
            noticeDO.setTitle(null);
        } else {
            noticeDO.setTitle(title);
        }
        if (noticeDO.getContent().equals(content)) {
            noticeDO.setContent(null);
        } else {
            noticeDO.setContent(content);
        }
        //根据公告id更新数据库相对应的数据
        try {
            noticeDOMapper.updateByPrimaryKeySelective(noticeDO);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.UPDATE_NOTICE_FAILED);
        }
        //根据公告id将notice_unread(公告未读成员列表)表中的数据删除
        try {
            noticeUnreadDOMapper.deleteByNoticeId(noticeDO.getNoticeId());
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.DELETE_NOTICE_UNREAD_FAILED);
        }
        //根据班级id和公告id对notice_unread(公告未读成员列表)表进行插入
        insertNoticeUnread(noticeDO.getClassId(), noticeDO.getNoticeId());
    }

    /**
     * @author CaiMJ
     * 在notice_unread(公告未读成员列表)表中插入班级所对应的所有学生id
     */
    private void insertNoticeUnread(Integer classId, Integer noticeId) throws BusinessException {
        //根据班级id查找学生id列表
        List<StudentModel> studentModelList = studentDOMapper.getStudentIdByClassId(classId);
        //新建NoticeUnreadDO 存储传入数据库的数据
        NoticeUnreadDO noticeUnreadDO = new NoticeUnreadDO();
        //遍历StudentDOList
        for (StudentModel studentModel : studentModelList) {
            //存储数据
            noticeUnreadDO.setStudentId(studentModel.getStudentId());
            noticeUnreadDO.setNoticeId(noticeId);
            //插入数据库
            try {
                noticeUnreadDOMapper.insert(noticeUnreadDO);
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.INSERT_NOTICE_UNREAD_FAILED);
            }
        }
    }

    /**
     * @author WengWenxin
     * 通过班级id获取教师的公告列表
     */
    @Override
    public List<NoticeTeacherVO> getNoticeList(Integer classId) throws BusinessException {
        //获取班级公告列表
        List<NoticeTeacherVO> noticeTeacherVOList = noticeDOMapper.selectNoticeListByClassId(classId);
        //获取session中的教师id，用于和公告表中的教师id比对，相同则可以进行修改
        Integer teacherId = (Integer) (MySessionUtil.getAttribute(Constants.TEACHER_ID_SESSION));
        //参数检验
        checkIntNull(teacherId);
        //将列表中的公告一个个检查其中的教师id是否和session中的教师id相同，相同则将isEditable设成true
        for (NoticeTeacherVO noticeTeacherVO : noticeTeacherVOList) {
            //如果公告的发布者和登陆的教师相等，则将isEditable设为true
            noticeTeacherVO.setEditable(noticeTeacherVO.getTeacherId().equals(teacherId));
        }
        return noticeTeacherVOList;
    }
}

