package com.retror.rabbitmq.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Slf4j
@RabbitListener(queues = {"delay-queue"})
public class RabbitDelayReceiver {
    //接收延迟队列的信息
    @RabbitHandler
    public void handleMessage(Map<String,Object> map) {
        log.info("消费者接收到了消息,ts={}", LocalDateTime.now().toString());
        log.info("map={}",map);
    }
}
