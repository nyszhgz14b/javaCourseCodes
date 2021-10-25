package com.example.week05.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    Student student ;
    @Bean(name = "student")
    public Student getStudent(){
        return student;
    }
}
