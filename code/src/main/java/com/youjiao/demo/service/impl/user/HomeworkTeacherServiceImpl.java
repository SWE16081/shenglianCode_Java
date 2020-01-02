package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.*;
import com.youjiao.demo.dao.HomeworkDOMapper;
import com.youjiao.demo.dao.HomeworkFileDOMapper;
import com.youjiao.demo.dataobject.HomeworkDO;
import com.youjiao.demo.dataobject.HomeworkFileDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.model.HomeworkFileMO;
import com.youjiao.demo.service.user.HomeworkTeacherService;
import com.youjiao.demo.util.*;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Ck
 * #date 2019/03/12 22:32
 */
@Service
public class HomeworkTeacherServiceImpl implements HomeworkTeacherService {
    private final HomeworkDOMapper homeworkDOMapper;
    private final HomeworkFileDOMapper homeworkFileDOMapper;

    @Autowired
    public HomeworkTeacherServiceImpl(HomeworkDOMapper homeworkDOMapper, HomeworkFileDOMapper homeworkFileDOMapper) {
        this.homeworkDOMapper = homeworkDOMapper;
        this.homeworkFileDOMapper = homeworkFileDOMapper;
    }

    /**
     * @author Ck
     * 教师端根据班级id和教师id获取作业列表
     */
    @Override
    public List<HomeworkInfoListVO> getHomeworkListByClassIdAndTeacherId(Integer classId, Integer teacherId) {

        List<HomeworkInfoListVO> listVO = homeworkDOMapper.selectHomeworkListByClassId(classId);
        long nowTime = System.currentTimeMillis();
        for (HomeworkInfoListVO vo : listVO) {
            //判断是否可以被当前教师编辑
            vo.setEditable(vo.getTeacherId().equals(teacherId));

            //判断是否已经截止    截止时间 小于 系统时间 ，表示已截止
            vo.setEnd(vo.getFinishTime().getTime() < nowTime);
        }
        return listVO;
    }

    /**
     * @author Ck
     * 根据作业id查询 已交 作业的学生名单
     */
    @Override
    public List<HomeworkCommitListVO> getCommittedHomeworkListByHomeworkId(Integer homeworkId) {
        return homeworkDOMapper.selectCommittedListByHomeworkId(homeworkId);
    }

    /**
     * @author Ck
     * 根据作业id 查询 没有交 作业的学生名单
     */
    @Override
    public List<HomeworkUncommitListVO> getUncommittedHomeworkListByHomeworkId(Integer homeworkId) {
        return homeworkDOMapper.selectUncommittedListByHomeworkId(homeworkId);
    }

    /**
     * @author Ck
     * 根据作业文件id 查询作业文件
     */
    @Override
    public HomeworkFileVO getHomeworkFileByFileId(Integer fileId) throws BusinessException {
        HomeworkFileVO vo = new HomeworkFileVO();
        HomeworkFileMO mo = homeworkFileDOMapper.selectByFileId(fileId);
        String moUrl = mo.getUrl();

        //参数检查
        MyValidation.checkStrNull(moUrl);

        BeanUtils.copyProperties(mo, vo);

        //根据url提取图片
        String base64 = MyImageUtil.downloadImage(moUrl, Constants.HOMEWORK_FILE_PATH);
        vo.setFile(base64);

        return vo;
    }

    /**
     * @author Kny
     * 教师端添加作业
     * 通过VO转DO插入作业
     * 根据作业id和班级id查询学生id列表并插入作业文件信息表
     */
    @Override
    @Transactional
    public void addHomeworkFromTeacher(HomeworkAddVO homeworkAddVO) throws BusinessException {
        //新建homeworkDO后VO转DO
        HomeworkDO homeworkDO = new HomeworkDO();
        BeanUtils.copyProperties(homeworkAddVO, homeworkDO);
        //从session获取教师id并存入变量
        Integer teacherId = (Integer) MySessionUtil.getAttribute(Constants.TEACHER_ID_SESSION);
        //将剩下的参数插入DO
        homeworkDO.setTeacherId(teacherId);
        homeworkDO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        homeworkDO.setModifyTime(new Timestamp(System.currentTimeMillis()));

        //调用insert语句将作业插入数据库并返回主键
        try {
            homeworkDOMapper.insertSelective(homeworkDO);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.HOMEWORK_ADD_FAILED);
        }

        //获取学生id
        List<HomeworkFileDO> studentList = homeworkFileDOMapper.selectStudentIdByClassId(homeworkDO.getClassId());
        //通过foreach用insert将学生列表插入数据库
        for (HomeworkFileDO student : studentList) {
            //存储数据
            student.setHomeworkId(homeworkDO.getHomeworkId());
            try {
                homeworkFileDOMapper.insert(student);
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.HOMEWORK_ADD_STUDENT_FAILED);
            }

        }

    }

    /**
     * @author Kny
     * 教师端修改作业
     * 先验证修改内容前后是否一致，不一致则VO转DO再修改作业
     * 根据作业id将文件信息表中对应的url和提交时间置为null
     */
    @Override
    public void modifyHomeworkFromTeacher(HomeworkModifyVO homeworkModifyVO) throws BusinessException {
        //新建DO并判断作业内容修改前后是否一致，不一致则VO转DO
        HomeworkDO homeworkDO = homeworkDOMapper.selectHomeworkByHomeworkId(homeworkModifyVO.getHomeworkId());
        //判断参数是否全为null
        boolean flag = true;

        if (homeworkDO.getType().equals(homeworkModifyVO.getType())) {
            homeworkDO.setType(null);
        } else {
            homeworkDO.setType(homeworkModifyVO.getType());
            flag = false;
        }
        if (homeworkDO.getTitle().equals(homeworkModifyVO.getTitle())) {
            homeworkDO.setTitle(null);
        } else {
            homeworkDO.setTitle(homeworkModifyVO.getTitle());
            flag = false;
        }
        if (homeworkDO.getContent().equals(homeworkModifyVO.getContent())) {
            homeworkDO.setContent(null);
        } else {
            homeworkDO.setContent(homeworkModifyVO.getContent());
            flag = false;
        }
        if (homeworkDO.getFinishTime().equals(homeworkModifyVO.getFinishTime())) {
            homeworkDO.setFinishTime(null);
        } else {
            homeworkDO.setFinishTime(homeworkModifyVO.getFinishTime());
            flag = false;
        }

        //添加主键
        homeworkDO.setHomeworkId(homeworkModifyVO.getHomeworkId());
        //修改修改时间
        homeworkDO.setModifyTime(new Timestamp(System.currentTimeMillis()));

        //用update修改作业内容
        try {
            homeworkDOMapper.updateByPrimaryKeySelective(homeworkDO);
        } catch (Exception e) {
            throw new BusinessException(e, EmBusinessErr.HOMEWORK_MODIFY_FAILED);
        }

        //用update修改作业文件信息中作业id对应的学生url和提交时间
        if (!flag) {
            try {
                homeworkFileDOMapper.updateHomeworkFileByHomeworkId(homeworkDO.getHomeworkId());
            } catch (Exception e) {
                throw new BusinessException(e, EmBusinessErr.HOMEWORK_MODIFY_STUDENT_FAILED);
            }
        }

    }
}
