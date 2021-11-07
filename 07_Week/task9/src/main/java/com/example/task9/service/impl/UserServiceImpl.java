package com.example.task9.service.impl;


import com.example.task9.dao.UserDao;
import com.example.task9.entity.UserInfo;
import com.example.task9.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public int addUser(UserInfo userInfo) {
        return userDao.addUser(userInfo);
    }
}
