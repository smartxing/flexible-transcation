package com.liangbo.xing.flexibletranscation.producer.example;

import com.liangbo.xing.flexibletranscation.check.ProducerChecker;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckDto;
import com.liangbo.xing.flexibletranscation.enums.TranscationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/25 下午12:50 xingliangbo Exp $
 */
@Configuration
public class ProducerCheckImpl extends ProducerChecker {

    private Logger logger = LoggerFactory.getLogger(ProducerCheckImpl.class);

    @Override
    public String check(FtmProducerCheckDto ftmProducerCheckDto) {

        logger.info("test check" + ftmProducerCheckDto.getMessageBody());
        /*
            回调检查方法
            1 自己根据mq消息 确认 业务是否可以确认 发送了
         */

        return TranscationStatus.OK.getVal();
    }
}
