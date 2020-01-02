package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.HomeworkParentVO;
import com.youjiao.demo.dao.HomeworkDOMapper;
import com.youjiao.demo.dao.HomeworkFileDOMapper;
import com.youjiao.demo.dataobject.HomeworkFileDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.user.HomeworkParentService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyImageUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Zjp
 * 2019/03/10
 * 家长端的作业相关的service的实现类
 */
@Service
public class HomeworkParentServiceImpl implements HomeworkParentService {
    private final HomeworkDOMapper homeworkDOMapper;

    private final HomeworkFileDOMapper homeworkFileDOMapper;

    @Autowired
    public HomeworkParentServiceImpl(HomeworkDOMapper homeworkDOMapper, HomeworkFileDOMapper homeworkFileDOMapper) {
        this.homeworkDOMapper = homeworkDOMapper;
        this.homeworkFileDOMapper = homeworkFileDOMapper;
    }

    /**
     * @author Zjp
     * 获得班级作业信息列表
     * 根据班级id获得班级作业列表
     * 根据学生id显示该学生的作业提交情况
     */
    @Override
    public List<HomeworkParentVO> getParentHomeworkList(Integer classId, Integer studentId) throws BusinessException {
        // 获取班级作业信息列表
        List<HomeworkParentVO> homeworkVOList = homeworkDOMapper.getParentHomeworkList(classId);
        // 补充一些信息
        for (HomeworkParentVO homeworkVO : homeworkVOList) {

            HomeworkFileDO homeworkFileDO = homeworkFileDOMapper.selectByHomeworkIdAndStudentId(
                    homeworkVO.getHomeworkId(), studentId);

            if (homeworkFileDO == null) {// 如果作业提交记录表中没有记录，教师端发布作业功能的代码可能有问题
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "该学生没有作业提交记录");
            }

            // 作业提交记录id，用于后续的提交作业功能
            homeworkVO.setFileId(homeworkFileDO.getFileId());
            // 确定该学生是否已提交作业，如果提交时间为空，则未提交该作业
            homeworkVO.setCommitted(homeworkFileDO.getCommitTime() != null);
        }
        return homeworkVOList;
    }

    /**
     * @author Zjp
     * 提交电子版作业
     * 根据作业id获得截止时间，检查是否超时
     * 根据作业提交记录id更新作业的提交记录
     * 将file上传到服务器
     */
    @Override
    public void parentCommitFile(Integer homeworkId, Integer fileId, String file) throws BusinessException {
        // 检查当前服务器时间是否超过该作业的截止时间，返回当前时间
        Timestamp nowDate = checkDeadline(homeworkId);

        // 创建作业文件实体
        HomeworkFileDO homeworkFileDO = new HomeworkFileDO();
        homeworkFileDO.setFileId(fileId);// 文件id，主键
        homeworkFileDO.setCommitTime(nowDate);// 提交时间

        // 上传文件到服务器
        String url = MyImageUtil.uploadImage(file, Constants.HOMEWORK_FILE_PATH);// 上传后的文件的url

        // 存储文件url
        homeworkFileDO.setUrl(url);

        // 根据文件id在作业文件表中更新数据
        try {
            homeworkFileDOMapper.updateByPrimaryKeySelective(homeworkFileDO);
        } catch (Exception e) {
            MyLog.error("作业提交记录id:\"" + fileId + "\"作业文件提交失败\n原因为:"
                    + MyExceptionUtil.getErrorMsg(e));
            throw new BusinessException(EmBusinessErr.HOMEWORK_COMMIT_FAILED);
        }
    }

    /**
     * @author Zjp
     * 确认纸质版作业
     * 根据作业id获得截止时间，检查是否超时
     * 根据作业提交记录id更新作业的提交记录
     */
    @Override
    public void parentConfirm(Integer homeworkId, Integer fileId) throws BusinessException {
        // 检查当前服务器时间是否超过该作业的截止时间，返回当前时间
        Timestamp nowDate = checkDeadline(homeworkId);
        System.out.println(nowDate);

        // 创建作业文件实体
        HomeworkFileDO homeworkFileDO = new HomeworkFileDO();
        homeworkFileDO.setFileId(fileId);
        homeworkFileDO.setCommitTime(nowDate);

        // 根据文件id在作业文件表中更新数据
        try {
            homeworkFileDOMapper.updateByPrimaryKeySelective(homeworkFileDO);
        } catch (Exception e) {
            MyLog.error("作业提交记录id:\"" + fileId + "\"作业确认失败\n原因为：" + MyExceptionUtil.getErrorMsg(e));
            throw new BusinessException(EmBusinessErr.HOMEWORK_COMMIT_FAILED);
        }
    }

    /**
     * @author Zjp
     * 检查当前服务器时间是否超过该作业的截止时间
     * 返回当前时间，类型为java.sql.Timestamp
     */
    private Timestamp checkDeadline(Integer homeworkId) throws BusinessException {
        // 生成当前时间
        Timestamp nowDate = new Timestamp(System.currentTimeMillis());
        // 获得该作业的截止时间
        Timestamp deadline = homeworkDOMapper.getDeadline(homeworkId);

        if (nowDate.after(deadline)) {// 如果当前时间超过截止时间
            throw new BusinessException(EmBusinessErr.HOMEWORK_COMMIT_TIMEOUT);
        }
        return nowDate;// 返回当前时间
    }
}
