package com.nustti.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
public class DemoController {
    public static final String INDEX = "index";

    @RequestMapping("/index")
    public String index(){
        log.info("项目已经启动");
        return INDEX;
    }
}
