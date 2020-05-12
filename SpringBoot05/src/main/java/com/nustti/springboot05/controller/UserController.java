package com.nustti.springboot05.controller;

import com.nustti.springboot05.bean.User;
import com.nustti.springboot05.service.UserService;
import com.nustti.springboot05.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String Hello(){
        return "Hello ABCD!";
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name","Jack");
        model.addAttribute("age",20);
        model.addAttribute("info","我是一个爱学习的好青年");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/save")
    public String save(User user){
        userService.save(user);
        return "save success!";
    }

    @RequestMapping("/userList")
    //
    // @ResponseBody
    public String userList(Model model){
        List<User> userList = userService.getUserList();
        model.addAttribute("userList",userList);
        return "list";

    }

    @ResponseBody
    @RequestMapping("/findOne")
    public User findOne(@RequestParam(value = "id") int id){
       return userService.find(id);
    }
    /**
     * 删除数据库的一条信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id") int id){
        User user = userService.find(id);
        userService.delete(user);
        return "delete success!";
    }

    /**
     * 更新数据库里的信息
     * @param id
     * @param age
     * @param name
     * @param description
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public String update(@RequestParam(value = "id") int id,int age,String name,String description){
        User user = userService.find(id);
        user.setName(name);
        user.setAge(age);
        user.setInfo(description);
        userService.save(user);
        return "update success!";
    }

}

