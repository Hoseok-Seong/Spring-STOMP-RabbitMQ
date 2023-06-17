package com.example.rabbitmq.procon;

import com.example.rabbitmq.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Message message) {
        rabbitTemplate.convertAndSend(message.getRoutingKey(), message);
        System.out.println("메세지 보내짐");
    }
}
