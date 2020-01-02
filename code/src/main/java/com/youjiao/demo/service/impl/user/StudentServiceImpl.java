package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.ChildVO;
import com.youjiao.demo.dao.StudentDOMapper;
import com.youjiao.demo.dataobject.StudentDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.user.StudentService;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tzj
 * 2019/03/07 20:31
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDOMapper studentDOMapper;

    public StudentServiceImpl(StudentDOMapper studentDOMapper) {
        this.studentDOMapper = studentDOMapper;
    }

    /**
     * @author Tzj
     * 通过家长ID获得该家长已添加的孩子列表
     */
    @Override
    public List<ChildVO> getStudentsByParentId(Integer parentId) {
        return studentDOMapper.getStudentsByParentId(parentId);
    }


    /**
     * @author Tzj
     * 家长通过输入孩子身份证添加孩子
     */
    @Override
    public void addChild(Integer parentId, String idNumber) throws BusinessException {
        try {
            studentDOMapper.addChild(parentId, idNumber);
        } catch (Exception e) {
           // MyLog.error("父母\"" + parentId + "\"添加孩子失败\t身份证号为\"" + idNumber + "\"\n" + "失败原因为:"
                   // + MyExceptionUtil.getErrorMsg(e));
            throw new BusinessException(EmBusinessErr.CHILD_ADD_FAILED);
        }
    }

    /**
     * @author Tzj
     * 通过孩子身份证查找孩子
     */
    @Override
    public StudentDO getStudentByIdNumber(String idNumber) {
        return studentDOMapper.getStudentByIdNumber(idNumber);
    }
}
