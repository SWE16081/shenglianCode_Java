package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.ChildVO;
import com.youjiao.demo.dataobject.StudentDO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tzj
 * 2019/03/07 20:29
 */
@Service
public interface StudentService {
    /**
     * @author Tzj
     * 通过家长ID获得该家长已添加的孩子列表
     */
    List<ChildVO> getStudentsByParentId(Integer parentId);

    /**
     * @author Tzj
     * 家长通过输入孩子身份证添加孩子
     */
    void addChild(Integer parentId, String idNumber) throws BusinessException;

    /**
     * @author Tzj
     * 通过孩子身份证查找孩子
     */
    StudentDO getStudentByIdNumber(String idNumber);
}
