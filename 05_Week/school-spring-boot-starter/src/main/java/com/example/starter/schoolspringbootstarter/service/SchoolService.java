package com.example.starter.schoolspringbootstarter.service;

import org.springframework.stereotype.Service;


public class SchoolService {
    //校名
    private String name;
    //城市
    private String city;
    //乡镇
    private String towns;

    public SchoolService(String name, String city, String towns) {
        this.name = name;
        this.city = city;
        this.towns = towns;
    }

    public String recruitTeachers(){
        System.out.println("recruit teachers");
        return this.city+" "+this.towns+" "+this.name;
    }
}
