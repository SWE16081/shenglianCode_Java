package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminTransactionRecordAllVO;
import com.youjiao.demo.controller.viewobject.admin.AdminTransactionRecordListVO;
import com.youjiao.demo.dataobject.StudentTransactionRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Mapper
@Component
public interface StudentTransactionRecordDOMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(StudentTransactionRecordDO record);

    int insertSelective(StudentTransactionRecordDO record);

    StudentTransactionRecordDO selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(StudentTransactionRecordDO record);

    int updateByPrimaryKey(StudentTransactionRecordDO record);

    /**
     * @author Lyy
     * 根据学生id获取该学生的所有交易记录列表
     */
    @Select("select * from student_transaction_record where student_id = #{studentId} order by pay_time")
    List<AdminTransactionRecordListVO> getTransactionRecordList(Integer studentId);

    /**
     * @author Lyy
     * 根据前端提交的序号List删除对应的托管学生交易记录
     */
    void deleteStudentTransactionRecordList(List<Integer> recordIdList);

    /**
     * @author Lyy
     * 根据前端提交的开始时间和截至时间和当前的班级id获取在时间范围内的所有该班级学生的交易记录列表
     */
    List<StudentTransactionRecordDO> selectTransactionRecordList(List<Integer> studentIdList, Date startTime, Date deadline);

    /**
     * @author Lyy
     * 根据前端选择的班级返回所有是该班学生的交易记录列表
     */
    List<StudentTransactionRecordDO> selectAllTransactionRecordList(@Param("studentIdList") List<Integer> studentIdList);

    /**
     * @author Lyy
     * 根据总园长的机构id查询总的交易记录
     */
    @Select("Select * from student_transaction_record")
    List<StudentTransactionRecordDO> getAllTransactionRecord();
}