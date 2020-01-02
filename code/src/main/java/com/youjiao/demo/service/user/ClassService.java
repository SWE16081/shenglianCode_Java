package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.ClassListVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ck
 * #date 2019/03/02 13:12
 */
@Service
public interface ClassService {

    /**
     * @author Ck
     * ����teacherId,isArchive��ȡ��ת���༶�б�ΪClassListVO
     */
    List<ClassListVO> getClassListByTeacherIdAndIsArchive(Integer teacherId, boolean isArchive);
}