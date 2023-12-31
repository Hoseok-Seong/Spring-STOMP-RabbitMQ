package com.example.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String routingKey;
    private String sender;
    private String message;
}
