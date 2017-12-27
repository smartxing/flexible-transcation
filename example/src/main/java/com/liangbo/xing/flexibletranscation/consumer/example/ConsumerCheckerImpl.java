package com.liangbo.xing.flexibletranscation.consumer.example;

import com.liangbo.xing.flexibletranscation.check.ConsumerChecker;
import com.liangbo.xing.flexibletranscation.domain.FtmMqContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/25 下午12:48 xingliangbo Exp $
 */
@Configuration
public class ConsumerCheckerImpl extends ConsumerChecker {

    private Logger logger = LoggerFactory.getLogger(ConsumerCheckerImpl.class);

    @Override
    public boolean consume(FtmMqContent ftmMqContent) {
        //处理 mq消息 即可,注意 处理幂等性
        logger.info("接受mq消息 {}", ftmMqContent);
        return true;
    }
}
