package com.example.task10shardingsphere.service;

import com.example.task10shardingsphere.entity.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> findAll();
    int addUser(UserInfo userInfo);
}
