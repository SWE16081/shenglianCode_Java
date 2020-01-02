package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.dataobject.ChapterDO;
import com.youjiao.demo.dataobject.CourseDO;
import com.youjiao.demo.dataobject.TextbookDO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Zjp
 * 2019/04/14
 */
public interface AdminCourseService {
    /**
     * @author Zjp
     * 根据章节id，将单个教案文件上传到对应的课程id目录下
     * 如果该章节已经存在教案文件，则删除旧文件，上传新文件
     */
    void uploadLessonPlan(MultipartFile file, Integer chapterId) throws BusinessException;

    /**
     * @author Zjp
     * 根据章节id，获取教案记录对象，然后获取文件路径，根据路径删除教案文件，并更新教案记录对象
     */
    void deleteLessonPlan(Integer chapterId) throws BusinessException;
    /**
     * @author Sxl
     * 返回所有课程
     */
    List<AdminCourseListVO> getAllCourseList()throws BusinessException;

    /**
     * @author Sxl
     * 获取所有章节
     */
    List<AdminChapterVO> getChapter(Integer courseId)throws BusinessException;

    /**
     * @author Sxl
     * 获取所有章节及其教案情况
     */
    List<AdminChapterWithLessonPlanVO> getChapterWithLessonPlan(Integer courseId)throws BusinessException;

    /**
     * @author Sxl
     * 添加课程
     */
    void addCourse(CourseDO courseDO, TextbookDO textbookDO, List<ChapterDO> chapterDOList)throws BusinessException;

    /**
     * @author Sxl
     * 删除课程
     */
    void deleteCourse(List<Integer> courseIdList)throws BusinessException;

    /**
     * @author Sxl
     * 修改课程
     */
    void updateCourse(AdminCourseAlterVO adminCourseAlterVO)throws BusinessException;

    /**
     * @author Sxl
     * 添加一级分类
     */
    void addFirstClassify(String name)throws BusinessException;

    /**
     * @author Sxl
     * 添加二级分类
     */
    void addSecondClassify(Integer firstId,String name)throws BusinessException;

    /**
     * @author Sxl
     * 删除一级分类
     */
    void deleteFirstClassify(Integer firstId)throws BusinessException;

    /**
     * @author Sxl
     * 删除二级分类
     */
    void deleteSecondClassify(Integer secondId)throws BusinessException;

    /**
     * @author Sxl
     * 获取分类
     */
    List<AdminFirstClassifyVO> getClassify()throws BusinessException;
    /**
     * @author Ck
     * 获取所有一级分类及子分类信息
     * 以element-ui中cascader 的格式获取包括分类的课程章节信息, 结构为: 一级分类 -> 二级分类 -> 课程 -> 章节
     */
    List<ScheduleFirstClassifyVO> getScheduleFirstClassify();
}
