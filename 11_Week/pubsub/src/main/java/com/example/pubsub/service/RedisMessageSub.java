package com.example.pubsub.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class RedisMessageSub implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
      log.info("接收到订单信息\t{}",message);
    }
}
