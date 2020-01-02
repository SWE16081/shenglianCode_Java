package com.youjiao.demo.dao;

import com.youjiao.demo.dataobject.AdminDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AdminDOMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminDO record);

    int insertSelective(AdminDO record);

    AdminDO selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminDO record);

    int updateByPrimaryKey(AdminDO record);

    /**
     * @author Tzj
     * 根据用户名和密码查询管理员实体
     */
    @Select("select * from admin where name = #{name} and password = #{password}")
    AdminDO findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /**
     * @author Tzj
     * 根据用户名查询管理员实体
     */
    @Select("select * from admin where name = #{name}")
    AdminDO findByName(@Param("name") String name);

    /**
     * @author Tzj
     * 重置密码
     */
    @Update("update admin set password=#{newPassword} where admin_id=#{adminId}")
    void resetPassword(@Param("newPassword") String newPassword, @Param("adminId") Integer adminId);


    /**
     * @author Tzj
     * 修改个人信息
     */
    @Update("update admin set password=#{password} where admin_id=#{adminId}")
    void modifyInfo(@Param("adminId") Integer adminId,
                    @Param("password") String password);

    /**
     * @author Tzj
     * 添加管理员
     */
    @Update("insert into admin(school_id,name,password,jurisdiction)values(#{schoolId},#{userName},#{password},#{jurisdiction})")
    void myInsert(@Param("userName") String userName,
                  @Param("password") String password,
                  @Param("schoolId") Integer schoolId,
                  @Param("jurisdiction") Integer jurisdiction);

    /**
     * @author Tzj
     * 获取所有非超级管理员信息
     */
    @Select("select * from admin where jurisdiction!=0 order by admin_id")
    List<AdminDO> getAllAdmin();

    /**
     * @author Tzj
     * 根据机构id获取该机构所有管理员
     */
    @Select("select * from admin where school_id=#{schoolId} and name!=#{userName} and jurisdiction!=0 order by admin_id")
    List<AdminDO> getAdminBySchoolId(@Param("schoolId") Integer schoolId, @Param("userName") String userName);

    /**
     * @author Tzj
     * 通过id获取管理员实体
     */
    @Select("select * from admin where admin_id=#{adminId}")
    AdminDO selectById(@Param("adminId")Integer adminId);

    /**
     * @author Tzj
     * 根据id删除管理员
     */
    @Delete("delete from admin where admin_id=#{adminId}")
    void deleteByAdminId(@Param("adminId") Integer adminId);
}