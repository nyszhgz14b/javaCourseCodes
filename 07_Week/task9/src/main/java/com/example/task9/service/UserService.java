package com.example.task9.service;


import com.example.task9.entity.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> findAll();
    int addUser(UserInfo userInfo);
}
