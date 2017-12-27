package com.liangbo.xing.flexibletranscation.producer.example;

import com.liangbo.xing.flexibletranscation.client.TranscationMsgApi;
import com.liangbo.xing.flexibletranscation.client.TranscationMsgClient;
import com.liangbo.xing.flexibletranscation.domain.FtmConfirmDto;
import com.liangbo.xing.flexibletranscation.domain.FtmPublishDto;
import com.liangbo.xing.flexibletranscation.domain.FtmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/25 下午12:59 xingliangbo Exp $
 */
@Component
public class ProducerExcample {

    @Autowired
    private TranscationMsgClient transcationMsgClient;

    public void test() {
        try {
            FtmPublishDto ftmPublishDto = new FtmPublishDto();
            FtmResponse ftmResponse = transcationMsgClient.publish(ftmPublishDto);
            /*
                执行业务
            */
            FtmConfirmDto ftmConfirmDto = new FtmConfirmDto();
            transcationMsgClient.comfirm(ftmConfirmDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
