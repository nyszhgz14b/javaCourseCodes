package com.example.week05.controller;

import com.example.starter.schoolspringbootstarter.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SchoolController {
    @Autowired(required = false)
    @Qualifier("school")
    private SchoolService service;
    @GetMapping("/recruitTeachers")
    public String recruitTeachers(){
        System.out.println(service);
        return service.recruitTeachers();
    }
}
