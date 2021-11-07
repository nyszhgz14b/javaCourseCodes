package com.example.task10shardingsphere.dao;

import com.example.task10shardingsphere.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface UserDao {
    List<UserInfo> findAll();
    int addUser(UserInfo userInfo);
}
