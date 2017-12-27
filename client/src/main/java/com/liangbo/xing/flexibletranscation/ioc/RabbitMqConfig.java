package com.liangbo.xing.flexibletranscation.ioc;

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

    @Value("${ftm.transcation.message.mq.address}")
    private String  address;
    @Value("${ftm.transcation.message.mq.username}")
    private String  mqUserName;
    @Value("${ftm.transcation.message.mq.password}")
    private String  mqPwd;
    @Value("${ftm.transcation.message.mq.virtual-host}")
    private String  mqVirtualhost;

    /**
     *  连接池
     * @return
     */

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(mqUserName);
        connectionFactory.setPassword(mqPwd);
        connectionFactory.setAddresses(address);
        connectionFactory.setVirtualHost(mqVirtualhost);
        return connectionFactory;
    }

    @Bean(name = "rabbitListener")
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());//默认采用所有的消息采用json序列化的方式
        return factory;
    }


}
