package com.example.splittable.service;



import com.example.splittable.entity.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> findAll();
    int addUser(UserInfo userInfo);
}
