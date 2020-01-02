package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.TeacherListVO;
import com.youjiao.demo.controller.viewobject.user.TeacherVO;
import com.youjiao.demo.dataobject.TeacherDO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    /**
     * @author Kny
     * 通过classId获取教师列表信息，包括教师id和教师名字
     */
    List<TeacherListVO> getTeacherList(Integer classId);

    /**
     * @author Kny
     * 通过classId获取班主任id
     */
    Integer getHeadTeacher(Integer classId);

    /**
     * @author Lyy
     * 根据教师id获取对应的个人信息页
     */
    TeacherVO showTeacherInfo() throws BusinessException;

    /**
     * @author Lyy
     * 检查编辑后的信息是否合理，是则修改
     */
    void checkEditInfo(String telephone, String avatar) throws BusinessException;

    /**
     * @author Tzj
     * 获取教师列表
     */
    List<TeacherDO> getTeacherList();
}
