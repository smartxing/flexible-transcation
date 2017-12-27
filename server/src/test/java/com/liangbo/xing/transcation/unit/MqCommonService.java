package com.liangbo.xing.transcation.unit;

import com.liangbo.xing.flexibletranscation.FlexibleTranscationApplication;
import com.liangbo.xing.flexibletranscation.domain.FtmMqContent;
import com.liangbo.xing.flexibletranscation.service.MqCommpontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/26 上午10:31 xingliangbo Exp $
 */
@SpringBootTest(classes = FlexibleTranscationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MqCommonService  extends AbstractTestNGSpringContextTests{

    @Autowired
    private MqCommpontService mqCommpontService;

    @Test
    public void test(){
        FtmMqContent ftmMqContent = new FtmMqContent();
        ftmMqContent.setRoutingKey("rrrrrr");
        ftmMqContent.setPubId("1111");
        ftmMqContent.setCreateTime(new Date());
        mqCommpontService.send("ftmtest", "mytest-routingkey", ftmMqContent);
    }

}
