package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.HomeworkParentVO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zjp
 * 2019/03/10
 * 家长端的作业相关的service接口
 */
@Service
public interface HomeworkParentService {
    /**
     * @author Zjp
     * 获得班级作业信息列表
     * 根据班级id获得班级作业列表
     * 根据学生id显示该学生的作业提交情况
     */
    List<HomeworkParentVO> getParentHomeworkList(Integer classId, Integer studentId) throws BusinessException;

    /**
     * @author Zjp
     * 提交电子版作业
     * 根据作业id获得截止时间，检查是否超时
     * 根据作业提交记录id更新作业的提交记录
     * 将file上传到服务器
     */
    void parentCommitFile(Integer homeworkId, Integer fileId, String file) throws BusinessException;

    /**
     * @author Zjp
     * 确认纸质版作业
     * 根据作业id获得截止时间，检查是否超时
     * 根据作业提交记录id更新作业的提交记录
     */
    void parentConfirm(Integer homeworkId, Integer fileId) throws BusinessException;
}
