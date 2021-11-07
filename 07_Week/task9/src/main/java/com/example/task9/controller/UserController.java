package com.example.task9.controller;


import com.example.task9.entity.UserInfo;
import com.example.task9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @RequestMapping("/findAll")
    public @ResponseBody List<UserInfo> findAll(){
        boolean b = false;
        return service.findAll();
    }
    @RequestMapping("/addUser")
    public @ResponseBody int addUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setCertNo("1111111111111");
        userInfo.setName("2222222222");
        userInfo.setPhone("111111111");
        userInfo.setPasswd("11111111111");
        userInfo.setUserName("33333333333333");
        userInfo.setSex(true);
        return service.addUser(userInfo);
    }
}
