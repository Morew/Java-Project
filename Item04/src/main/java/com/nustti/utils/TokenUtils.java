package com.nustti.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component//注入容器中
public class TokenUtils {
    public String getToken(){
        return UUID.randomUUID().toString();
    }
}
