package com.example.stomp.controller;

import com.example.stomp.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessageSendingOperations operations;

    // /pub/chatroom/id
    @MessageMapping("/channel/{id}")
    public void sendMessage(@DestinationVariable("id") Long id, Message message) {
        operations.convertAndSend
                ("/sub/channel/" + message.getChannelId(), message);
        log.info("메세지 전송 성공");
    }
}
