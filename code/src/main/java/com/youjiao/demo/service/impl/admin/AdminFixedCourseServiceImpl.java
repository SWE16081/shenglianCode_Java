package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminFixedCourseVO;
import com.youjiao.demo.dao.FixedCourseDOMapper;
import com.youjiao.demo.dao.ScheduleDOMapper;
import com.youjiao.demo.dataobject.FixedCourseDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminFixedCourseService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 16:44
 */
@Service
public class AdminFixedCourseServiceImpl implements AdminFixedCourseService {
    private final FixedCourseDOMapper fixedCourseDOMapper;
    private final ScheduleDOMapper scheduleDOMapper;

    public AdminFixedCourseServiceImpl(FixedCourseDOMapper fixedCourseDOMapper, ScheduleDOMapper scheduleDOMapper) {
        this.fixedCourseDOMapper = fixedCourseDOMapper;
        this.scheduleDOMapper = scheduleDOMapper;
    }

    /**
     * @author Ck
     * 添加固定课程
     */
    @Override
    public void addFixedCourse(String name) throws BusinessException {
        List<FixedCourseDO> sameList = fixedCourseDOMapper.selectByName(name,Constants.ALIVE);
        if(!sameList.isEmpty()){
            throw new BusinessException(EmBusinessErr.FIXED_COURSE_ADD_FAILED_REPEAT);
        }
        try {
            fixedCourseDOMapper.insertFixtureByName(name, Constants.ALIVE);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.FIXED_COURSE_ADD_FAILED);
        }
    }

    /**
     * @author Ck
     * 逻辑删除固定课程
     * 该方法在运行环境下不使用，保留该方法用于开发测试
     */
    @Deprecated
    @Override
    public void logicalDeleteFixedCourse(Integer id) throws BusinessException {
        try {
            fixedCourseDOMapper.logicalDeleteById(id, !Constants.ALIVE);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.FIXED_COURSE_DELETE_FAILED);
        }
    }

    /**
     * @author Ck
     * 修改固定课程
     * 先插入修改后的固定课程 插入值为VO中的name
     * 逻辑删除VO中id对应的固定课程
     * 再根据原有VO中的id 查找
     * 所有服务器时间之后(含当日) 和 修改前固定课程id 的 课程计划表
     * 将搜索的课程计划表的固定课程id更新为新插入的固定课程id
     */
    @Transactional
    @Override
    public void modifyFixedCourse(AdminFixedCourseVO fixedCourseVO) throws BusinessException {
        List<FixedCourseDO> sameList = fixedCourseDOMapper.selectByName(fixedCourseVO.getName(),Constants.ALIVE);
        if(!sameList.isEmpty()){
            throw new BusinessException(EmBusinessErr.FIXED_COURSE_MODIFY_FAILED_REPEAT);
        }
        try {
            //保存旧课程的id号
            Integer oldId = fixedCourseVO.getFixedCourseId();
            //添加固定课程
            FixedCourseDO fixedCourseDO = new FixedCourseDO();
            BeanUtils.copyProperties(fixedCourseVO, fixedCourseDO);
            fixedCourseDO.setAlive(Constants.ALIVE);
            fixedCourseDOMapper.insertFixture(fixedCourseDO);
            //逻辑删除旧课程
            fixedCourseDOMapper.logicalDeleteById(oldId, !Constants.ALIVE);

            Integer newId = fixedCourseDO.getFixedCourseId();
            Date today = new Date(System.currentTimeMillis());

            //System.out.println("原id:" + oldId + " 新id:" + newId + " 现在时间:" + today);

            scheduleDOMapper.updateNewActivityIdByOldActivityId(oldId, newId, today, Constants.SCHEDULE_TYPE_FIXTURE);

        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.FIXED_COURSE_MODIFY_FAILED);
        }
    }

    /**
     * @author Ck
     * 获取固定课程列表
     */
    @Override
    public List<AdminFixedCourseVO> getFixedCourseList() {
        List<AdminFixedCourseVO> list;
        list = fixedCourseDOMapper.selectFixtureAll(Constants.ALIVE);
        return list;
    }
}
