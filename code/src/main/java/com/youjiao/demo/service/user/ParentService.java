package com.youjiao.demo.service.user;

import com.youjiao.demo.controller.viewobject.user.ParentListVO;
import com.youjiao.demo.controller.viewobject.user.ParentVO;
import com.youjiao.demo.dataobject.ParentDO;
import com.youjiao.demo.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParentService {
    /**
     * @author Kny
     * 通过classId获取父母列表信息
     */
    List<ParentListVO> getParentList(Integer classId);

    /**
     * @author Tzj
     * 通过父母id获得父母实体
     */
    ParentDO getParentByParentId(Integer parentId);

    /**
     * @author Lyy
     * 根据家长id获取对应的个人信息页
     */
    ParentVO showParentInfo() throws BusinessException;

    /**
     * @author Lyy
     * 检查编辑后的信息是否合理，是则修改
     */
    void checkEditInfo(String telephone, String avatar) throws BusinessException;

}
