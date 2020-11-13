package com.retror.rabbitmq.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitQueueConfig {

    //定义队列，交换机，路由键（正常）
    public static final String NORMAL_QUEUE = "normar-queue";
    public static final String NORMAL_EXCHANGE = "normar-exchange";
    public static final String NORMAL_ROUTINGKEY = "normar-routingkey";


    //定义队列，交换机，路由键（死信）
    public static final String DELAY_QUEUE = "delay-queue";
    public static final String DELAY_EXCHANGE = "delay-exchange";
    public static final String DELAY_ROUTINGKEY = "delay-routingkey";


    @Bean
    public Queue norma1Queue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 15000);//message在该队列queue的存活时间最大为10秒
        map.put("x-dead-letter-exchange", DELAY_EXCHANGE); //x-dead-letter-exchange参数是设置该队列的死信交换器（DLX）
        map.put("x-dead-letter-routing-key", DELAY_ROUTINGKEY);//x-dead-letter-routing-key参数是给这个DLX指定路由键
        return new Queue(NORMAL_QUEUE, true, false, false, map);
    }

    //直连交换机
    @Bean
    public DirectExchange normalExchange() {
        return new DirectExchange(NORMAL_EXCHANGE, true, false);
    }

    @Bean
    public Binding normalBinding() {
        return BindingBuilder.bind(norma1Queue()).to(normalExchange())
                .with(NORMAL_ROUTINGKEY);
    }

//死信

    @Bean
    public Queue delaQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE, true, false);

    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delaQueue()).to(delayExchange())
                .with(DELAY_ROUTINGKEY);
    }
}
