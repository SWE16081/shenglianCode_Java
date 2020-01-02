package com.youjiao.demo.dao;

import com.youjiao.demo.controller.viewobject.admin.AdminContractListVO;
import com.youjiao.demo.dataobject.AgencyContractDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Mapper
@Component
public interface AgencyContractDOMapper {
    int deleteByPrimaryKey(Integer contractId);

    int insert(AgencyContractDO record);

    int insertSelective(AgencyContractDO record);

    AgencyContractDO selectByPrimaryKey(Integer contractId);

    int updateByPrimaryKeySelective(AgencyContractDO record);

    int updateByPrimaryKey(AgencyContractDO record);

    /**
     * @author WengWenxin
     * 根据代理商ID返回合约列表
     */
    @Select("select\n" +
            "*,(\n" +
            "    Case\n" +
            "\t\twhen deadline>NOW() then\n" +
            "\t\t deadline-NOW()\n" +
            "\t\t when deadline<now() then\n" +
            "\t\t NOW()-deadline\n" +
            "\t\t END\n" +
            "\t )timesort,\n" +
            "\t (\n" +
            "\t   CASE\n" +
            "\t\t when deadline>now() then\n" +
            "\t\t 0\t\t\t\t\n" +
            "\t\t when deadline<now() then\n" +
            "\t\t 1\n" +
            "\t\t end\n" +
            "\t\t )timesort1\n" +
            "\t\t from\n" +
            "\t\t  agency_contract\n" +
            "\t\t\twhere agent_id=#{agentId}\n" +
            "\t\t\tORDER BY\n" +
            "\t\t\ttimesort1,\n" +
            "\t\t\ttimesort\n")
    List<AdminContractListVO> selectByAgentId(Integer agentId);


    /**
     * @author WengWenxin
     * 根据开始时间和截止时间判断是否与合约中的时间重合 用于添加时判断
     */
    @Select("select * \n" +
            "from \n" +
            "(\n" +
            "select * from agency_contract where agent_id =#{agentId}\n" +
            ")a\n" +
            "  where (start_time >=#{startTime}AND start_time <=#{deadline}) OR \n" +
            "    (start_time <= #{startTime} AND deadline >=#{deadline})OR\n" +
            "    (deadline >=#{startTime}AND deadline <=#{deadline})\n" +
            "\t\t\t\t")
    List<Integer> illegalTimeContractAdd(Integer agentId,Date startTime, Date deadline);

    /**
     * @author WengWenxin
     * 根据开始时间和截止时间判断是否与合约中的时间重合 用于修改时判断 因为修改时要忽略掉修改的那个时间段，所以需要合约ID
     */
    @Select("select contract_id\n" +
            "            from\n" +
            "            (\n" +
            "            select *\n" +
            "            from agency_contract\n" +
            "            where contract_id!=#{contractId} AND agent_id=#{agentId}\n" +
            "            )a\n" +
            "            where (start_time >=#{startTime}AND start_time <=#{deadline}) OR\n" +
            "            (start_time <=#{startTime} AND deadline >=#{deadline})OR\n" +
            "            (deadline >= #{startTime}AND deadline <=#{deadline})")
    List<Integer> illegalTimeContractUpdate(Integer agentId,Integer contractId, Date startTime, Date deadline);

    /**
     * @author WengWenxin
     * 获取最晚的截止时间
     */
    @Select("SELECT deadline from agency_contract \n" +
            "where agent_id=#{agentId}\n" +
            "ORDER BY deadline\n" +
            "desc\n" +
            "LIMIT 1")
    Date getLatestDeadline(Integer agentId);
}