package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.ParentDO;
import com.youjiao.demo.service.model.ParentListModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface ParentDOMapper {
    /**
     * @author Zjp
     * 通过手机号获得父母对象
     */
    @Select("select * from parent where telephone = #{telephone}")
    ParentDO selectByTelephone(String telephone);

    /**
     * @author WengWenxin
     * 通过手机号和密码获得家长对象，用于验证登陆
     */
    @Select("select * from parent where telephone = #{telephone} and password = #{password}")
    ParentDO selectByTelephoneAndPassword(String telephone, String password);

    /**
     * @author Kny
     * 根据班级id查询父母列表信息包括学生名字，父母性别，父母电话
     */
    @Select("select student.name,parent.sex,parent.telephone from student,parent where student.parent_id=parent.parent_id and class_id = #{classId}")
    List<ParentListModel> selectParentListByClassId(Integer classId);

    int deleteByPrimaryKey(Integer parentId);

    int insert(ParentDO record);

    int insertSelective(ParentDO record);

    ParentDO selectByPrimaryKey(Integer parentId);

    int updateByPrimaryKeySelective(ParentDO record);

    int updateByPrimaryKey(ParentDO record);

    /**
     * @author Tzj
     * 通过父母id获得父母实体
     */
    @Select("select * from parent where parent_id = #{parentId}")
    ParentDO getParentByParentId(@Param("parentId") Integer parentId);

    /**
     * @author Lyy
     * 将信息填入家长表中
     * 默认密码为电话号码
     */
    @Insert("insert into parent(telephone,job,address,sex,password,name) values(#{telephone},#{job},#{address},#{pSex},#{password},#{parentName})")
    void addParentInfo(String telephone, String job, String address, Boolean pSex,String parentName,String password);

    /**
     * @author Lyy
     * 重置密码为手机号码
     */
    @Update("update parent set password = #{a} where telephone = #{telephone}")
    void resetPassword(String telephone,String a);

    /**
     * @author Lyy
     * 根据电话号码查询对应家长id
     */
    @Select("select parent_id from parent where telephone=#{telephone}")
    Integer getParentIdByTelephone(String telephone);
}