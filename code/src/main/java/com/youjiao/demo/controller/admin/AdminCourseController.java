package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.dataobject.ChapterDO;
import com.youjiao.demo.dataobject.CourseDO;
import com.youjiao.demo.dataobject.TextbookDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminCourseService;
import com.youjiao.demo.validator.MyValidation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author Zjp
 * 2019/04/14
 * 课程管理
 */
@Controller("adminCourse")
@RequestMapping("/admin/course")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class AdminCourseController extends BaseController {

    private final AdminCourseService adminCourseService;

    @Autowired
    public AdminCourseController(AdminCourseService adminCourseService) {
        this.adminCourseService = adminCourseService;
    }

    /**
     * @author Sxl
     * 获得课程列表
     */
    @GetMapping("/getCourseList")
    @ResponseBody
    public CommonReturnType getAllCourseList() throws BusinessException {
        //获得所有未被逻辑删除的课程
        List<AdminCourseListVO> courseListModel = adminCourseService.getAllCourseList();
        return CommonReturnType.create(courseListModel);

    }

    /**
     * @author Sxl
     * 获得某一课程的章节列表
     */
    @ResponseBody
    @PostMapping("/getChapter")
    public CommonReturnType getChapterByCourseId(@Param("courseId") Integer courseId) throws BusinessException {
        MyValidation.checkIntNull(courseId);
        List<AdminChapterVO> chapterVOList = adminCourseService.getChapter(courseId);
        return CommonReturnType.create(chapterVOList);
    }

    /**
     * @author Sxl
     * 添加课程
     */
    @ResponseBody
    @PostMapping(value = "/addCourse")
    public CommonReturnType addCourse(@Valid @RequestBody AdminCourseAddVO adminCourseAddVO) throws BusinessException {
//        @Valid @RequestBody AdminCourseListVO adminCourseListVO, BindingResult result
        //测试数据
//        AdminCourseAddVO adminCourseAddVO=new AdminCourseAddVO();
//        adminCourseAddVO.setCourseName("书法");
//        adminCourseAddVO.setUseTextbook(true);
//        adminCourseAddVO.setFirstId(3);
//        adminCourseAddVO.setSecondId(1);
//        adminCourseAddVO.setBookName("书法入门经典");
//        adminCourseAddVO.setPublish("清华大学出版社");
//        AdminChapterVO[] chapterVOList = new AdminChapterVO[2];
//        Byte a=1;
//        AdminChapterVO chapterVO = new AdminChapterVO(null,a,"书法概论","认识什么是书法？学习书法的益处");
//        chapterVOList[0]=chapterVO;
//        a=2;
//        AdminChapterVO chapterVO1 = new AdminChapterVO(null,a,"书法入门","学习简单的书法知识");
//        chapterVOList[1]=chapterVO1;
//        adminCourseAddVO.setChapter(chapterVOList);
        //保存数据
        //获取courseDO相关
        MyValidation.checkObjectNull(adminCourseAddVO);
        //用对应的dataobject存储接收的数据
        //course课程基本信息
        CourseDO courseDO = new CourseDO();
        BeanUtils.copyProperties(adminCourseAddVO, courseDO);
        //textBook教材信息
        TextbookDO textbookDO = new TextbookDO();
        BeanUtils.copyProperties(adminCourseAddVO, textbookDO);

        //章节信息
        List<ChapterDO> chapterDOList = new ArrayList<>();
        for (AdminChapterAddVO adminChapterAddVO : adminCourseAddVO.getChapter()) {
            ChapterDO chapterDO = new ChapterDO();
            BeanUtils.copyProperties(adminChapterAddVO, chapterDO);
            chapterDOList.add(chapterDO);
        }
        //service
        adminCourseService.addCourse(courseDO, textbookDO, chapterDOList);
//        MyLog.info("Request : /api/admin/courseManage/addCourse");
        return CommonReturnType.create(null);
    }


    /**
     * @author Sxl
     * 修改课程
     */
    @ResponseBody
    @PostMapping(value = "/updateCourse")
    public CommonReturnType updateCourse(@Valid @RequestBody AdminCourseAlterVO adminCourseAlterVO) throws BusinessException {
        //测试数据
//        AdminCourseAlterVO adminCourseAlterVO=new AdminCourseAlterVO();
//        adminCourseAlterVO.setCourseId(1);
//        adminCourseAlterVO.setCourseName("英语2");
//        adminCourseAlterVO.setUseTextbook(false);
//        adminCourseAlterVO.setFirstId(1);
//        adminCourseAlterVO.setSecondId(null);
//        adminCourseAlterVO.setId(1);
//        adminCourseAlterVO.setBookName("书法入门经典");
//        adminCourseAlterVO.setPublish("陕西工业出版社");
//        List<AdminChapterVO> chapterVOList = new ArrayList<AdminChapterVO>();
//        Byte a=1;
//        AdminChapterVO chapterVO = new AdminChapterVO(null,a,"概论","认识什么是书法？学习书法的益处");
//        chapterVOList.add(chapterVO);
//        a=2;
//        AdminChapterVO chapterVO1 = new AdminChapterVO(null,a,"入门","学习简单的书法知识");
//        chapterVOList.add(chapterVO1);
//        a=3;
//        AdminChapterVO chapterVO2 = new AdminChapterVO(null,a,"字体学习","掌握基本的书法字体结构");
//        chapterVOList.add(chapterVO2);
//        adminCourseAlterVO.setChapter(chapterVOList);
//        if (result.hasErrors()) {
//            MyLog.error( Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
//            throw new BusinessException(EmBusinessErr.COURSE_MODIFY_FAILED);
//        }
        MyValidation.checkObjectNull(adminCourseAlterVO);
        adminCourseService.updateCourse(adminCourseAlterVO);

//        MyLog.info("Request : /api/admin/courseManage/updateCourse");
        return CommonReturnType.create(null);
    }


    /**
     * @author Sxl
     * 删除或批量删除课程
     */
    @ResponseBody
    @PostMapping(value = "/deleteCourse")
    public CommonReturnType deleteCourse(@Valid @RequestBody ArrayList<Integer> courseIdList, BindingResult result) throws BusinessException {
//        courseIdList.add(1);
        if (result.hasErrors()) {
            System.out.println("Request-error : /api/admin/course/deleteCourse\t[" + Objects.requireNonNull(result.getFieldError()).getDefaultMessage() + "]");
            throw new BusinessException(EmBusinessErr.COURSE_DELETE_FAILED);
        }
        MyValidation.checkObjectNull(courseIdList);
        adminCourseService.deleteCourse(courseIdList);
//        MyLog.info("Request : /api/admin/courseManage/deleteCourse");
        return CommonReturnType.create(null);
    }


    /**
     * @author Sxl
     * 获取所有分类
     */
    @GetMapping("/getClassify")
    @ResponseBody
    public CommonReturnType getClassifyList() throws BusinessException {
        List<AdminFirstClassifyVO> adminFirstClassifyVO = adminCourseService.getClassify();
//        MyLog.info("Request : /api/admin/courseManage/getClssify");
        return CommonReturnType.create(adminFirstClassifyVO);
    }


    /**
     * @author Sxl
     * 增加一级分类
     */
    @ResponseBody
    @PostMapping("/addFirstClassify")
    public CommonReturnType addFirstClassify(@RequestParam("firstName") String name) throws BusinessException {

        //测试数据
//        AdminClassifyAddVO adminClassifyAddVO = new AdminClassifyAddVO();
//        adminClassifyAddVO.setFirst_name("数学");
//        adminClassifyAddVO.setSecond_name("算术");
        MyValidation.checkStrNull(name);
        adminCourseService.addFirstClassify(name);
//        MyLog.info("Request : /api/admin/courseManage/addClassify");
        return CommonReturnType.create(null);

    }

    /**
     * @author Sxl
     * 增加二级分类
     */
    @ResponseBody
    @PostMapping("/addSecondClassify")
    public CommonReturnType addSecondClassify(@RequestParam(name = "firstId") Integer first_id,
                                              @RequestParam(name = "secondName") String name) throws BusinessException {
        MyValidation.checkObjectNull(first_id);
        MyValidation.checkObjectNull(name);
        adminCourseService.addSecondClassify(first_id, name);
//        MyLog.info("Request : /api/admin/courseManage/addClassify");
        return CommonReturnType.create(null);
    }

    /**
     * @author Sxl
     * 删除一级分类
     */
    @ResponseBody
    @PostMapping("/deleteFirstClassify")
    public CommonReturnType deleteClassify(@RequestParam(name = "firstId") Integer firstId) throws BusinessException {
        MyValidation.checkIntNull(firstId);
        adminCourseService.deleteFirstClassify(firstId);
//        MyLog.info("Request : /api/admin/courseManage/deleteClassify");
        return CommonReturnType.create(null);
    }

    /**
     * @author Sxl
     * 删除二级分类
     */
    @ResponseBody
    @PostMapping("/deleteSecondClassify")
    public CommonReturnType deleteSecondClassify(@RequestParam(name = "secondId") Integer secondId) throws BusinessException {
        MyValidation.checkIntNull(secondId);
        adminCourseService.deleteSecondClassify(secondId);
//        MyLog.info("Request : /api/admin/courseManage/deleteClassify");
        return CommonReturnType.create(null);
    }

    /**
     * @author Sxl
     * 根据课程id，获取其章节列表及其教案记录,查看是否存在教案
     */
    @ResponseBody
    @PostMapping("/getChapterWithLessonPlan")
    public CommonReturnType getChapterWithLessonPlanByCourseId(@Param("courseId") Integer courseId) throws BusinessException {
        MyValidation.checkIntNull(courseId);
        List<AdminChapterWithLessonPlanVO> chapterVOList = adminCourseService.getChapterWithLessonPlan(courseId);
        return CommonReturnType.create(chapterVOList);
    }

    /**
     * @author Zjp
     * 上传单个教案文件
     * 如果该章节已经存在教案文件，则删除旧文件，上传新文件
     */
    @PostMapping(value = "uploadLessonPlan", consumes = {CONTENT_TYPE_MULTIPART})
    @ResponseBody
    public CommonReturnType uploadLessonPlan(MultipartFile file,
                                             @RequestParam(name = "chapterId") Integer chapterId) throws BusinessException {
        MyValidation.checkIntNull(chapterId);
        MyValidation.checkObjectNull(file);
        //上传单个教案文件文件到服务器
        adminCourseService.uploadLessonPlan(file, chapterId);
        return CommonReturnType.create(null);
    }

    /**
     * @author Zjp
     * 删除单个教案文件
     */
//    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "deleteLesson")//consumes = {CONTENT_TYPE_MULTIPART})
    public CommonReturnType deleteLesson(@RequestParam("chapterId") Integer chapterId) throws BusinessException {

        MyValidation.checkIntNull(chapterId);
        //根据章节id，删除对应的教案文件
        adminCourseService.deleteLessonPlan(chapterId);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 以element-ui中cascader 的格式获取包括分类的课程章节信息, 结构为: 一级分类 -> 二级分类 -> 课程 -> 章节
     */
    @GetMapping("getCourseWithClassify")
    @ResponseBody
    public CommonReturnType getCourseWithClassify() throws BusinessException {
        List<ScheduleFirstClassifyVO> list = adminCourseService.getScheduleFirstClassify();
        return CommonReturnType.create(list);
    }
}
