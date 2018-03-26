package com.lxy.ms.user.controller;

import com.github.pagehelper.PageInfo;
import com.lxy.ms.user.User;
import com.lxy.ms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("add")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "success";
    }

    @RequestMapping("all")
    public PageInfo<User> selectList(){
        return new PageInfo<>(userService.selectList());
    }
}
