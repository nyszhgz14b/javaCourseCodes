package com.example.task10shardingsphere.entity;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String userName;
    private String certNo;
    private String name;
    private boolean sex;
    private String passwd;
    private String phone;
    private String createTime;
    private String updateTime;
    private String reamrk;
    private String resver1;
    private String resver2;
}
