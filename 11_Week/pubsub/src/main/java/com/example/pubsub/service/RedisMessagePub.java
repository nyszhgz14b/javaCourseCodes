package com.example.pubsub.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
@Slf4j
public class RedisMessagePub  {

    private final StringRedisTemplate redisTemplate;
    private final ChannelTopic topic;
    public void publish(String message) {
        log.info("send\t{}",message);
        redisTemplate.convertAndSend(topic.getTopic(),message);
    }
}
