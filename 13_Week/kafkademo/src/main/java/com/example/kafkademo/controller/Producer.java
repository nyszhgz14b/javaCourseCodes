package com.example.kafkademo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @RequestMapping("/sendMsg")
    public String sendMsg(String topic,String message) throws ExecutionException, InterruptedException {
        log.info("{}\t{}",topic,message);
        ListenableFuture future = kafkaTemplate.send(topic, message);
        Object o = future.get();
        log.info("future\t{}",o);
        return "sucess";
    }
}
