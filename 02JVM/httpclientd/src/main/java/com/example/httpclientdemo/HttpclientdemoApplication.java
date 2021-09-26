package com.example.httpclientdemo;

import com.example.httpclientdemo.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class HttpclientdemoApplication implements ApplicationRunner {
    @Value("${http.url}")
    private String url;
    public static void main(String[] args) {
        SpringApplication.run(HttpclientdemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String result = HttpClientUtils.httpRequest(url, "GET", new HashMap());
        System.out.println(result);
    }
}
