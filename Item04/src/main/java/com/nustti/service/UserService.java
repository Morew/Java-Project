package com.nustti.service;

import com.nustti.entity.UserEntity;
import com.nustti.utils.BaseApiUtils;

import java.util.Map;

/**
 * 用户服务
 */

public interface UserService {
     void register(UserEntity userEntity);

     String md5PassSalt(String phone,String password);

     /**
      * account 账户（选择邮箱或者手机密码登录）
      * 功能：登陆成功后，生成对应的token,作为key,将用户userID存放在redis中
      * 登录服务
      */
     Map<String,Object> login(String account,String password);

     Map<String,Object> getUser(String token);

}
