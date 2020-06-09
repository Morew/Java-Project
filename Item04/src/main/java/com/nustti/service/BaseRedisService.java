package com.nustti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BaseRedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //已经封装了一些增删查改操作

    public void setString(String key,String value,Long timeOut){
        set(key,value,timeOut);
    }
    public void set(String key, Object value,Long timeOut){
        if(value!=null){
            if(value instanceof String ){
                String setValue = (String) value;
                stringRedisTemplate.opsForValue().set(key,setValue);
            }else if(value instanceof  Long) {
                String value1 = String.valueOf(value);
                stringRedisTemplate.opsForValue().set(key,value1);
            }
            //设置有效期
            if(timeOut!=null){
                stringRedisTemplate.expire(key,timeOut, TimeUnit.SECONDS);
            }

        }
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delete(String key){
        stringRedisTemplate.delete(key);
    }




}
