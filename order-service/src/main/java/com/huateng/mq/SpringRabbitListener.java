package com.huateng.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 监听MQ
 */
@Slf4j
@Component
public class SpringRabbitListener {
    @RabbitListener(bindings = @QueueBinding(//
            value = @Queue(name = "topic.queue1"),//,arguments = @Argument(name = "x-queue-model",value = "lazy")惰性队列，消息直接存储在硬盘上
            exchange = @Exchange(name = "it.topic",type = ExchangeTypes.TOPIC),//
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg){
//        System.out.println(1 / 0);
//        log.debug("消息处理完成！");
        System.out.println("消费者接收到topic.queue1的消息：【" + msg + "】");
    }
}
