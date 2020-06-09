package com.nustti.utils;


import java.sql.Timestamp;
import java.util.Date;

public class DataUtils {


    /**
     * 获得当前unix时间戳（单位秒）
     * @return
     */
    public static  long currentTimeStamp(){
        return  System.currentTimeMillis() / 1000;
    }
    /**
     * 获取当前系统时间戳
     * @return
     */
    public static Timestamp getTimeStamp(){
        return  new Timestamp(new Date().getTime());
    }
}
