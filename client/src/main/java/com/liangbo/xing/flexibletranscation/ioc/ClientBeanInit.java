package com.liangbo.xing.flexibletranscation.ioc;

import com.liangbo.xing.flexibletranscation.client.TranscationMsgClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 上午11:35 xingliangbo Exp $
 */
@Configuration
public class ClientBeanInit {

    @Autowired
    private Environment env;

    @Bean
    public TranscationMsgClient createTranscationMsgApi() {
        return new TranscationMsgClient(env.getProperty("ftm.asking.target.server.addr"));
    }
}
