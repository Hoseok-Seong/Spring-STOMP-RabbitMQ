package com.example.rabbitmq.controller;

import com.example.rabbitmq.model.Message;
import com.example.rabbitmq.procon.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final Producer producer;

    @PostMapping("/chat")
    public void sendMessage(@RequestBody Message message) {
        producer.sendMessage(message);
    }

}
