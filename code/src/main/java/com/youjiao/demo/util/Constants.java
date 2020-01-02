package com.youjiao.demo.util;

public final class Constants {
    public static final int TEACHER = 0;
    public static final int PARENT = 1;

    //标记用户和管理员是否登录成功
    public static final String USER_LOGIN_SESSION ="userLogin";
    //教师id
    public static final String TEACHER_ID_SESSION = "teacherId";
    //家长id
    public static final String PARENT_ID_SESSION = "parentId";
    //机构id
    public static final String SCHOOL_ID_SESSION = "schoolId";
    //机构名称
    public static final String SCHOOL_NAME_SESSION = "schoolName";

    //管理员名称和权限
    public static final String JURISDICTION_SESSION = "jurisdiction";
    public static final String USER_NAME_SESSION = "adminName";

    public static final String TEACHER_NAME_MAP = "teacherName";

    public static final String VERIFICATION_CODE = "verificationCode";

    public static final String PARENT_AVATAR_PATH = "/static/images/upload/parentAvatar/";
    public static final String TEACHER_AVATAR_PATH = "/static/images/upload/teacherAvatar/";
    public static final String HOMEWORK_FILE_PATH = "/static/images/upload/homeworkFile/";
    public static final String DISHES_PIC_PATH = "/static/images/upload/dishesImage/";
    public static final String LESSON_PLAN_FILE_PATH = "/static/files/upload/lessonPlan/";
    //菜谱类型
    public static final Byte BREAK_DISH = 0;
    public static final Byte LUNCH_DISH = 1;
    public static final Byte NOON_DISH = 2;

    //学年的分隔点
    public static final byte TERM_YEAR_EXPLODE_ON_MONTH = 7;
    //学期的分隔点
    public static final byte TERM_NUM_EXPLODE_ON_MONTH = 2;
    //第一个学期
    public static final byte FIRST_TERM_NUM = 0;
    //第二个学期
    public static final byte SECOND_TERM_NUM = 1;



    /**
     * 表示逻辑删除中的“未删除”
     */
    public static final Boolean ALIVE = true;

    //固定课程表中的类型
    public static final byte SCHEDULE_TYPE_HOLIDAY = 0;
    public static final byte SCHEDULE_TYPE_FIXTURE = 1;
    public static final byte SCHEDULE_TYPE_COURSE = 2;
    public static final byte SCHEDULE_TYPE_ACTIVITY = 3;

    //管理员权限
    //超级园长
    public static final byte ADMIN_JURISDICTION_TOP = 0;
    //园长
    public static final byte ADMIN_JURISDICTION_MIDDLE = 1;
    //普通管理员
    public static final byte ADMIN_JURISDICTION_LOWER = 2;
    //代理商园长
    public static final byte AGENTS_JURISDICTION_TOP = 3;
    //代理商的普通管理员
    public static final byte AGENTS_JURISDICTION_LOWER = 4;
    //教师
    public static final byte USER_JURISDICTION_TEACHER = 0;
    //家长
    public static final byte USER_JURISDICTION_PARENT = 1;


    public static final String FILE_PATH= "/static/images/upload/parentAvatar/avatarParent.jpg";

}
