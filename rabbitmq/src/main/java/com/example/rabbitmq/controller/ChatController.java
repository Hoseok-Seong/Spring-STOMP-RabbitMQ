package com.example.rabbitmq.controller;

import com.example.rabbitmq.model.Message;
import com.example.rabbitmq.procon.Consumer;
import com.example.rabbitmq.procon.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final Producer producer;
    private final Consumer consumer;

    @MessageMapping()
    public void sendMessage(Message message) {
        producer.sendMessage(message);
    }

}
