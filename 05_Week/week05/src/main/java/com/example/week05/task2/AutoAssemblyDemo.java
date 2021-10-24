package com.example.week05.task2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoAssemblyDemo {
    public static void main(String[] args){
        System.out.println(AutoAssemblyDemo.class.getPackage().getName());
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
       /* StudentService studentService = (StudentService) context.getBean("studentService");
        System.out.println(studentService.getStudent());*/

        School school = (School) context.getBean("school");
        System.out.println(school);
    }
}
