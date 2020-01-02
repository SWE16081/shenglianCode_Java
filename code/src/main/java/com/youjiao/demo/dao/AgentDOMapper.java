package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminTransactionRecordSchoolListVO;
import com.youjiao.demo.dataobject.AgentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Mapper
@Component
public interface AgentDOMapper {
    int deleteByPrimaryKey(Integer agentId);

    int insert(AgentDO record);

    int insertSelective(AgentDO record);

    AgentDO selectByPrimaryKey(Integer agentId);

    int updateByPrimaryKeySelective(AgentDO record);

    int updateByPrimaryKey(AgentDO record);

    /**
     * @author CainMJ
     * 通过截止时间排序
     */
    @Select("SELECT * FROM agent ORDER BY deadline DESC")
    List<AgentDO> getAgentList();

    /**
     * @author Kny
     * 通过代理商id查询机构名称
     */
    @Select("select agent_name from agent where agent_id = #{agentId}")
    String selectAgentNameByAgentId(Integer agentId);
	
	    /**
     * @author Lyy
     * 筛选出除了本机构以外的其他机构列表（供总园长使用）
     */
    @Select("select agent_id,agent_name from agent where agent_id != #{schoolId} and level = 0")
    List<AdminTransactionRecordSchoolListVO> getSchoolList(Integer schoolId);

    /**
     * @author WengWenxin
     * 修改代理商的最晚截止时间
     */
    @Select("UPDATE agent set deadline=#{date}\n" +
            "where agent_id=#{agentId}")
    void setDeadline(Date date,Integer agentId);

    /**
     * @author Lyy
     * 根据机构id获取机构名称
     */
	@Select("select agent_name from agent where agent_id=#{agentId}")
    String getAgentNameByAgentId(Integer agentId);
}