package com.example.splittable.dao;


import com.example.splittable.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface UserDao {
    List<UserInfo> findAll();
    int addUser(UserInfo userInfo);
}
