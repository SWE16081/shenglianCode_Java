package com.youjiao.demo.util;

import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lyy
 * #date 2019/04/21 14:32
 */
public class SessionStuUtil {
    /**
     * @author Lyy
     * 获取session中的机构id
     */
    public static Integer initSchoolId(HttpServletRequest httpServletRequest) throws BusinessException {
        Integer schoolId = (Integer) (httpServletRequest.getSession().getAttribute(Constants.SCHOOL_ID_SESSION));
        if (schoolId == null) {
            MyLog.error("session中未找到机构id");
            throw new BusinessException(EmBusinessErr.USER_NOT_EXIST);
        }
        return schoolId;
    }

}
