package com.liangbo.xing.flexibletranscation.config.mq;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingliangbo
 * @version $Id: RabbitMqConfig, v 0.1 2017-03-01-下午4:54 xingliangbo Exp $
 */
@Configuration
public class RabbitMqConfig {


    /**
     * RabbitMQ server地址
     */
    @Value("${ftm.transcation.message.mq.address}")
    private String  address;

    /**
     *  RabbitMQ server鉴权用户名
     */
    @Value("${ftm.transcation.message.mq.username}")
    private String  mqUserName;

    /**
     * RabbitMQ server鉴权密码
     */
    @Value("${ftm.transcation.message.mq.password}")
    private String  mqPwd;

    @Value("${ftm.transcation.message.mq.virtual-host}")
    private String  mqVirtualhost;

    /**
     *  每个queue消费者并发数
     */
    @Value("${ftm.transcation.message.mq.concurrent_consumer:1}")
    private Integer mqConsumerSize;

    /**
     *  连接池
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(mqUserName);
        connectionFactory.setPassword(mqPwd);
        connectionFactory.setVirtualHost(mqVirtualhost);
        return connectionFactory;
    }

    /**
     * @return
     */
    @Bean(name = "rabbitListener")
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());//默认采用所有的消息采用json序列化的方式
        factory.setConcurrentConsumers(mqConsumerSize);
        return factory;
    }

    /**
     * @return
     */
    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
