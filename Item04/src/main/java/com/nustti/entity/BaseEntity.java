package com.nustti.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

/**
 * 封装一些相同功能的字段和属性
 */
@Data
//@Slf4j//进行日志打印
public class BaseEntity {
    //主键
    private Long id;

    //创建时间
    private Timestamp created;


    //修改时间
    private Timestamp updated;



}
