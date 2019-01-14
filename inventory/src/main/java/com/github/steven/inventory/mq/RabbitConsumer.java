package com.github.steven.inventory.mq;

import com.steven.transaction.core.service.MqReceiveService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * The type Rabbit consumer.
 * @author xiaoyu
 */
@Component
@ConditionalOnProperty(prefix = "spring.rabbitmq", name = "host")
@RabbitListener(queues = "inventory",containerFactory = "myContainerFactory")
public class RabbitConsumer {

    private final MqReceiveService mqReceiveService;

    /**
     * Instantiates a new Rabbit consumer.
     *
     * @param mqReceiveService the  mq receive service
     */
    @Autowired(required = false)
    public RabbitConsumer(MqReceiveService mqReceiveService) {
        this.mqReceiveService = mqReceiveService;
    }


    /**
     * Process.
     *
     * @param msg the msg
     */
    @RabbitHandler
    public void process(byte[] msg) {
        mqReceiveService.processMessage(msg);
    }


}
