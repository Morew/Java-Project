package com.nustti.mapper;

import com.nustti.utils.ReflectionUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BaseProvider {
    /**
     * 自定义封装sql语句
     * @return
     */
    public String save(Map<String,Object> map){
        //实体类
        Object oj = map.get("oj");
        //表名称
        String table = (String) map.get("table");
        //生成添加到sql语句，使用反射机制
        //步骤：使用反射机制加载这个类的所有属性
        SQL sql = new SQL(){{
            INSERT_INTO("mb_user");
            VALUES(ReflectionUtils.fatherAndSonFields(oj),ReflectionUtils.fatherAndSonValue(oj));
        }};
        return sql.toString();
    }
}
