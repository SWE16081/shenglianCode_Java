package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.dao.*;
import com.youjiao.demo.dataobject.*;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminCourseService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyFileUtil;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zjp
 * 2019/04/14
 */
@Service
public class AdminCourseImpl implements AdminCourseService {

    private final String CHAPTER_NOT_EXIST = "章节不存在";
    private final String LESSON_PLAN_NOT_EXIST = "文件不存在";

    private final ChapterDOMapper chapterDOMapper;
    private final LessonPlanDOMapper lessonPlanDOMapper;
    private final CourseDOMapper courseDOMapper;
    private final TextbookDOMapper textbookDOMapper;
    private final FirstClassificationDOMapper firstClassificationDOMapper;
    private final SecondClassificationDOMapper secondClassificationDOMapper;

    @Autowired
    public AdminCourseImpl(ChapterDOMapper chapterDOMapper, LessonPlanDOMapper lessonPlanDOMapper, CourseDOMapper courseDOMapper, TextbookDOMapper textbookDOMapper, FirstClassificationDOMapper firstClassificationDOMapper, SecondClassificationDOMapper secondClassificationDOMapper) {
        this.chapterDOMapper = chapterDOMapper;
        this.lessonPlanDOMapper = lessonPlanDOMapper;
        this.courseDOMapper = courseDOMapper;
        this.textbookDOMapper = textbookDOMapper;
        this.firstClassificationDOMapper = firstClassificationDOMapper;
        this.secondClassificationDOMapper = secondClassificationDOMapper;
    }

    /**
     * @author Zjp
     * 根据章节id，将单个教案文件上传到对应的课程id目录下
     * 如果该章节已经存在教案文件，则删除旧文件，上传新文件
     */
    @Override
    public void uploadLessonPlan(MultipartFile file, Integer chapterId) throws BusinessException {
        //获得系统当前时间，作为文件提交时间
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        //获取章节id所属的课程id，作为保存教案文件的目录名
        Integer courseId = chapterDOMapper.getCourseIdByChapterId(chapterId);

        //如果该章节已经有旧文件，则删除旧文件
        if (lessonPlanDOMapper.selectByChapterId(chapterId) != null) {
            deleteLessonPlan(chapterId);
        }
        //添加新的章节教案
        //文件名
        String filename = file.getOriginalFilename();
        //服务器中存储文件的路径
        String filepath = courseId.toString() + "/" + filename;

        //同一目录下文件名不能重复
        if (lessonPlanDOMapper.selectByFileUrl(filepath) != null) {
            throw new BusinessException(EmBusinessErr.FILE_UPLOAD_FAILED, "教案文件名在该课程中已存在");
        }

        //上传文件
        MyFileUtil.upload(file, Constants.LESSON_PLAN_FILE_PATH + filepath);
        //教案对象
        LessonPlanDO lessonPlanDO = new LessonPlanDO();
        lessonPlanDO.setChapterId(chapterId);
        lessonPlanDO.setCommitTime(nowTime);
        lessonPlanDO.setFileUrl(filepath);
        lessonPlanDO.setFilename(filename);
        //添加教案记录
        try {
            lessonPlanDOMapper.insert(lessonPlanDO);
        } catch (Exception e) {
            //添加到数据库失败就删除文件
            MyFileUtil.deleteFile(Constants.LESSON_PLAN_FILE_PATH + filepath);
            throw new BusinessException(e,EmBusinessErr.FILE_UPLOAD_FAILED);
        }
    }



    /**
     * @author Zjp
     * 删除单个教案
     * 根据章节id，获取教案记录对象，然后获取文件路径，根据路径删除教案文件，并更新教案记录对象
     */
    @Override
    public void deleteLessonPlan(Integer chapterId) throws BusinessException {
        //获取教案记录对象
        LessonPlanDO lessonPlanDO = lessonPlanDOMapper.selectByChapterId(chapterId);

        //保存删除文件的路径
        String fileUrl = lessonPlanDO.getFileUrl();

        //删除该教案记录
        try {
            lessonPlanDOMapper.deleteByChapterId(chapterId);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.FILE_DELETE_FAILED);
        }

        //删除文件夹存放文件
        MyFileUtil.deleteFile(Constants.LESSON_PLAN_FILE_PATH + fileUrl);
    }


    /**
     * @author Sxl
     * 获得课程列表
     */
    @Override
    public List<AdminCourseListVO> getAllCourseList() {
        //查询课程表所有未被逻辑删除的课程
        return courseDOMapper.selectAllCourse();
    }


    /**
     * @author Sxl
     * 获得课程章节
     */
    @Override
    public List<AdminChapterVO> getChapter(Integer courseId) {
        //查询对应课程的所有章节（未被逻辑删除）
        List<ChapterDO> chapterDOList = chapterDOMapper.selectByCourseId(courseId);
        List<AdminChapterVO> chapterVOList = new ArrayList<>();
        for (ChapterDO chapterDO : chapterDOList) {
            AdminChapterVO chapterVO = new AdminChapterVO();
            chapterVO.setChapterId(chapterDO.getChapterId());
            chapterVO.setName(chapterDO.getName());
            chapterVO.setNumber(chapterDO.getNumber());
            chapterVO.setDescription(chapterDO.getDescription());
            chapterVOList.add(chapterVO);
        }
        return chapterVOList;
    }

    /**
     * @author Sxl
     * 获得课程章节及其教案情况
     */
    @Override
    public List<AdminChapterWithLessonPlanVO> getChapterWithLessonPlan(Integer courseId) {
        return chapterDOMapper.selectChapterByCourseId(courseId);
    }

    /**
     * @author Sxl
     * 添加课程
     */
    @Override
    public void addCourse(CourseDO courseDO, TextbookDO textbookDO, List<ChapterDO> chapterDOList) throws BusinessException {
        courseDO.setAlive(true);
        int id;
        try {
            //添加课程基本信息，获取插入课程的courseId
            courseDOMapper.insert(courseDO);
            id = courseDO.getCourseId();
            //插入章节
            for (ChapterDO chapterDO : chapterDOList) {
                //添加章节
                addChapter(chapterDO, id);
            }
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.COURSE_ADD_FAILED);
        }
        //该门课程有教材 添加教材
        if (courseDO.getUseTextbook()) {
            try {
                //设置教材表courseId
                textbookDO.setCourseId(id);
                //插入对应教材
                textbookDOMapper.insert(textbookDO);
            } catch (Exception e) {
                throw new BusinessException(e,EmBusinessErr.TEXTBOOK_ADD_FAILED);
            }
        }
    }

    /**
     * @author Sxl
     * 批量逻辑删除课程
     */
    @Override
    public void deleteCourse(List<Integer> courseIdList) throws BusinessException {
        try {
            //批量逻辑删除章节
            chapterDOMapper.logicDeleteChapterList(courseIdList);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.CHAPTER_DELETE_FAILED);
        }
        try {
            //批量逻辑删除课程
            courseDOMapper.deleteCourseList(courseIdList);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.COURSE_DELETE_FAILED);
        }
    }

    /**
     * @author Sxl
     * 添加章节
     */
    private void addChapter(ChapterDO chapterDO, Integer courseId) throws BusinessException {
        //设置章节表courseId和状态（是否删除）
        chapterDO.setCourseId(courseId);
        chapterDO.setAlive(true);
        chapterDO.setChapterId(null);
        try {
            chapterDOMapper.insert(chapterDO);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.CHAPTER_ADD_FAILED);
        }
    }

    /**
     * @author Sxl
     * 修改课程
     */
    @Override
    public void updateCourse(AdminCourseAlterVO adminCourseAlterVO) throws BusinessException {
        Integer courseId = adminCourseAlterVO.getCourseId();
        //删除该课程章节列表
        try {
            chapterDOMapper.deleteChapterByCourseId(courseId);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.CHAPTER_DELETE_FAILED);
        }
        //添加新的章节列表且原有的章节id不变
        List<AdminChapterVO> chapterVOList = adminCourseAlterVO.getChapter();
        for (AdminChapterVO chapterVO : chapterVOList) {
            ChapterDO chapterDO = new ChapterDO();
            BeanUtils.copyProperties(chapterVO, chapterDO);
            //添加章节
            addChapter(chapterDO, courseId);
        }
        try {
            //删除原来的教材
            textbookDOMapper.deleteByCourseId(courseId);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.TEXTBOOK_DELETE_FAILED);
        }
        //插入新的教材
        TextbookDO textbookDO = new TextbookDO();
        BeanUtils.copyProperties(adminCourseAlterVO, textbookDO);
        try {
            textbookDOMapper.insert(textbookDO);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.TEXTBOOK_ADD_FAILED);
        }
        try {
            //删除原来的课程
            courseDOMapper.deleteByPrimaryKey(courseId);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.COURSE_DELETE_FAILED);
        }
        //插入修改后新的课程
        CourseDO courseDO = new CourseDO();
        BeanUtils.copyProperties(adminCourseAlterVO, courseDO);

        if (courseDO.getFirstId() == null) {
            courseDO.setFirstId(0);
        }
        if (courseDO.getSecondId() == null) {
            courseDO.setSecondId(0);
        }
        courseDO.setAlive(true);
        try {
            courseDOMapper.insert(courseDO);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.COURSE_ADD_FAILED);
        }
    }

    /**
     * @author Sxl
     * 添加一级分类
     */
    @Override
    public void addFirstClassify(String name) throws BusinessException {
        //创建一个一级分类实例
        FirstClassificationDO firstClassificationDO = new FirstClassificationDO();
        firstClassificationDO.setFirstName(name);
//        System.out.println("一级"+name);
        //在数据库添加一级分类
        try {
            firstClassificationDOMapper.insert(firstClassificationDO);
            //添加一个默认二级分类
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.FIRST_CLASSIFY_ADD_FAILED);
        }
    }

    /**
     * @author Sxl
     * 添加二级分类
     */
    @Override
    public void addSecondClassify(Integer firstId, String name) throws BusinessException {
        //创建一个二级分类实例
        SecondClassificationDO secondClassificationDO = new SecondClassificationDO();
        secondClassificationDO.setSecondName(name);
        secondClassificationDO.setFirstId(firstId);
        //在数据库添加二级分类
        try {
            secondClassificationDOMapper.insert(secondClassificationDO);
        } catch (Exception e) {
            throw new BusinessException(e,EmBusinessErr.SECOND_CLASSIFY_ADD_FAILED);
        }
    }

    /**
     * @author Sxl
     * 删除一级分类
     */
    @Override
    public void deleteFirstClassify(Integer firstId) throws BusinessException {
        //获取该分类下的课程
        List<CourseDO> courseDOList = courseDOMapper.selectByFirstId(firstId);
        //当前分类不包含课程
        if (courseDOList.isEmpty()) {
            try {
                secondClassificationDOMapper.deleteByFirstId(firstId);
            } catch (Exception e) {
                throw new BusinessException(e,EmBusinessErr.SECOND_CLASSIFY_DELETE_FAILED);
            }
            try {
                firstClassificationDOMapper.deleteByPrimaryKey(firstId);
            } catch (Exception e) {
                throw new BusinessException(e,EmBusinessErr.SECOND_CLASSIFY_DELETE_FAILED);
            }
        } else {
            throw new BusinessException(EmBusinessErr.CLASSIFY_CONTAINS_COURSE);
        }
    }

    /**
     * @author Sxl
     * 删除二级分类
     */
    @Override
    public void deleteSecondClassify(Integer secondId) throws BusinessException {
        //二级分类的删除
        //获取当前二级分类下的课程
        List<CourseDO> courseDOList = courseDOMapper.selectBySecondId(secondId);
        if (courseDOList.isEmpty()) {
            //当前分类不包含课程
            try {
                secondClassificationDOMapper.deleteByPrimaryKey(secondId);
            } catch (Exception e) {
                throw new BusinessException(e,EmBusinessErr.SECOND_CLASSIFY_DELETE_FAILED);
            }
        } else {
            throw new BusinessException(EmBusinessErr.CLASSIFY_CONTAINS_COURSE);
        }
    }

    /**
     * @author Sxl
     * 获取分类
     */
    @Override
    public List<AdminFirstClassifyVO> getClassify() {
        //获取所有的一级分类
        List<FirstClassificationDO> firstClassificationDOList = firstClassificationDOMapper.selectAll();

        List<AdminFirstClassifyVO> adminFirstClassifyVOList = new ArrayList<>();
        for (FirstClassificationDO firstClassificationDO : firstClassificationDOList) {
            //每一个一级分类
            AdminFirstClassifyVO classifyVO1 = new AdminFirstClassifyVO();
            //赋值
            Integer firstId = firstClassificationDO.getFirstId();
            List<SecondClassificationDO> secondClassificationDOList = secondClassificationDOMapper.selectByFirstId(firstId);
            BeanUtils.copyProperties(firstClassificationDO, classifyVO1);//把firstClassificationDO复制到classifyVO1
            classifyVO1.setSecondClassificationDOList(secondClassificationDOList);
            adminFirstClassifyVOList.add(classifyVO1);
        }
        return adminFirstClassifyVOList;
    }


    /**
     * @author Ck
     * 获取所有一级分类及子分类信息
     * 以element-ui中cascader 的格式获取包括分类的课程章节信息, 结构为: 一级分类 -> 二级分类 -> 课程 -> 章节
     */
    @Override
    public List<ScheduleFirstClassifyVO> getScheduleFirstClassify() {
        //获取一级分类
        List<ScheduleFirstClassifyVO> list = firstClassificationDOMapper.selectScheduleFirstClassify();
        for (ScheduleFirstClassifyVO first : list) {
            //获取二级分类
            List<ScheduleSecondClassifyVO> secondList = secondClassificationDOMapper.selectScheduleSecondClassify(first.getId());
            first.setChildren(secondList);
            for (ScheduleSecondClassifyVO second : secondList) {
                //获取课程
                List<ScheduleCourseVO> courseList = courseDOMapper.selectScheduleCourse(second.getId());
                second.setChildren(courseList);
                for (ScheduleCourseVO course : courseList) {
                    //获取章节
                    List<ScheduleChapterVO> chapterList = chapterDOMapper.selectScheduleChapter(course.getId());
                    course.setChildren(chapterList);
                }
            }
        }
        return list;
    }
}
