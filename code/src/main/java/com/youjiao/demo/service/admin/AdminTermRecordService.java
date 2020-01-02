package com.youjiao.demo.service.admin;

import com.youjiao.demo.dataobject.TermRecordDO;
import com.youjiao.demo.error.BusinessException;

import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 20:42
 */
public interface AdminTermRecordService {
    /**
     * @author Ck
     * 添加学期记录
     */
    void addTermRecord(TermRecordDO termRecordDO) throws BusinessException;

    /**
     * @author Ck
     * 修改学期记录
     */
    void modifyTermRecord(TermRecordDO termRecordDO) throws BusinessException;

    /**
     * @author Ck
     * 查询所有学期记录
     */
    List<TermRecordDO> getTermRecordAll(Byte role);

    /**
     * @author Ck
     * 根据学期id
     */
    TermRecordDO getTermRecordById(Integer id);
}
