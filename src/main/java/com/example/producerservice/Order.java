package com.example.producerservice;

import lombok.Data;

import java.util.UUID;

@Data
public class Order {
    private UUID id;
    private OrderStatus status;
    private String payload;

    enum OrderStatus {
        CREATED,
        DELETED,
        APPROVED
    }
}
