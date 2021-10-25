package com.example.week05.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = "com.example.week05.task2")
public class AutoAssemblyDemo {
    public static void main(String[] args){
        System.out.println(AutoAssemblyDemo.class.getPackage().getName());
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
       /* StudentService studentService = (StudentService) context.getBean("studentService");
        System.out.println(studentService.getStudent());*/
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(School1.class);
        School1 school = applicationContext.getBean(School1.class);
        System.out.println(school);
    }
}
