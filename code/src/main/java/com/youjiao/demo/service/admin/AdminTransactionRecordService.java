package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.*;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lyy
 * #date 2019/04/14 19:58
 */
@Service
public interface AdminTransactionRecordService {
    /**
     * @author Lyy
     * 根据前端的机构id获取除该机构id对应的机构以外的所有机构列表
     * 仅包括机构id和机构名称
     */
    List<AdminTransactionRecordSchoolListVO> getSchoolList() throws BusinessException;

    /**
     * @author Lyy
     * 根据前端的机构id获取该机构名下的所有班级列表
     * 仅包括班级名称和id
     */
    List<AdminStudentClassListVO> getClassList(Integer schoolId) throws BusinessException;

    /**
     * @author Lyy
     * 根据前端的班级id获取该机构名下的所有学生列表
     * 仅包括学生名字和id
     */
    List<AdminTransactionRecordStudentListVO> getStudentList(Integer classId) throws BusinessException;

    /**
     * @author Lyy
     * 根据学生id获取该学生的所有交易记录列表
     */
    List<AdminTransactionRecordListVO> getTransactionRecordList(Integer studentId) throws BusinessException;

    /**
     * @author Lyy
     * 根据前端提交的开始时间和截至时间和当前的班级id获取在时间范围内的所有该班级学生的交易记录列表
     */
    List<AdminTransactionRecordAllVO> selectTransactionRecordList(AdminTransactionRecordSelectVO adminTransactionRecordSelectVO) throws BusinessException;

    /**
     * @author Lyy
     * 根据前端提交的当前的班级id获取所有该班级学生的交易记录列表
     */
    List<AdminTransactionRecordAllVO> selectAllTransactionRecordList(Integer classId) throws BusinessException;

    /**
     * @author Lyy
     * 根据前端提交的托管学生交易记录数据添加一条记录到对应的机构id中
     */
    void addTransactionRecord(AdminTransactionRecordAddVO adminTransactionRecordListVO) throws BusinessException;

    /**
     * @author Lyy
     * 根据前端提交的托管学生交易记录数据及序号修改对应的托管学生交易记录
     */
    void updateTransactionRecord(AdminTransactionRecordListVO adminTransactionRecordListVO) throws BusinessException;

    /**
     * @author Lyy
     * 根据前端提交的序号List删除对应的托管学生交易记录
     */
    void deleteTransactionRecord(List<Integer> recordIdList) throws BusinessException;

    /**
     * @author Lyy
     * 根据总园长的机构id查询总的交易记录
     */
    List<AdminAllTransactionRecordVO> getAllTransactionRecord()throws BusinessException;
}
