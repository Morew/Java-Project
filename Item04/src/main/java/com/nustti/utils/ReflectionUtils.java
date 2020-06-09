package com.nustti.utils;

import com.nustti.entity.UserEntity;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.sql.Timestamp;

public class ReflectionUtils {
    /**
     * 反射工具类
     *封装当前类和父类的属性值
     * @return
     */
    public static String fatherAndSonValue(Object oj){
        if(oj == null){
            return null;
        }
        Class classInfo = oj.getClass();
        /**通过反射操作类的私有（private）成员变量时，需要通过field.setAccessible(true)将字段设置为可以访问的。**/
        Field[] declaredFields = classInfo.getDeclaredFields();
        Field.setAccessible(declaredFields,true);
        Field[] declaredFields1 = classInfo.getSuperclass().getDeclaredFields();
        Field.setAccessible(declaredFields1,true);
        String sonField = getFiledValue(oj,declaredFields);
        String fatherField = getFiledValue(oj,declaredFields1);
        return sonField + "," + fatherField;
    }

    /**
     * 获取到属性值
     * @param oj
     * @param declareFileds
     * @return
     */
    public static String  getFiledValue(Object oj,Field[] declareFileds) {
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < declareFileds.length; i++) {
            //获取到属性值
            try {
                /**
                 * 另一种方式获取私有属性 declareFileds[i].setAccessible(true);
                 */
                Field field = declareFileds[i];
                Object o = field.get(oj);
                //标识类型是否为String类型
                boolean flag = false;
                if(o != null && (o instanceof  String) || (o instanceof Timestamp)){
                    flag = true;
                }
                if(flag){
                    sf.append("'" + o + "'");
                }else{
                    sf.append(o);
                }
                if(i < declareFileds.length-1){
                    sf.append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return sf.toString();
    }

    /**
     * 获取属性名称
     * @param field
     * @return
     */
    public  static  String getFiled(Field[] field){
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < field.length; i++) {
            sf.append(field[i].getName());
            if(i < field.length - 1){
                sf.append(",");
            }
        }
        return sf.toString();
    }
    public static String fatherAndSonFields(Object oj){
        if(oj == null){
            return null;
        }
        //获取class文件
        Class classInfo = oj.getClass();

        //获取父类属性
        Field[] declareFileds= classInfo.getSuperclass().getDeclaredFields();
        //获取当前类的所有属性
        Field[] declareFileds1 = classInfo.getDeclaredFields();
        String s2 = getFiled(declareFileds);
        String s1 = getFiled(declareFileds1);
        return s1  +","+ s2;
    }

    /*public static void main(String[] args) {
        UserEntity testEntity = new UserEntity();
        testEntity.setUsername("张三");
        testEntity.setPassword("123456");
        testEntity.setPhone("13456788990");
        testEntity.setCreated(DataUtils.getTimeStamp());
        testEntity.setUpdated(DataUtils.getTimeStamp());
        testEntity.setId(1L);
        String fatherAndSonFields = fatherAndSonFields(testEntity);
        String insertFileds = fatherAndSonValue(testEntity);
        System.out.println(fatherAndSonFields);
        System.out.println(insertFileds);


        //封装mysql
        SQL sql = new SQL(){{
            INSERT_INTO("mb_user");
            VALUES(fatherAndSonFields,insertFileds);
        }};
        System.out.println(sql.toString());
    }
*/
}
