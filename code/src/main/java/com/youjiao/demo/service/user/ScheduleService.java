package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.ScheduleRowVO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author Ck
 * #date 2019/07/14 12:46
 */
@Service
public interface ScheduleService {
    /**
     * @author Ck
     * 根据时间和学期id获取课程计划
     */
    List<ScheduleRowVO> getScheduleWeekList(Date date, Byte grade) throws BusinessException;
}
