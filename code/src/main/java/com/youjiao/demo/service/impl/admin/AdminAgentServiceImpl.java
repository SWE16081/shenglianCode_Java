package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminAgentAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminAgentUpdateVO;
import com.youjiao.demo.controller.viewobject.admin.AdminContractAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminContractListVO;
import com.youjiao.demo.dao.AgencyContractDOMapper;
import com.youjiao.demo.dao.AgentDOMapper;
import com.youjiao.demo.dataobject.AgencyContractDO;
import com.youjiao.demo.dataobject.AgentDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminAgentService;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class AdminAgentServiceImpl implements AdminAgentService {

    private final AgentDOMapper agentDOMapper;
    private final AgencyContractDOMapper agencyContractDOMapper;

    @Autowired
    public AdminAgentServiceImpl(AgentDOMapper agentDOMapper, AgencyContractDOMapper agencyContractDOMapper) {
        this.agentDOMapper = agentDOMapper;
        this.agencyContractDOMapper = agencyContractDOMapper;
    }

    /**
     * @author CainMJ
     * 返回代理商列表
     */
    @Override
    public List<AgentDO> getAgentList() {
        return agentDOMapper.getAgentList();
    }

    /**
     * @author CainMJ
     * 根据数据添加代理商
     */
    @Override
    public Integer insertAgent(AdminAgentAddVO adminAgentAddVo) throws BusinessException {
        AgentDO agentDO = new AgentDO();
        BeanUtils.copyProperties(adminAgentAddVo, agentDO);
        //判断首次加入时间
        if(nowDateAndAgentDate(adminAgentAddVo.getFirstTime()) >= 0){
            throw new BusinessException(EmBusinessErr.INSERT_AGENT_TIME_FAILED);
        }
        //插入数据
        try {
            agentDOMapper.insert(agentDO);
            return agentDO.getAgentId();
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.INSERT_AGENT_FAILED);
        }
    }

    /**
     * @author CainMJ
     * 根据传入数据修改代理商数据
     */
    @Override
    public void updateAgentPrimaryKey(AdminAgentUpdateVO adminAgentUpdateVO) throws BusinessException {
        //根据主键获取相对应的供应商数据
        AgentDO agentDO = new AgentDO();
        //设置变量
        BeanUtils.copyProperties(adminAgentUpdateVO, agentDO);
        //判断首次加入时间
        if(nowDateAndAgentDate(agentDO.getFirstTime()) >= 0){
            throw new BusinessException(EmBusinessErr.INSERT_AGENT_TIME_FAILED);
        }
        //修改数据
        try {
            agentDOMapper.updateByPrimaryKey(agentDO);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.UPDATE_AGENT_FAILED);
        }
    }

    /**
     * @author WengWenxin
     * 根据传入的代理商ID返回代理商合约记录
     */
    @Override
    public List<AdminContractListVO> getContractList(Integer agentId) {

        return agencyContractDOMapper.selectByAgentId(agentId);
    }

    /**
     * @author WengWenxin
     * 根据传入的合约信息添加代理商合约记录
     */
    @Override
    public void addContract(AdminContractAddVO adminContractAddVO) throws BusinessException {

        //获取当前时间，开始时间，截止时间和最晚截止时间
        long now = System.currentTimeMillis();
        long startTime = adminContractAddVO.getStartTime().getTime();
        long deadline = adminContractAddVO.getDeadline().getTime();
        //如果开始时间在截止时间之后，则判断日期是不合法的  ****
        if (startTime > deadline) {
            throw new BusinessException(EmBusinessErr.CONTRACT_FAILED_TIME);
        }
        //如果开始时期在当前时期之前则不合法
        if (isTimeOneYear(adminContractAddVO.getStartTime())) {
            throw new BusinessException(EmBusinessErr.INSERT_CONTRACT_FAILED_TIME);
        }
        //如果新的日期有重合
        isTimeLegalAdd(adminContractAddVO.getAgentId(), adminContractAddVO.getStartTime(), adminContractAddVO.getDeadline());
        //创建AgencyContractDO来保存前端传来的数据
        AgencyContractDO agencyContractDO = new AgencyContractDO();
        BeanUtils.copyProperties(adminContractAddVO, agencyContractDO);
        //写入数据
        try {
            //插入合约表
            agencyContractDOMapper.insert(agencyContractDO);
            //获取该代理商最晚截止时间
            Date latestDeadline = agencyContractDOMapper.getLatestDeadline(agencyContractDO.getAgentId());
            //修改代理商表最晚合约截止日期
            agentDOMapper.setDeadline(latestDeadline, agencyContractDO.getAgentId());
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.INSERT_CONTRACT_FAILED);
        }
    }

    /**
     * @author WengWenxin
     * 根据传入的合约信息修改代理商合约记录
     */
    @Override
    public void updateContract(AgencyContractDO agencyContractDO) throws BusinessException {
        //获取当前系统日期
        long now = System.currentTimeMillis();
        //获取合约开始日期
        long startTime = agencyContractDO.getStartTime().getTime();
        //获取合约的截止日期
        long deadline = agencyContractDO.getDeadline().getTime();

        //如果当前时间超过截止时间则不允许修改
        if (now > deadline) {
            throw new BusinessException(EmBusinessErr.UPDATE_CONTRACT_FAILED_TIME);
        }
        //如果开始时间比截止时间晚则时间非法 *****
        if (startTime > deadline) {
            throw new BusinessException(EmBusinessErr.CONTRACT_FAILED_TIME);
        }
        //如果修改的时间距离今日一年不允许修改
        if(isTimeOneYear(agencyContractDO.getStartTime()))
        {
            throw new BusinessException(EmBusinessErr.UPDATE_CONTRACT_FAILED_ONE);
        }
        //如果新的日期有重合
        isTimeLegalUpdate(agencyContractDO.getAgentId(), agencyContractDO.getContractId(), agencyContractDO.getStartTime(), agencyContractDO.getDeadline());
        //修改数据库内容
        try {
            agencyContractDOMapper.updateByPrimaryKeySelective(agencyContractDO);
            //获取该代理商最晚截止时间
            Date latestDeadline = agencyContractDOMapper.getLatestDeadline(agencyContractDO.getAgentId());
            //修改代理商表最晚合约截止日期
            System.out.println(latestDeadline);
            System.out.println(agencyContractDO.getAgentId());
            agentDOMapper.setDeadline(latestDeadline, agencyContractDO.getAgentId());
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.UPDATE_AGENT_FAILED);
        }

    }

    /**
     * @author Tzj
     * 通过代理商id查询代理商名称
     */
    @Override
    public String findSchoolNameBySchoolId(Integer schoolId) {
        return agentDOMapper.getAgentNameByAgentId(schoolId);
    }

    /**
     * @author WengWenxin
     * 在添加合约的过程中，新的日期是否和合约中的日期重合
     */
    private void isTimeLegalAdd(Integer agentId, Date startTime, Date deadline) throws BusinessException {
        //用于保存和新的日期重合的合约ID，主要用来判断日期是否合法
        List<Integer> overlapContractTime = agencyContractDOMapper.illegalTimeContractAdd(agentId, startTime, deadline);
        //如果从数据库中查到和修改时间重合的合约列表不为空，则修改日期不合法
        if (overlapContractTime.size() != 0) {
            throw new BusinessException(EmBusinessErr.INSERT_CONTRACT_FAILED_OVERLAP_TIME);
        }
    }

    /**
     * @author WengWenxin
     * 在修改合约的过程中，新的日期是否和合约中的日期重合
     */
    private void isTimeLegalUpdate(Integer agentId, Integer contractId, Date startTime, Date deadline) throws BusinessException {
        //用于保存和新的日期重合的合约ID，主要用来判断日期是否合法
        List<Integer> overlapContractTime = agencyContractDOMapper.illegalTimeContractUpdate(agentId, contractId, startTime, deadline);
        //如果从数据库中查到和修改时间重合的合约列表不为空，则修改日期不合法
        if (overlapContractTime.size() != 0) {
            throw new BusinessException(EmBusinessErr.UPDATE_CONTRACT_FAILED_OVERLAP_TIME);
        }
    }

    /**
    * @author CaiMJ
    * 判断代理商传入的日期与当前日期是否相差一年
    */
    private int nowDateAndAgentDate(Date agentDate){
        //获取系统当前时间
        Date nowDate = new Date(new java.util.Date().getTime());
        Calendar nowDateCalendar = new GregorianCalendar();
        nowDateCalendar.setTime(nowDate);
        //将传来的时间增加一年
        Calendar agentDateCalendar = new GregorianCalendar();
        agentDateCalendar.setTime(agentDate);
        agentDateCalendar.add(Calendar.YEAR,1);
        //在比较前，将Calendar的MILLISECOND单位都设置为0，减少精度
        agentDateCalendar.set(Calendar.MILLISECOND,0);
        nowDateCalendar.set(Calendar.MILLISECOND,0);
        //将传来的时间和现在的时间相比较，如果传来的时间加一年小于现在时间，表示传来的时间是超过一年的时间
        return nowDateCalendar.compareTo(agentDateCalendar);
    }
	
	/**
 * @author WengWenxin
 * 在添加合约的过程中，开始日期是否距离今天超过365天
 */
private boolean isTimeOneYear(Date startTime) {
    Date today=new Date(System.currentTimeMillis());
    Calendar startDay = Calendar.getInstance();
    Calendar nowDay = Calendar.getInstance();
    startDay.setTime(startTime);
    nowDay.setTime(today);
    nowDay.add(Calendar.YEAR,-1);
    if(startDay.before(nowDay))
        return true;
    else
        return false;
}
}
