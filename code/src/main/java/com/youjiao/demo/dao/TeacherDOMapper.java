package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminClassTeacherListVO;
import com.youjiao.demo.controller.viewobject.user.TeacherListVO;
import com.youjiao.demo.dataobject.TeacherDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TeacherDOMapper {
    /**
     * @author Kny
     * 查询手机号是否重复
     */
    @Select("select * from teacher where telephone = #{telephone}")
    TeacherDO selectTelephoneRepeat(String teacherCode);
    /**
     * @author Kny
     * 查询身份证号是否重复
     */
    @Select("select * from teacher where id_number = #{idNumber}")
    TeacherDO selectIdNumberRepeat(String teacherCode);
    /**
     * @author Kny
     * 查询教师编号是否重复
     */
    @Select("select * from teacher where teacher_code = #{teacherCode}")
    TeacherDO selectTeacherCodeRepeat(String teacherCode);
    /**
     * @author Kny
     * 通过教师id逻辑删除教师
     */
    void updateTeacherAliveByTeacherId(List<Integer> list);

    /**
     * @author Kny
     * 根据机构id获取教师信息列表
     */
    @Select("select * from teacher where school_id = #{schoolId} and alive=true ")
    List<TeacherDO> selectTeacherListBySchoolId(Integer schoolId);

    /**
     * @author WengWenxin
     * 通过手机号和密码获得教师对象，用于验证登陆
     */
    @Select("select * from teacher where telephone = #{telephone} and password = #{password} and alive=true ")
    TeacherDO selectByTelephoneAndPassword(String telephone, String password);

    /**
     * @author Zjp
     * 通过教师编号获得教师对象
     */
    @Select("select * from teacher where teacher_code = #{teacherCode} and alive=true ")
    TeacherDO selectByTeacherCode(String teacherCode);

    /**
     * @author Zjp
     * 通过手机号获得教师对象
     */
    @Select("select * from teacher where telephone = #{telephone} and alive=true ")
    TeacherDO selectByTelephone(String telephone);

    /**
     * @author Zjp
     * 通过教师id更新手机号和密码
     */
    @Update("update teacher set telephone = #{telephone},password = #{password} where teacher_id = #{teacherId}")
    void updateByTeacherId(String telephone, String password, Integer teacherId);

    /**
     * @author Kny
     * 通过班级id获取教师列表信息，包括教师id，教师名字
     */
    @Select(" select teacher_id,name,telephone from teacher where teacher_id in ( select teacher_id from teacher_class where class_id = #{classId} ) and alive=true ")
    List<TeacherListVO> selectTeacherListByClassId(Integer classId);

    /**
     * @author Ck
     * 通过班级id获取教师信息
     */
    @Select(" select teacher_id,teacher_code,name,sex  from teacher where teacher_id in ( select teacher_id from teacher_class where class_id = #{classId} ) and alive=true ")
    List<AdminClassTeacherListVO> selectAdminTeacherListByClassId(Integer classId);


    /**
     * @author Kny
     * 通过班级id获取班主任id
     */
    @Select("select teacher_id from class where class_id = #{classId}")
    Integer selectHeadTeacherByClassId(Integer classId);

    @Select("select * from teacher where alive=true ")
    List<TeacherDO> getTeacherList();

    int deleteByPrimaryKey(Integer teacherId);

    int insert(TeacherDO record);

    int insertSelective(TeacherDO record);

    TeacherDO selectByPrimaryKey(Integer teacherId);

    int updateByPrimaryKeySelective(TeacherDO record);

    int updateByPrimaryKey(TeacherDO record);
}