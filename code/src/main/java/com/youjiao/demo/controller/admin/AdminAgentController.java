package com.youjiao.demo.controller.admin;

import com.youjiao.demo.controller.BaseController;
import com.youjiao.demo.controller.viewobject.admin.AdminAgentAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminAgentUpdateVO;
import com.youjiao.demo.controller.viewobject.admin.AdminContractAddVO;
import com.youjiao.demo.dataobject.AgencyContractDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.response.CommonReturnType;
import com.youjiao.demo.service.admin.AdminAgentService;
import com.youjiao.demo.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.youjiao.demo.validator.MyValidation.*;

@Controller("adminAgent")
@RequestMapping("/admin/agent")
public class AdminAgentController extends BaseController {

    private final AdminAgentService adminAgentService;

    @Autowired
    public AdminAgentController(AdminAgentService adminAgentService) {
        this.adminAgentService = adminAgentService;
    }

    /**
     * @author CainMJ
     * 根据时间显示代理商
     */
    @GetMapping(value = "getAgentList")
    @ResponseBody
    public CommonReturnType getAgentList() {
        //根据截止时间返回代理商列表
        return CommonReturnType.create(adminAgentService.getAgentList());
    }

    /**
     * @author CainMJ
     * 根据数据添加代理商
     */
    @PostMapping(value = "addAgent")
    @ResponseBody
    public CommonReturnType addAgent(@Valid @RequestBody AdminAgentAddVO adminAgentAddVO, BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.INSERT_NOT_EMPTY, result);
        //检测传入数据是否有误
        checkObjectNull(adminAgentAddVO.getLevel());
        checkTelephone(adminAgentAddVO.getTelephone());
        //传递数据,返回结果
        return CommonReturnType.create(adminAgentService.insertAgent(adminAgentAddVO));
    }

    /**
     * @author CainMJ
     * 根据数据修改代理商数据
     */
    @PostMapping(value = "updateAgent")
    @ResponseBody
    public CommonReturnType updateAgent(@Valid @RequestBody AdminAgentUpdateVO adminAgentUpdateVO, BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.INSERT_NOT_EMPTY, result);
        //检测传入数据是否有误
        checkObjectNull(adminAgentUpdateVO.getLevel());
        checkTelephone(adminAgentUpdateVO.getTelephone());

        //传递数据，修改数据库
        adminAgentService.updateAgentPrimaryKey(adminAgentUpdateVO);
        //返回结果
        return CommonReturnType.create(null);
    }

    /**
     * @author WengWenxin
     * 显示合约列表接口
     */
    @GetMapping(value = "/getContractList")
    @ResponseBody
    public CommonReturnType getContractList(@RequestParam(name = "agentId") Integer agentId) throws BusinessException {
        checkIntNull(agentId);
        return CommonReturnType.create(adminAgentService.getContractList(agentId));
    }

    /**
     * @author WengWenxin
     * 添加合约
     */
    @PostMapping(value = "/addContract")
    @ResponseBody
    public CommonReturnType addContract(@Valid @RequestBody AdminContractAddVO adminContractAddVO, BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.INSERT_CONTRACT_FAILED, result);

        //将添加信息传给Service处理
        adminAgentService.addContract(adminContractAddVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author WengWenxin
     * 修改合约
     */
    @PostMapping(value = "/updateContract")
    @ResponseBody
    public CommonReturnType updateContract(@Valid @RequestBody AgencyContractDO agencyContractDO, BindingResult result) throws BusinessException {

        MyValidation.validateObject(EmBusinessErr.INSERT_CONTRACT_FAILED, result);
        //将更新信息传给Service处理
        adminAgentService.updateContract(agencyContractDO);
        return CommonReturnType.create(null);
    }
}
