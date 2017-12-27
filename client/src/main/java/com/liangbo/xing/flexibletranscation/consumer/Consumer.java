package com.liangbo.xing.flexibletranscation.consumer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liangbo.xing.flexibletranscation.check.ConsumerChecker;
import com.liangbo.xing.flexibletranscation.client.TranscationMsgApi;
import com.liangbo.xing.flexibletranscation.client.TranscationMsgClient;
import com.liangbo.xing.flexibletranscation.domain.FtmAskingDto;
import com.liangbo.xing.flexibletranscation.domain.FtmMqContent;
import com.liangbo.xing.flexibletranscation.util.GsonUtil;
import com.rabbitmq.client.AMQP;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午8:07 xingliangbo Exp $
 */
@Component
@ConditionalOnBean(ConsumerChecker.class)
public class Consumer {

    private Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
    @Autowired
    private ConsumerChecker consumerChecker;

    @Autowired
    private TranscationMsgClient transcationMsgClient;

    @Autowired
    private Environment environment;

    @RabbitListener(queues = {"${ftm.transcation.client.queue}"},containerFactory = "rabbitListener")
    public void listener(Message message) {
        try {
            String msg = new String(message.getBody(), "UTF-8");
            LOGGER.info("receive from queue:{} message:{}", message.getMessageProperties().getConsumerQueue(), msg);
            FtmMqContent mqContent = GsonUtil.getGson().fromJson(msg, FtmMqContent.class);
            if (this.consumerChecker.consume(mqContent)) {
                transcationMsgClient.ask(new FtmAskingDto(mqContent.getMessageId(), mqContent.getPubId(), environment.getProperty("ftm.publish.app.key", "")));
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

}
