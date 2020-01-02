package com.youjiao.demo.service.impl.admin;

import com.youjiao.demo.controller.viewobject.admin.AdminDishesAddVO;
import com.youjiao.demo.controller.viewobject.admin.AdminDishesNameVO;
import com.youjiao.demo.controller.viewobject.admin.RecordDishesListGetVO;
import com.youjiao.demo.controller.viewobject.admin.VarietyOfDishesVO;
import com.youjiao.demo.controller.viewobject.user.RecordDishesListVO;
import com.youjiao.demo.dao.DishesDOMapper;
import com.youjiao.demo.dao.DishesRecordDOMapper;
import com.youjiao.demo.dao.DishesRecordInformationDOMapper;
import com.youjiao.demo.dataobject.DishesDO;
import com.youjiao.demo.dataobject.DishesRecordInformationDO;
import com.youjiao.demo.error.BusinessException;
import com.youjiao.demo.error.EmBusinessErr;
import com.youjiao.demo.service.admin.AdminDishesService;
import com.youjiao.demo.util.Constants;
import com.youjiao.demo.util.MyExceptionUtil;
import com.youjiao.demo.util.MyImageUtil;
import com.youjiao.demo.util.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.youjiao.demo.util.Constants.*;

/**
 * @author WengWenxin
 * #date 2019/03/23 13:19
 */
@Service
public class AdminDishesServiceImpl implements AdminDishesService {

    private final DishesRecordDOMapper dishesRecordDOMapper;
    private final DishesDOMapper dishesDOMapper;
    private final DishesRecordInformationDOMapper dishesRecordInformationDOMapper;
    //周的数值(0为周日，6为周日)
    private int intWeek;
    private Calendar calendar = Calendar.getInstance();

    @Autowired
    public AdminDishesServiceImpl(DishesRecordInformationDOMapper dishesRecordInformationDOMapper,
                                  DishesDOMapper dishesDOMapper,
                                  DishesRecordDOMapper dishesRecordDOMapper) {
        this.dishesRecordInformationDOMapper = dishesRecordInformationDOMapper;
        this.dishesDOMapper = dishesDOMapper;
        this.dishesRecordDOMapper = dishesRecordDOMapper;
    }

    /**
     * @author WengWenxin
     * 返回菜品列表
     */
    @Override
    public List<VarietyOfDishesVO> getVarietyOfDishesList() throws BusinessException {

        List<VarietyOfDishesVO> volist = dishesDOMapper.selectAll();

        for (VarietyOfDishesVO vo : volist) {
            //根据url提取图片
            String newImageUrl = MyImageUtil.downloadImage(vo.getImg(), Constants.DISHES_PIC_PATH);
            //将原来的路径替换成base64编码
            vo.setImg(newImageUrl);
        }
        return volist;
    }

    /**
     * @author WengWenxin
     * 添加菜品
     */
    @Override
    public void addVarietyOfDishes(DishesDO dishesDO) throws BusinessException {
        //判断菜名是否重复
        Integer name = dishesDOMapper.isNameRepeat(dishesDO.getName());
        if (name != null)
            throw new BusinessException(EmBusinessErr.DISHES_COMMIT_FAILED_Name);
        //上传图片至服务器
        String url = MyImageUtil.uploadImage(dishesDO.getImg(), Constants.DISHES_PIC_PATH);// 上传后的文件的url
        // 存储文件url
        dishesDO.setImg(url);
        // 将数据存入数据库
        try {
            dishesDOMapper.insert(dishesDO);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.DISHES_COMMIT_FAILED);
        }
    }

    /**
     * @author WengWenxin
     * 修改菜品
     */
    @Override
    public void updateVarietyOfDishes(DishesDO dishesDO) throws BusinessException {

        //判断菜名是否重复
        Integer name = dishesDOMapper.isNameRepeatForUpdate(dishesDO.getName(),dishesDO.getDishId());
        if (name != null)
            throw new BusinessException(EmBusinessErr.DISHES_COMMIT_FAILED_Name_UPDATE);
        //上传图片至服务器
        MyImageUtil.deleteImage(dishesDOMapper.getImg(dishesDO.getDishId()), Constants.DISHES_PIC_PATH);
        String url = MyImageUtil.uploadImage(dishesDO.getImg(), Constants.DISHES_PIC_PATH);// 上传后的文件的url
        //在数据库中修改
        dishesDO.setImg(url);
        dishesDO.setAlive(true);
        dishesDOMapper.updateByPrimaryKey(dishesDO);
    }

    /**
     * @author WengWenxin
     * 删除菜品
     */
    @Override
    public void deleteVarietyOfDishes(List<Integer> dishesIdList) throws BusinessException {

        try {
            //获取被使用的dishId列表
            List<Integer> usedDish = dishesRecordDOMapper.getDishIdList();
            //菜品表中有但是没用过的dishId 列表 用于物理删除，图片跟着一起删
            List<Integer> physicsDelete = new ArrayList<>();
            //菜品里有并且菜谱中用到过的dishId 列表 逻辑删除，图片不删
            List<Integer> logicDelete = new ArrayList<>();
            for (Integer i : dishesIdList) {
                logicDelete.add(i);
                physicsDelete.add(i);
            }
            logicDelete.retainAll(usedDish);
            physicsDelete.removeAll(logicDelete);
            //如果存在需要被物理删除的菜品，连图带数据全删了
            if (physicsDelete.size() != 0) {
                for (Integer dishId : physicsDelete)
                    MyImageUtil.deleteImage(dishesDOMapper.getImg(dishId), Constants.DISHES_PIC_PATH);
                dishesDOMapper.deleteDishesListPhysics(physicsDelete);
            }
            //菜谱里用过的就逻辑删除
            if (logicDelete.size() != 0)
                dishesDOMapper.deleteDishesListLogic(logicDelete);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.DELETE_DISHES_COMMIT_FAILED);
        }
    }

    /**
     * @author CainMJ
     * 返回菜谱，根据时间排序(从新到旧)
     */
    @Override
    public List<RecordDishesListGetVO> getRecordDishesListVO() {
        //获取对应的所有菜谱数据
        return RechangeRecordDishesListGetVO(dishesRecordInformationDOMapper.selectForOrderTime());
    }

    /**
     * @author CainMJ
     * 根据时间返回当天的所有菜谱
     */
    @Override
    public List<RecordDishesListGetVO> getRecordDishesListForDay(Date date) {
        return RechangeRecordDishesListGetVO(dishesRecordInformationDOMapper.selectRecordDishByOrderTime(date));
    }

    /**
     * @author CainMJ
     * 根据时间返回当前周的所有菜谱
     */
    @Override
    public List<RecordDishesListGetVO> getRecordDishesListForWeek(Date date) {

        //通过时间获取周一周五时间
        Date MonDay = new Date(GetMonday(date).getTime());
        Date FriDay = new Date(GetFriday(date).getTime());

        return RechangeRecordDishesListGetVO(dishesRecordInformationDOMapper.selectRecordDishListForWeek(MonDay, FriDay));
    }

    /**
     * @author CainMJ
     * 返回菜品：dishId与name
     */
    @Override
    public List<AdminDishesNameVO> getDishesNameList() {
        return dishesDOMapper.selectAllName();
    }


    /**
     * @author CainMJ
     * 根据时间与菜品id列表,遍历列表,在数据库中依次添加数据
     */
    @Override
    public void addRecordDishes(AdminDishesAddVO adminDishesAddVO) throws BusinessException {
        //定义DishesRecordInformationDO存储插入数据
        DishesRecordInformationDO dishesRecordInformationDO = new DishesRecordInformationDO();
        //设置用餐日期
        dishesRecordInformationDO.setOrderTime(adminDishesAddVO.getDate());
        //根据日期，查找数据库
        //数据库中是否有相同时间的数据
        if (!dishesRecordInformationDOMapper.selectRecordDishByOrderTime(dishesRecordInformationDO.getOrderTime()).isEmpty()) {
            throw new BusinessException(EmBusinessErr.INSERT_DISHES_FAILED_REPEAT);
        }
        //插入早餐数据
        InsertToDishesRecordData(BREAK_DISH, dishesRecordInformationDO, adminDishesAddVO.getBreakDishId());
        //插入午餐数据
        InsertToDishesRecordData(LUNCH_DISH, dishesRecordInformationDO, adminDishesAddVO.getLunchDishId());
        //插入午点数据
        InsertToDishesRecordData(NOON_DISH, dishesRecordInformationDO, adminDishesAddVO.getNoonDishId());
    }

    /**
     * @author CainMJ
     * 根据时间与DishId对数据库进行删除，插入操作
     */
    @Override
    @Transactional
    public void updateRecordDishes(AdminDishesAddVO adminDishesAddVO) throws BusinessException {
        //将删除的菜品从列表中移除
        for(int i=0;i<adminDishesAddVO.getBreakDishId().size();i++){
            if(dishesDOMapper.getAliveForDishId(adminDishesAddVO.getBreakDishId().get(i))==0){
                adminDishesAddVO.getBreakDishId().remove(i);
                i--;
            }
        }
        for(int i=0;i<adminDishesAddVO.getLunchDishId().size();i++){
            if(dishesDOMapper.getAliveForDishId(adminDishesAddVO.getLunchDishId().get(i))==0){
                adminDishesAddVO.getLunchDishId().remove(i);
                i--;
            }
        }
        for(int i=0;i<adminDishesAddVO.getNoonDishId().size();i++){
            if(dishesDOMapper.getAliveForDishId(adminDishesAddVO.getNoonDishId().get(i))==0){
                adminDishesAddVO.getNoonDishId().remove(i);
                i--;
            }
        }
        //判断菜品列表是否为空
        if(adminDishesAddVO.getBreakDishId().isEmpty()||
                adminDishesAddVO.getLunchDishId().isEmpty()||
                adminDishesAddVO.getNoonDishId().isEmpty()){
            throw new BusinessException(EmBusinessErr.DISHES_LIST_NOT_EMPTY);
        }
        //获取日期对应的用餐信息主键
        List<Integer> recordId = dishesRecordInformationDOMapper.selectRecordIdByOrderTime(adminDishesAddVO.getDate());
        //通过recordId列表将用餐记录表(dishes_record)中的相关数据删除
        try {
            dishesRecordDOMapper.deleteDishesRecordList(recordId);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.DELETE_DISHES_RECORD_FAILED);
        }
        //通过recordId列表将用餐信息表(dishes_record_information)中的相关数据删除
        try {
            dishesRecordInformationDOMapper.deleteDishesRecordInfoList(recordId);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.DELETE_DISHES_RECORD_INFO_FAILED);
        }
        //将修改后数据插入数据库
        addRecordDishes(adminDishesAddVO);
    }

    /**
     * @author CainMJ
     * 在用餐信息表与用餐记录表中插入数据
     */
    @Transactional
    public void InsertToDishesRecordData(Byte Type,
                                         DishesRecordInformationDO dishesRecordInformationDO,
                                         List<Integer> dishId) throws BusinessException {
        //设置RecordId(主键)为空,防止下次插入主键值重复
        dishesRecordInformationDO.setRecordId(null);
        //设置用餐类型
        dishesRecordInformationDO.setType(Type);
        //将用餐类型和用餐日期插入用餐信息表
        try {
            dishesRecordInformationDOMapper.insert(dishesRecordInformationDO);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.INSERT_DISHES_RECORD_INFO_FAILED);
        }
        //将recordID与dishID列表插入用餐记录表
        try {
            dishesRecordDOMapper.insertToDishesRecord(dishesRecordInformationDO.getRecordId(), dishId);
        } catch (Exception e) {
throw new BusinessException(e,EmBusinessErr.INSERT_DISHES_RECORD_FAILED);
        }
    }

    /**
     * @author CainMJ
     * 获得星期一的日期
     */
    private java.util.Date GetMonday(Date date) {
        calendar.setTime(date);
        intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //如果是周日，改变为上一周(美式时间周日为第一天)
        if (intWeek == 0)
            calendar.add(Calendar.DATE, -7);
        calendar.add(Calendar.DATE, 1 - intWeek);
        return calendar.getTime();
    }

    /**
     * @author CainMJ
     * 获得星期五的日期
     */
    private java.util.Date GetFriday(Date date) {
        calendar.setTime(date);
        intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        //如果是周日，改变为上一周(美式时间周日为第一天)
        if (intWeek == 0)
            calendar.add(Calendar.DATE, -7);
        calendar.add(Calendar.DATE, 7 - intWeek);
        return calendar.getTime();
    }

    /**
     * @author CainMJ
     * 将RecordDishesListVO转变为RecordDishesListGetVO
     */
    private List<RecordDishesListGetVO> RechangeRecordDishesListGetVO(List<RecordDishesListVO> recordDishesListVOList) {
        //创建返回数据List
        List<RecordDishesListGetVO> recordDishesListGetVOList = new ArrayList<>();
        //存储数据
        Date orderTime = null;
        List<String> breakfast = new ArrayList<>();
        List<String> lunch = new ArrayList<>();
        List<String> noon = new ArrayList<>();
        List<Integer> breakfastId = new ArrayList<>();
        List<Integer> lunchId = new ArrayList<>();
        List<Integer> noonId = new ArrayList<>();
        //遍历获得的菜谱数据
        for (int i = 0; i < recordDishesListVOList.size(); i++) {
            //第一次执行对时间赋值
            if (i == 0) {
                orderTime = recordDishesListVOList.get(i).getOrderTime();
            }
            //若时间相同，则在数组内添加数据
            //若时间不同，则插入将数据插入recordDishesListGetVOList
            if (orderTime.equals(recordDishesListVOList.get(i).getOrderTime())) {
                //根据菜谱类型，对List进行插入
                if(recordDishesListVOList.get(i).getType().equals(BREAK_DISH)){
                    breakfast.add(recordDishesListVOList.get(i).getName());
                    breakfastId.add(recordDishesListVOList.get(i).getDishId());
                }else if(recordDishesListVOList.get(i).getType().equals(LUNCH_DISH)){
                    lunch.add(recordDishesListVOList.get(i).getName());
                    lunchId.add(recordDishesListVOList.get(i).getDishId());
                }else if(recordDishesListVOList.get(i).getType().equals(NOON_DISH)){
                    noon.add(recordDishesListVOList.get(i).getName());
                    noonId.add(recordDishesListVOList.get(i).getDishId());
                }
            } else {
                //将数据插入RecordDishesListGetVOList
                recordDishesListGetVOList.add(InsertIntoRecordDishesListGetVOList(orderTime, breakfast, lunch, noon,
                        breakfastId, lunchId, noonId));
                //清空数组数据，为下个时间点准备
                breakfast = new ArrayList<>();
                lunch = new ArrayList<>();
                noon = new ArrayList<>();
                breakfastId = new ArrayList<>();
                lunchId = new ArrayList<>();
                noonId = new ArrayList<>();
                //将时间改为下一次时间
                orderTime = recordDishesListVOList.get(i).getOrderTime();
                i--;
            }
            if (i == recordDishesListVOList.size() - 1) {
                //将数据插入RecordDishesListGetVOList
                recordDishesListGetVOList.add(InsertIntoRecordDishesListGetVOList(orderTime, breakfast, lunch, noon,
                        breakfastId, lunchId, noonId));
            }
        }
        return recordDishesListGetVOList;
    }

    /**
     * @author CainMJ
     * 将数据传入RecordDishesListGetVO，并返回RecordDishesListGetVO
     */
    private RecordDishesListGetVO InsertIntoRecordDishesListGetVOList(Date orderTime,
                                                                      List<String> breakfast,
                                                                      List<String> lunch,
                                                                      List<String> noon,
                                                                      List<Integer> breakfastId,
                                                                      List<Integer> lunchId,
                                                                      List<Integer> noonId) {
        //新建recordDishesListGetVO用来插入List
        RecordDishesListGetVO recordDishesListGetVO = new RecordDishesListGetVO();
        //赋值
        recordDishesListGetVO.setOrderTime(orderTime);
        recordDishesListGetVO.setBreakfast(breakfast);
        recordDishesListGetVO.setLunch(lunch);
        recordDishesListGetVO.setNoon(noon);
        recordDishesListGetVO.setBreakfastId(breakfastId);
        recordDishesListGetVO.setLunchId(lunchId);
        recordDishesListGetVO.setNoonId(noonId);
        return recordDishesListGetVO;
    }
}
