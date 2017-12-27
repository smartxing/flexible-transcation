package com.liangbo.xing.flexibletranscation.config.mq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingliangbo
 * @version $Id: MqExchangeQueueConfig, v 0.1 2017-03-01-下午5:36 xingliangbo Exp $
 */
@Configuration
public class MqExchangeQueueConfig {


    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


}
