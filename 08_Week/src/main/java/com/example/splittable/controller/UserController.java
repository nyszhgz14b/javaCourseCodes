package com.example.splittable.controller;


import com.example.splittable.entity.UserInfo;
import com.example.splittable.service.UserService;
import com.example.utils.SnowFlakeUtils;
import org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;
import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;
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
       // userInfo.setId(System.currentTimeMillis());
        userInfo.setCertNo("1111111111111");
        userInfo.setName("2222222222");
        userInfo.setPhone("111111111");
        userInfo.setPasswd("11111111111");
        userInfo.setUserName("33333333333333");
        userInfo.setSex(true);
        return service.addUser(userInfo);
    }
}
