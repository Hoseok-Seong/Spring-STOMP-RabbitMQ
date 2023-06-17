package com.example.rabbitmq.procon;

import com.example.rabbitmq.model.Queue;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "queue")
    public void receiveMessage(Queue queue) {
        rabbitTemplate.receiveAndConvert(queue.getQueueName());
        System.out.println("메세지 받아짐");
    }
}
