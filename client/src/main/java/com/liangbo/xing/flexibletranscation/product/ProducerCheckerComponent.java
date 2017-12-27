package com.liangbo.xing.flexibletranscation.product;

import com.liangbo.xing.flexibletranscation.check.ProducerChecker;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckDto;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckResp;
import com.liangbo.xing.flexibletranscation.enums.TranscationStatus;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午8:59 xingliangbo Exp $
 */
@RestController
public class ProducerCheckerComponent {

    private Logger LOGGER = LoggerFactory.getLogger(ProducerCheckerComponent.class);

    @Autowired
    private ProducerChecker producerChecker;

    @RequestMapping(value = "/transcation/check", method = RequestMethod.POST)
    public String check(@RequestBody FtmProducerCheckDto ftmProducerCheckDto) {
        try {
            return producerChecker.check(ftmProducerCheckDto);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return TranscationStatus.UNKNOW.getVal();
    }

}
