package com.example.producerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final AtomicInteger atomicInt = new AtomicInteger(0);

    private final String topicName;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderController(@Value("${topic-name}") String topicName, KafkaTemplate<String, Object> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void sendToKafka(Order order) {
        int key = atomicInt.incrementAndGet() % 3;
        kafkaTemplate.send(topicName, key, String.valueOf(key), order);
    }
}
