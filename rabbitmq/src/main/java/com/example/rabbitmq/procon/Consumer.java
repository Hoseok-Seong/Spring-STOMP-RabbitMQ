package com.example.rabbitmq.procon;

import com.example.rabbitmq.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@RabbitListener(queues = "#{queue.name}")
@Slf4j
public class Consumer {

    @RabbitHandler
    public void receiveMessage(final Message message) {

        log.info("메세지 수신 성공! " + message.getSender() + ": " + message.getMessage());
    }
}
