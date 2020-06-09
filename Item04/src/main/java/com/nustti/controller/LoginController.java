package com.nustti.controller;

import com.nustti.entity.UserEntity;
import com.nustti.service.UserService;
import com.nustti.utils.BaseApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 实现登录
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    BaseApiUtils baseApiUtils = new BaseApiUtils();

    @PostMapping("/login")
    public Map<String,Object> login(@Param("account") String account, @Param("password") String password){
        return userService.login(account,password);
    }

    @PostMapping("/getUser")
    public Map<String, Object> getUser(@RequestParam("token") String token)
    {
        if(StringUtils.isEmpty(token)){
            return baseApiUtils.setResultError("token不能为空！");
        }
        return userService.getUser(token);
    }
}
