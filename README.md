# Spring-STOMP-RabbitMQ

* 프로젝트 목표
  - Springboot Websocket을 사용한 STOMP 메세징 구현
  - Springboot와 RabbitMQ 연동하여 Messaging Queue 구현

## STOMP

* STOMP 프로토콜 적용 이유
  - Low-Level의 웹소켓의 단점 보완
  - Pub/Sub 기반으로 동작하여 그룹 채팅 구현 가능

* Pub/Sub
![pubsub](https://github.com/Hoseok-Seong/Spring-STOMP-RabbitMQ/assets/93416157/845b1550-32e8-400f-819e-451337166087)

* build.gradle 의존성 추가
  - implementation 'org.springframework.boot:spring-boot-starter-websocket'

* WebSocketConfig 코드
``` bash
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 테스트할 때만 setAllowedOrigins("*"); 허용
        registry.addEndpoint("/ws").setAllowedOrigins("*");
    }
}
```

* ChatController 코드
``` bash
@MessageMapping("/chatroom/{id}")
    public void sendMessage(@DestinationVariable("id") Long id, ChatMessageReq chatMessageReq) {
        simpMessageSendingOperations.convertAndSend
                ("/sub/chatroom/" + chatMessageReq.getChatroomId(), chatMessageReq);
        log.info("메세지 전송 성공");
        chatMessageService.saveMessage(chatMessageReq);
    }
```

* 기술 블로그
  - https://white-developer.tistory.com/78
  - https://white-developer.tistory.com/80

## RabbitMQ

* Messaging Queue 구현 이유
  - 분산 시스템에서 컴포넌트 간에 메세지 송수신이 유용
  - 비동기 통신이 가능

* Messaging Queue
![image](https://github.com/Hoseok-Seong/Spring-STOMP-RabbitMQ/assets/93416157/29ba349b-0386-4361-93dc-b0ee967c6b61)

* Topic Exchange 구조

![image](https://github.com/Hoseok-Seong/Spring-STOMP-RabbitMQ/assets/93416157/1180ed65-ed4a-4f38-877d-9326b424b16f)

* Topic Exchange와 Routing Key 
  - 대표적인 Topic Exchange를 선택해서 구현
  - 라우팅 키는 "foo.bar.#" 로 설정

* build.gradle 의존성 추가
  - implementation 'org.springframework.boot:spring-boot-starter-websocket'

* Producer 코드
``` bash
@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    public void sendMessage(Message message) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), message.getRoutingKey(), message);
        log.info("메세지 발신 성공");
    }
}
```

* Consumer 코드
``` bash
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
```

* 기술 블로그
  - https://white-developer.tistory.com/78
  - https://white-developer.tistory.com/84
