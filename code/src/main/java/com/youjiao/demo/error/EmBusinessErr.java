package com.youjiao.demo.error;

public enum EmBusinessErr implements CommonError {
    PARAMETER_INVALIDATION_ERROR(101, "参数不合法"),
    UNKNOWN_ERROR(102, "未知错误"),
    PERMISSION_DENIED(103,"无权限"),
    NOT_LOGIN_USER(104,"未登录"),

    //用户相关错误
    USER_NOT_EXIST(201, "用户不存在"),
    USER_LOGIN_FAILED(202, "用户名或密码不正确"),
    VERIFICATION_CODE_WRONG(203,"验证码错误"),
    TELEPHONE_EXIST(204,"手机号已被注册"),
    USER_REGISTER_TWICE(205,"用户已存在，不可重复注册"),

    //孩子相关错误
    PARENT_ALREADY_EXIST(301, "该孩子已有父母"),
    CHILD_ADD_FAILED(302, "添加孩子失败"),
    CHILD_NOT_EXIST(303, "孩子不存在"),
    UPDATE_CHILD_FAILED(304, "修改孩子信息失败"),
    CHILD_DELETE_FAILED(305,"孩子删除失败"),
    CHILD_SEX_ILLEGAL(306,"学生性别与身份证上不符合"),
    CHILD_ID_NUMBER_ALREADY_EXIST(307,"身份证号已存在"),
    UPDATE_PARENT_FAILED(308,"修改家长信息失败"),

    // 作业相关错误
    HOMEWORK_COMMIT_TIMEOUT(411, "作业提交超时"),
    HOMEWORK_COMMIT_FAILED(412, "作业提交失败"),
    HOMEWORK_ADD_FAILED(413, "作业添加失败"),
    HOMEWORK_ADD_STUDENT_FAILED(414, "添加作业学生列表插入失败"),
    HOMEWORK_MODIFY_FAILED(415, "修改作业失败"),
    HOMEWORK_MODIFY_STUDENT_FAILED(416, "作业修改学生列表重置失败"),

    //图片相关错误
    IMAGE_NOT_EXIST(511, "图片不存在"),
    UPLOAD_IMAGE_FAILED(512, "图片上传失败"),
    DOWNLOAD_IMAGE_FAILED(513, "图片获取失败"),
    IMAGE_DELETE_FAILED(514,"图片删除失败"),

    //公告相关错误
    INSERT_NOTICE_FAILED(601, "公告添加失败"),
    UPDATE_NOTICE_FAILED(602, "公告修改失败"),
    INSERT_NOTICE_UNREAD_FAILED(603, "公告未读人员列表添加失败"),
    DELETE_NOTICE_UNREAD_FAILED(604, "公告未读人员列表删除失败"),

    //菜谱相关错误
    DISHES_COMMIT_FAILED(701, "菜品添加失败"),
    INSERT_DISHES_RECORD_INFO_FAILED(702, "用餐信息插入失败"),
    INSERT_DISHES_RECORD_FAILED(703, "用餐记录插入失败"),
    DELETE_DISHES_RECORD_INFO_FAILED(704, "用餐信息删除失败"),
    DELETE_DISHES_RECORD_FAILED(705, "用餐记录删除失败"),
    UPDATE_DISHES_FAILED(706, "禁止修改以前的菜谱信息"),
    INSERT_DISHES_FAILED(707, "禁止添加以前的菜谱信息"),
    DELETE_DISHES_COMMIT_FAILED(708, "菜品删除失败"),
    INSERT_DISHES_FAILED_REPEAT(709,"菜谱添加失败，已有相同日期的菜谱"),
    DISHES_LIST_NOT_EMPTY(710,"时间/菜品列表不能为空"),
    DISHES_DATE_NOT_EMPTY(711,"时间不能为空"),
    DISHES_COMMIT_FAILED_Name(712,"添加菜品失败，菜名不能重复"),
    DISHES_COMMIT_FAILED_Name_UPDATE(713,"修改菜品失败，菜名不能重复"),

    //班级相关错误
    CLASS_ARCHIVE_MODIFY_FAILED(801, "修改班级归档信息失败"),
    CLASS_ADD_FAILED(802, "创建班级失败"),
    CLASS_MODIFY_FAILED(803, "修改班级失败"),
    CLASS_DELETE_FAILED(804, "删除班级失败"),
    CLASS_ADD_STUDENT_FAILED(805, "班级插入学生失败"),
    CLASS_ADD_TEACHER_FAILED(806, "班级插入教师失败"),
    CLASS_DELETE_STUDENT_FAILED(807, "班级删除学生失败"),
    CLASS_DELETE_TEACHER_FAILED(808, "班级删除教师失败"),


    //登录相关错误
    CODE_WRONG(901, "验证码错误"),
    USER_OR_PASSWORD_WRONG(902, "用户名或密码错误"),
    CONTRACT_EXPIRED(903, "合约已到期"),


    //管理员管理相关错误
    NOT_PERMISSION(1001, "无权限"),
    USER_ALREADY_EXIST(1002, "用户已存在"),

    //代理商相关错误
    INSERT_AGENT_FAILED(1101, "添加代理商失败"),
    UPDATE_AGENT_FAILED(1102, "修改代理商失败"),
    INSERT_CONTRACT_FAILED(1103, "添加合约失败"),
    UPDATE_CONTRACT_FAILED(1104, "修改合约失败,请检查遗漏项"),
    UPDATE_CONTRACT_FAILED_TIME(1105, "修改合约失败,当前日期超过截至日期"),
    UPDATE_CONTRACT_FAILED_OVERLAP_TIME(1111, "修改合约失败,修改的日期与原有合约中的日期有重合"),
    CONTRACT_FAILED_TIME(1107, "开始日期不能晚于截止日期"),
    INSERT_CONTRACT_FAILED_TIME(1109, "添加合约失败,开始时间不能超过今日一年"),
    INSERT_CONTRACT_FAILED_LATEST_TIME(1110, "添加合约失败,开始时间不能早于最晚截止日期"),
    INSERT_CONTRACT_FAILED_OVERLAP_TIME(1112, "添加合约失败,添加的日期与原有合约中的日期有重合"),
    INSERT_NOT_EMPTY(1113,"数据不得为空"),
    INSERT_AGENT_TIME_FAILED(1114,"首次加入时间不允许超过系统当前时间一年之前"),
	UPDATE_CONTRACT_FAILED_ONE(1115, "修改合约失败,开始时间距离今日超出一年"),


    //教师相关错误
    TEACHER_ADD_FAILED(1201, "添加教师失败"),
    TEACHER_MODIFY_FAILED(1202, "修改教师失败"),
    TEACHER_DELETE_FAILED(1203, "删除教师失败"),
    TEACHER_CODE_REPEAT(1204,"教师编号重复"),
    TEACHER_ID_NUMBER_REPEAT(1205,"身份证号重复"),

    //活动相关错误
    ACTIVITY_ADD_FAILED(1301, "添加活动失败"),
    ACTIVITY_MODIFY_FAILED(1302, "修改活动失败"),
    ACTIVITY_DELETE_FAILED(1303, "删除活动失败"),
    ACTIVITY_ADD_FAILED_REPEAT(1304, "添加活动失败: 已有该活动"),
    ACTIVITY_MODIFY_FAILED_REPEAT(1305, "修改活动失败: 已有该活动"),

    //文件相关错误
    FILE_UPLOAD_FAILED(1401, "文件上传失败"),
    FILE_DOWNLOAD_FAILED(1402, "文件下载失败"),
    FILE_DELETE_FAILED(1403, "文件删除失败"),
    FILE_IS_EMPTY(1404, "文件为空"),
    FILE_NOT_EXIST(1405, "文件不存在"),
	
	
	//代理学生交易记录管理相关错误
    DELETE_STUDENT_TRANSACTION_RECORD_FAILED(1501,"删除代理学生交易记录失败"),
    INSERT_STUDENT_TRANSACTION_RECORD_FAILED(1501,"添加代理学生交易记录失败"),
	
	
    //固定课程相关错误
    FIXED_COURSE_ADD_FAILED(1601,"添加固定课程失败"),
    FIXED_COURSE_MODIFY_FAILED(1602,"修改固定课程失败"),
    FIXED_COURSE_DELETE_FAILED(1603,"删除固定课程失败"),
    FIXED_COURSE_ADD_FAILED_REPEAT(1604,"添加固定课程失败:已有该固定课程"),
    FIXED_COURSE_MODIFY_FAILED_REPEAT(1605,"修改固定课程失败:已有该固定课程"),




    //学期记录相关错误
    TERM_RECORD_ADD_FAILED(1701,"添加学期记录失败"),
    TERM_RECORD_MODIFY_FAILED(1702,"修改学期记录失败"),
    TERM_RECORD_ADD_FAILED_REPEAT(1703,"添加学期记录失败:已有该学期记录"),
    TERM_RECORD_MODIFY_FAILED_REPEAT(1703,"修改学期记录失败:已有该学期记录"),
    TERM_RECORD_NOT_EXISTED(1704,"没有该学期记录"),

    //课程计划表相关错误
    SCHEDULE_JURISDICTION_FAILED(1801,"查询课程计划表权限不足"),
    SCHEDULE_ADD_FAILED(1802,"添加课程计划失败"),
    SCHEDULE_MODIFY_FAILED(1803,"修改课程计划失败"),
    SCHEDULE_DELETE_FAILED(1804,"删除课程计划失败"),
    SCHEDULE_DELETE_FAILED_TIME_PAST(1805,"删除课程计划失败:不能删除过去课程计划"),
    SCHEDULE_MODIFY_FAILED_MODIFY_TIME_PAST(1806,"修改课程计划失败:不能修改过去课程计划"),
    SCHEDULE_ADD_FAILED_THE_WEEK_HAD_COURSE(1807,"添加课程计划失败:该周已有课程计划"),
    SCHEDULE_ADD_FAILED_TIME_PAST(1808,"添加课程计划失败:不能添加过去课程计划"),
    SCHEDULE_ADD_FAILED_TYPE_ERROR(1809,"添加课程失败:课程类型错误"),
	
	    //课程相关错误
    COURSE_ADD_FAILED(1901,"添加课程失败"),
    COURSE_MODIFY_FAILED(1902,"修改课程失败"),
    COURSE_DELETE_FAILED(1903,"删除课程失败"),

    //章节相关错误
    CHAPTER_ADD_FAILED(2001,"添加章节失败"),
    CHAPTER_MODIFY_FAILED(2002,"修改章节失败"),
    CHAPTER_DELETE_FAILED(2003,"删除章节失败"),

    //教材相关错误
    TEXTBOOK_ADD_FAILED(2101,"添加教材失败"),
    TEXTBOOK_MODIFY_FAILED(2102,"修改教材失败"),
    TEXTBOOK_DELETE_FAILED(2103,"删除教材失败"),

    //一级分类相关错误
    FIRST_CLASSIFY_ADD_FAILED(2201,"添加一级分类失败"),
    FIRST_CLASSIFY_MODIFY_FAILED(2202,"修改一级分类失败"),
    FIRST_CLASSIFY_DELETE_FAILED(2203,"删除一级分类失败"),

    //二级分类相关错误
    SECOND_CLASSIFY_ADD_FAILED(2301,"添加二级分类失败"),
    SECOND_CLASSIFY_MODIFY_FAILED(2302,"修改二级分类失败"),
    SECOND_CLASSIFY_DELETE_FAILED(2303,"删除二级分类失败"),

    //分类相关错误
    CLASSIFY_CONTAINS_COURSE(2401,"分类中包含课程"),

    // 作业相关错误
    NOT_FUTURE_DATE(2501,"截止日期必须为将来日期"),
	;

    private int errCode;
    private String msg;

    EmBusinessErr(int errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.msg;
    }

    @Override
    public void setErrMsg(String msg) {
        this.msg = msg;
    }
}
