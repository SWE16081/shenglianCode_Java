package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminSchedulePostVO;
import com.youjiao.demo.controller.viewobject.admin.AdminScheduleRowVO;
import com.youjiao.demo.controller.viewobject.admin.AdminScheduleVO;
import com.youjiao.demo.error.BusinessException;

import java.sql.Date;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/16 15:07
 */
public interface AdminScheduleService {
    /**
     * @author Ck
     * 根据日期和学期id查询一周的课程计划表
     */
    List<AdminScheduleRowVO> getScheduleWeekList(Date date, Integer termId);

    /**
     * @author Ck
     * 添加一周的课程计划表
     */
    void addScheduleWeek(AdminSchedulePostVO adminSchedulePost, Date date) throws BusinessException;

    /**
     * @author Ck
     * 删除一周的课程计划表
     */
    void deleteScheduleWeek(Date date,Integer termId) throws BusinessException;

    /**
     * @author Ck
     * 修改一周的课程计划表
     */
    void modifyScheduleWeek(AdminSchedulePostVO adminSchedulePost) throws BusinessException;

    /**
     * @author Ck
     * 根据id查询课程计划表
     */
    AdminScheduleVO getScheduleById(Integer id);
}
