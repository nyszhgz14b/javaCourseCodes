package com.example.task9.dao;


import com.example.task9.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    List<UserInfo> findAll();
    int addUser(UserInfo userInfo);
}
