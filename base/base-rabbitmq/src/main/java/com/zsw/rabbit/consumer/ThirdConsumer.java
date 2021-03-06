package com.zsw.rabbit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Administrator on 2019/8/25 19:24
 **/
@Slf4j
@Component
@RabbitListener(queues = "${com.zsw.rabbitmq.queue.third}", containerFactory = "simpleRabbitListenerContainerFactory")
public class ThirdConsumer {

    @RabbitHandler
    public void handler(String message) {
        log.info("consumer 3, receive message: {}", message);
    }

}
