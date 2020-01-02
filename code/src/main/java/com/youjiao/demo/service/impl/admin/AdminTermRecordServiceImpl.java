package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.dao.TermRecordDOMapper;
import com.youjiao.demo.dataobject.TermRecordDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminTermRecordService;
import com.youjiao.demo.util.CompareUtil;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Ck
 * #date 2019/04/14 20:46
 */
@Service
public class AdminTermRecordServiceImpl implements AdminTermRecordService {
    private final TermRecordDOMapper termRecordDOMapper;

    public AdminTermRecordServiceImpl(TermRecordDOMapper termRecordDOMapper) {
        this.termRecordDOMapper = termRecordDOMapper;
    }

    /**
     * @author Ck
     * 添加学期记录
     */
    @Override
    public void addTermRecord(TermRecordDO termRecordDO) throws BusinessException {
        List<TermRecordDO> sameList = termRecordDOMapper.selectBySame(termRecordDO.getYear(), termRecordDO.getTermNum(), termRecordDO.getGrade());
        if (!sameList.isEmpty()) {
            throw new BusinessException(EmBusinessErr.TERM_RECORD_ADD_FAILED_REPEAT);
        }

        termRecordDO.setTermId(null);
        try {
            termRecordDOMapper.insert(termRecordDO);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.TERM_RECORD_ADD_FAILED);
        }
    }

    /**
     * @author Ck
     * 修改学期记录
     */
    @Override
    public void modifyTermRecord(TermRecordDO termRecordDO) throws BusinessException {
        List<TermRecordDO> sameList = termRecordDOMapper.selectBySame(termRecordDO.getYear(), termRecordDO.getTermNum(), termRecordDO.getGrade());
        if (!sameList.isEmpty()) {
            throw new BusinessException(EmBusinessErr.TERM_RECORD_MODIFY_FAILED_REPEAT);
        }

        TermRecordDO dbTermRecord = termRecordDOMapper.selectByPrimaryKey(termRecordDO.getTermId());
        dbTermRecord = (TermRecordDO) CompareUtil.CompareDOVO(dbTermRecord, termRecordDO);

        if (dbTermRecord != null) {
            dbTermRecord.setTermId(termRecordDO.getTermId());
            try {
                termRecordDOMapper.updateByPrimaryKeySelective(dbTermRecord);
            } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.TERM_RECORD_MODIFY_FAILED);
            }
        }
    }

    /**
     * @author Ck
     * 查询所有学期记录
     */
    @Override
    public List<TermRecordDO> getTermRecordAll(Byte role) {
        List<TermRecordDO> list;
        List<TermRecordDO> ans = new ArrayList<>();
        list = termRecordDOMapper.selectTermRecordAll();
        for (TermRecordDO dos : list) {
            boolean bool = !checkTermBefore(dos);
            if (bool || role == Constants.ADMIN_JURISDICTION_TOP) {
                ans.add(dos);
            }
        }
        return ans;
    }

    @Override
    public TermRecordDO getTermRecordById(Integer id) {
        TermRecordDO termRecordDO;
        termRecordDO = termRecordDOMapper.selectByPrimaryKey(id);
        return termRecordDO;
    }

    //检查学期是否是未来的学期不包括当前学期
    private boolean checkTermBefore(TermRecordDO dos) {
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());
        int nowYear = today.get(Calendar.YEAR);
        return dos.getYear() >= nowYear;
    }
}
