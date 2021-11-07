package com.example.task10shardingsphere.service.impl;

import com.example.task10shardingsphere.dao.UserDao;
import com.example.task10shardingsphere.entity.UserInfo;
import com.example.task10shardingsphere.service.UserService;
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
