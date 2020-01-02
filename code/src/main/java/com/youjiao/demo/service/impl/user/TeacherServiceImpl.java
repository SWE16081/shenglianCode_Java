package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.TeacherListVO;
import com.youjiao.demo.controller.viewobject.user.TeacherVO;
import com.youjiao.demo.dao.TeacherDOMapper;
import com.youjiao.demo.dataobject.TeacherDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.user.TeacherService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyImageUtil;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.util.MySessionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.youjiao.demo.validator.MyValidation.checkObjectNull;


@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDOMapper teacherDOMapper;

    @Autowired
    public TeacherServiceImpl(TeacherDOMapper teacherDOMapper) {
        this.teacherDOMapper = teacherDOMapper;
    }

    /**
     * @author Kny
     * 通过classId获取教师列表信息，包括教师id和教师名字
     */
    @Override
    public List<TeacherListVO> getTeacherList(Integer classId) {
        return teacherDOMapper.selectTeacherListByClassId(classId);
    }

    /**
     * @author Kny
     * 通过classId获取班主任id
     */
    @Override
    public Integer getHeadTeacher(Integer classId) {
        return teacherDOMapper.selectHeadTeacherByClassId(classId);
    }

    /**
     * @author Lyy
     * 根据教师id获取对应的个人信息页
     */
    @Override
    public TeacherVO showTeacherInfo() throws BusinessException {
        // 获取session中的教师id
        Integer teacherId = (Integer) (MySessionUtil.getAttribute(Constants.TEACHER_ID_SESSION));

        TeacherDO teacherDO = teacherDOMapper.selectByPrimaryKey(teacherId);
        checkObjectNull(teacherDO);

        //根据数据库头像的url获得教师头像
        String avatar = MyImageUtil.downloadImage(teacherDO.getAvatar(), Constants.TEACHER_AVATAR_PATH);
        teacherDO.setAvatar(avatar);

        return convertFromTeacherDO(teacherDO);
    }

    /**
     * @author Lyy
     * 教师个人信息页展示VO
     */
    private TeacherVO convertFromTeacherDO(TeacherDO teacherDO) {
        if (teacherDO == null) {
            return null;
        }
        TeacherVO teacherVO = new TeacherVO();
        BeanUtils.copyProperties(teacherDO, teacherVO);
        return teacherVO;
    }

    /**
     * @author Lyy
     * 检查编辑后的信息是否合理，是则修改
     */
    @Override
    public void checkEditInfo(String telephone, String avatar) throws BusinessException {
        // 获取session中的教师id
        Integer teacherId = (Integer) (MySessionUtil.getAttribute(Constants.TEACHER_ID_SESSION));

        TeacherDO teacherDO = teacherDOMapper.selectByPrimaryKey(teacherId);

        //图片格式大小判断待处理

        //如果新图片和原图片相同，则设置为null，否则设置为新图片的路径
        teacherDO.setAvatar(MyImageUtil.replaceImage(avatar, teacherDO.getAvatar(), Constants.TEACHER_AVATAR_PATH));

        if (telephone.equals((teacherDO.getTelephone()))) {
            teacherDO.setTelephone(null);
        } else {//有改动电话号码则需要判断是否已被注册
            if (teacherDOMapper.selectByTelephone(telephone) != null) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "手机号已被注册");
            } else {
                teacherDO.setTelephone(telephone);
            }
        }
        teacherDOMapper.updateByPrimaryKeySelective(teacherDO);
    }

    /**
     * @author Tzj
     * 获取教师列表
     */
    @Override
    public List<TeacherDO> getTeacherList() {
        return teacherDOMapper.getTeacherList();
    }
}
