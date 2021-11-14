package com.example.splittable.service.impl;


import com.example.splittable.dao.UserDao;
import com.example.splittable.entity.UserInfo;
import com.example.splittable.service.UserService;
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
