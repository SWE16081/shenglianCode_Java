package com.youjiao.demo.util;

import java.lang.reflect.Field;

/**
 * @author Ck
 * #date 2019/03/26 19:38
 */
public final class CompareUtil {

    /**
     * @author Ck
     * 判断DO VO中相同属性的值是否相等，相同则将DO中置为null，否则设为VO值，返回结构为DO，并且当DO为null时，表示DO VO相同字段完全相同
     */
    public static Object CompareDOVO(Object DO, Object VO) {
        //System.out.println("---");
        if (DO == null || VO == null) {
            return null;
        }
        Field[] fieldsVO = VO.getClass().getDeclaredFields();
        Field[] fieldsDO = DO.getClass().getDeclaredFields();
        //标记该DO是否与VO不同，完全相同为false即初值，否则为true
        boolean isChange = false;
        try {
            //遍历VO中的属性
            for (Field fieldVO : fieldsVO) {
                fieldVO.setAccessible(true);
                //遍历DO中的属性
                for (Field fieldDO : fieldsDO) {
                    fieldDO.setAccessible(true);
                    //若属性名相同
                    if (fieldVO.getName().equals(fieldDO.getName())) {
                        //若属性值相同则将DO中的值设为null
                        //System.out.println(isChange);
                        if (fieldVO.get(VO).equals(fieldDO.get(DO))) {
                            fieldDO.set(DO, null);
                            //System.out.println("if "+isChange);
                        } else {
                            //若属性值不同则将DO中的值设为VO中的值，并且标记该DO已经修改
                            fieldDO.set(DO, fieldVO.get(VO));
                            isChange = true;
                            //System.out.println("else "+isChange);
                        }
                        //System.out.println("fieldNameVO：" + fieldVO.getName() + "\t,value:" + fieldVO.get(VO));
                        //System.out.println("fieldNameDO：" + fieldDO.getName() + "\t,value:" + fieldDO.get(DO));
                        //System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        //如果DO被标记不需要修改isChange==false，那么将DO设为null对象 表示不需要更新
        //System.out.println("end "+isChange);
        if (!isChange) {
            return null;
        } else {
            return DO;
        }
    }
}
