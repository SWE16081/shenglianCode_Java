package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.HomeworkCommitListVO;
import com.youjiao.demo.controller.viewobject.user.HomeworkFileVO;
import com.youjiao.demo.controller.viewobject.user.HomeworkInfoListVO;
import com.youjiao.demo.controller.viewobject.user.HomeworkUncommitListVO;
import com.youjiao.demo.controller.viewobject.user.HomeworkAddVO;
import com.youjiao.demo.controller.viewobject.user.HomeworkModifyVO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Ck
 * #date 2019/03/12 22:27
 */
//@Service
public interface HomeworkTeacherService {
    /**
     * @author Ck
     * 教师端根据班级id和教师id获取作业列表
     */
    List<HomeworkInfoListVO> getHomeworkListByClassIdAndTeacherId(Integer classId, Integer teacherId);

    /**
     * @author Ck
     * 根据作业id 查询 已交 作业的学生名单
     */
    List<HomeworkCommitListVO> getCommittedHomeworkListByHomeworkId(Integer homeworkId);

    /**
     * @author Ck
     * 根据作业id 查询 没有交 作业的学生名单
     */
    List<HomeworkUncommitListVO> getUncommittedHomeworkListByHomeworkId(Integer homeworkId);

    /**
     * @author Ck
     * 根据作业文件id 查询作业文件
     */
    HomeworkFileVO getHomeworkFileByFileId(Integer fileId) throws BusinessException;
	
	 /**
     * @author Kny
     * 教师端添加作业
     * 通过VO转DO插入作业
     * 根据作业id和班级id查询学生id列表并插入作业文件信息表
     */
    void addHomeworkFromTeacher(HomeworkAddVO homeworkAddVO) throws BusinessException;

    /**
     * @author Kny
     * 教师端修改作业
     * 先验证修改内容前后是否一致，不一致则VO转DO再修改作业
     * 根据作业id将文件信息表中对应的url和提交时间置为null
     */
    void modifyHomeworkFromTeacher(HomeworkModifyVO homeworkModifyVO) throws BusinessException;
}
