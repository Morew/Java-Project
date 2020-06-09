package com.nustti.controller;

import com.nustti.entity.UserEntity;
import com.nustti.service.UserService;
import com.nustti.utils.BaseApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 注册
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class RegisterController {
    @Autowired
    private UserService userService;

    BaseApiUtils base = new BaseApiUtils();

    @PostMapping("/register")
    public Map<String,Object> register(@RequestBody UserEntity userEntity){
        if(StringUtils.isEmpty(userEntity.getUsername())){
            return base.setResultParameterERROR("用户名称不能为空");
        }
        try {
            userService.register(userEntity);
            return base.setResultSuccess();
        }
        catch (Exception e){
            log.error("###regist() ERROR:",e);
            return base.setResultError("注册失败！");
        }

    }

}
