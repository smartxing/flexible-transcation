package com.liangbo.xing.flexibletranscation.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 上午10:20 xingliangbo Exp $
 */
@Service
public class MqCommpontService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String topic, String routingKey, Object obj) {
        rabbitTemplate.convertAndSend(topic, routingKey, obj);
    }

}
