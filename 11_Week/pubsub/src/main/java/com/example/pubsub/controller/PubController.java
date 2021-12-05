package com.example.pubsub.controller;

import com.example.pubsub.service.RedisMessagePub;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class PubController {
    @Autowired
    private RedisMessagePub redisMessagePub;
    @GetMapping("/push")
    public String push(String message){
        redisMessagePub.publish(message);
        return "pub:\t"+message;
    }
}
