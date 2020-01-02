package com.youjiao.demo.service.impl.user;

import com.youjiao.demo.controller.viewobject.user.ParentListVO;
import com.youjiao.demo.controller.viewobject.user.ParentVO;
import com.youjiao.demo.dao.ParentDOMapper;
import com.youjiao.demo.dataobject.ParentDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.model.ParentListModel;
import com.youjiao.demo.service.user.ParentService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyImageUtil;
import com.youjiao.demo.util.MyLog;
import com.youjiao.demo.util.MySessionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {

    private final ParentDOMapper parentDOMapper;

    @Autowired
    public ParentServiceImpl(ParentDOMapper parentDOMapper) {
        this.parentDOMapper = parentDOMapper;
    }

    /**
     * @author Kny
     * 通过classId获取父母信息列表并返回
     */
    @Override
    public List<ParentListVO> getParentList(Integer classId) {
        List<ParentListModel> list = parentDOMapper.selectParentListByClassId(classId);
        List<ParentListVO> ansList = new ArrayList<>();
        //MO转VO
        for (ParentListModel ele : list) {
            ansList.add(new ParentListVO(ele.getStudentName(), ele.getSex(), ele.getTelephone()));
        }
        return ansList;
    }

    /**
     * @author Tzj
     * 通过父母id获得父母实体
     */
    @Override
    public ParentDO getParentByParentId(Integer parentId) {
        return parentDOMapper.getParentByParentId(parentId);
    }

    /**
     * @author Lyy
     * 根据家长id获取对应的个人信息页
     */
    @Override
    public ParentVO showParentInfo() throws BusinessException {
        // 获取session中的家长id
        Integer parentId = (Integer) (MySessionUtil.getSession().getAttribute(Constants.PARENT_ID_SESSION));

        ParentDO parentDO = parentDOMapper.selectByPrimaryKey(parentId);
        if (parentDO == null) {
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
        }
        //根据图片的url获取服务器端的图片
        String avatar = MyImageUtil.downloadImage(parentDO.getAvatar(), Constants.PARENT_AVATAR_PATH);
        parentDO.setAvatar(avatar);
        return convertFromParentDO(parentDO);
    }

    /**
     * @author Lyy
     * 家长个人信息页展示VO
     */
    private ParentVO convertFromParentDO(ParentDO parentDO) {
        if (parentDO == null) {
            return null;
        }
        ParentVO parentVO = new ParentVO();
        BeanUtils.copyProperties(parentDO, parentVO);
        return parentVO;
    }

    /**
     * @author Lyy
     * 检查编辑后的信息是否合理，是则修改
     */
    @Override
    public void checkEditInfo(String telephone, String avatar) throws BusinessException {
        // 获取session中的教师id
        Integer parentId = (Integer) (MySessionUtil.getSession().getAttribute(Constants.PARENT_ID_SESSION));

        ParentDO parentDO = parentDOMapper.selectByPrimaryKey(parentId);

        //图片格式大小判断待处理

        //如果新图片和原图片相同，则设置为null，否则设置为新图片的路径
        parentDO.setAvatar(MyImageUtil.replaceImage(avatar, parentDO.getAvatar(), Constants.PARENT_AVATAR_PATH));

        //如果新手机号和原手机号相同，则设置为null
        if ((parentDO.getTelephone()).equals(telephone)) {
            parentDO.setTelephone(null);
        } else {//若有改动电话号码则需要判断是否已被注册
            if (parentDOMapper.selectByTelephone(telephone) != null) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "手机号已被注册");
            } else {
                parentDO.setTelephone(telephone);
            }
        }
        parentDOMapper.updateByPrimaryKeySelective(parentDO);
    }

}
