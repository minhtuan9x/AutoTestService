package com.example.agent.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {


    @KafkaListener(topics = "example-topic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }

}
