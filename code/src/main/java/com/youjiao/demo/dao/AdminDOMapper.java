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
     * �����û����������ѯ����Աʵ��
     */
    @Select("select * from admin where name = #{name} and password = #{password}")
    AdminDO findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /**
     * @author Tzj
     * �����û�����ѯ����Աʵ��
     */
    @Select("select * from admin where name = #{name}")
    AdminDO findByName(@Param("name") String name);

    /**
     * @author Tzj
     * ��������
     */
    @Update("update admin set password=#{newPassword} where admin_id=#{adminId}")
    void resetPassword(@Param("newPassword") String newPassword, @Param("adminId") Integer adminId);


    /**
     * @author Tzj
     * �޸ĸ�����Ϣ
     */
    @Update("update admin set password=#{password} where admin_id=#{adminId}")
    void modifyInfo(@Param("adminId") Integer adminId,
                    @Param("password") String password);

    /**
     * @author Tzj
     * ��ӹ���Ա
     */
    @Update("insert into admin(school_id,name,password,jurisdiction)values(#{schoolId},#{userName},#{password},#{jurisdiction})")
    void myInsert(@Param("userName") String userName,
                  @Param("password") String password,
                  @Param("schoolId") Integer schoolId,
                  @Param("jurisdiction") Integer jurisdiction);

    /**
     * @author Tzj
     * ��ȡ���зǳ�������Ա��Ϣ
     */
    @Select("select * from admin where jurisdiction!=0 order by admin_id")
    List<AdminDO> getAllAdmin();

    /**
     * @author Tzj
     * ���ݻ���id��ȡ�û������й���Ա
     */
    @Select("select * from admin where school_id=#{schoolId} and name!=#{userName} and jurisdiction!=0 order by admin_id")
    List<AdminDO> getAdminBySchoolId(@Param("schoolId") Integer schoolId, @Param("userName") String userName);

    /**
     * @author Tzj
     * ͨ��id��ȡ����Աʵ��
     */
    @Select("select * from admin where admin_id=#{adminId}")
    AdminDO selectById(@Param("adminId")Integer adminId);

    /**
     * @author Tzj
     * ����idɾ������Ա
     */
    @Delete("delete from admin where admin_id=#{adminId}")
    void deleteByAdminId(@Param("adminId") Integer adminId);
}