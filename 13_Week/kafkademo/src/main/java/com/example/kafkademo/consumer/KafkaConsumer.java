package com.example.kafkademo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"test","aaa"})
    public void listen(ConsumerRecord consumerRecord){
        System.out.println(consumerRecord.topic()+":"+consumerRecord.value());
    }
}
