package com.huateng.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 消息发送者
 */
@Slf4j
@RestController
@RequestMapping("/mq")
public class Publisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

   /* @GetMapping("/{message}")
    public void send1(@PathVariable String message){
        // 队列名称
        String exchangeName = "it.topic";
        // 路由key
        String routingKey = "china.haha";

        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName,routingKey,message);
    }*/

    @GetMapping("/{message}")
    public void send(@PathVariable String message){

        // 2.全局唯一的消息ID，需要封装到CorrelationData中
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        // 3.添加callback
        correlationData.getFuture().addCallback(
                result -> {
                    if(result.isAck()){
                        // 3.1.ack，消息成功
                        log.debug("消息发送成功, ID:{}", correlationData.getId());
                    }else{
                        // 3.2.nack，消息失败
                        log.error("消息发送失败, ID:{}, 原因{}",correlationData.getId(), result.getReason());
                    }
                },
                ex -> log.error("消息发送异常, ID:{}, 原因{}",correlationData.getId(),ex.getMessage())
        );
        // 4.发送消息
        rabbitTemplate.convertAndSend("it.topic", "china.topic", message, correlationData);

    }
}
