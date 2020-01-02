package com.youjiao.demo.service.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminAgentAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminAgentUpdateVO;
import com.youjiao.demo.controller.viewobject.admin.AdminContractAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminContractListVO;
import com.youjiao.demo.dataobject.AgencyContractDO;
import com.youjiao.demo.dataobject.AgentDO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminAgentService {

    /**
     * @author CainMJ
     * 返回代理商列表,根据截止时间排序
     */
    List<AgentDO> getAgentList();

    /**
     * @author CainMJ
     * 插入代理商数据
     */
    Integer insertAgent(AdminAgentAddVO adminAgentAddVo) throws BusinessException;

    /**
     * @author CainMJ
     * 根据传入数据修改代理商数据
     */
    void updateAgentPrimaryKey(AdminAgentUpdateVO adminAgentUpdateVO) throws BusinessException;

    /**
     * @author WengWenxin
     * 根据传入的代理商ID返回代理商合约记录
     */
    List<AdminContractListVO> getContractList(Integer agentId);

    /**
     * @author WengWenxin
     * 根据传入的合约信息添加代理商合约记录
     */
    void addContract(AdminContractAddVO adminContractAddVO) throws BusinessException;

    /**
     * @author WengWenxin
     * 根据传入的合约信息修改代理商合约记录
     */
    void updateContract(AgencyContractDO agencyContractDO) throws BusinessException;


    /**
     * @author Tzj
     * 通过机构id获取机构名称
     */
    String findSchoolNameBySchoolId(Integer schoolId);
}
