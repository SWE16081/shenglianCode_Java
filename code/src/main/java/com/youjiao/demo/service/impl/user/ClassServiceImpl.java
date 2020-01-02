package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.ClassListVO;
import com.youjiao.demo.dao.ClassDOMapper;
import com.youjiao.demo.service.user.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ck
 * #date 2019/03/02 13:14
 */
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassDOMapper classDOMapper;

    @Autowired
    public ClassServiceImpl(ClassDOMapper classDOMapper) {
        this.classDOMapper = classDOMapper;
    }

    /**
     * @author Ck
     * ????teacherId,isArchive???????????б??ClassListVO
     */
    @Override
    public List<ClassListVO> getClassListByTeacherIdAndIsArchive(Integer teacherId, boolean isArchive) {
        return classDOMapper.selectByTeacherIdAndIsArchive(teacherId, isArchive);
    }
}
