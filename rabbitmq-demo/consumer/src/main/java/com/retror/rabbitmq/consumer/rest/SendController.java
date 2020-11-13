package com.retror.rabbitmq.consumer.rest;



import com.retror.rabbitmq.consumer.bean.OrderVo;
import com.retror.rabbitmq.provider.config.RabbitQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class SendController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/senderBymodel")
    public  Map<String,Object> senderBymodel(String orderNo){
        OrderVo orderVo=new OrderVo();

        orderVo.setOrderId(10000001L);
        orderVo.setOrderNo(orderNo);
        orderVo.setCreatedate(new Date());
        //推送消息
        rabbitTemplate.convertAndSend(RabbitQueueConfig.NORMAL_EXCHANGE,
                RabbitQueueConfig.NORMAL_ROUTINGKEY,orderVo);
        log.info("生产者发送消息,Exchange={}","routingkey={}",RabbitQueueConfig.NORMAL_EXCHANGE,
                RabbitQueueConfig.NORMAL_ROUTINGKEY);
        Map<String,Object> json=new HashMap<>();
        json.put("code",1);
        json.put("msg","发送消息成功。。。");
        return json;
    }


    @RequestMapping("/sender")
    public  Map<String,Object> sender(){
        Map<String,Object> data=this.createData();

        //推送消息
        rabbitTemplate.convertAndSend(RabbitQueueConfig.NORMAL_EXCHANGE,
                RabbitQueueConfig.NORMAL_ROUTINGKEY,data);
        log.info("生产者发送消息,Exchange={}","routingkey={}",RabbitQueueConfig.NORMAL_EXCHANGE,
                RabbitQueueConfig.NORMAL_ROUTINGKEY);
        Map<String,Object> json=new HashMap<>();
        json.put("code",1);
        json.put("msg","发送消息成功。。。");
        return json;
    }

    private Map<String,Object> createData(){
        Map<String,Object> data=new HashMap<>();
        String  createData= LocalDateTime.now().
                format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String message="hello rabbitmq!!!";
        data.put("createData",createData);
        data.put("message",message);
        return data;
    }


}
