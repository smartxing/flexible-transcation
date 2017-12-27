package com.liangbo.xing.transcation.unit;

import com.liangbo.xing.AbstractTranscationTest;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmTopicApplyResp;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmTopicDto;
import com.liangbo.xing.flexibletranscation.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/24 下午10:01 xingliangbo Exp $
 */
public class TopicServiceTest extends AbstractTranscationTest {

    private Logger logger = LoggerFactory.getLogger(TopicServiceTest.class);

    @Autowired
    private TopicService topicService;

    @Test
    @Transactional
    @Rollback
    public void createTopicTest() {
        FtmTopicDto ftmTopicDto = new FtmTopicDto();
        ftmTopicDto.setTopic("test");
        ftmTopicDto.setMaxRetryTime(1);
        ftmTopicDto.setRetryInterval(1000);
        FtmTopicApplyResp ftmTopicApplyResp = topicService.createTopic(ftmTopicDto);
        logger.info(ftmTopicApplyResp.getTopicId() + "," + ftmTopicApplyResp.getTopicName());
        Assert.assertNotNull(ftmTopicApplyResp.getTopicId());
        Assert.assertEquals(ftmTopicApplyResp.getTopicName(), "test");
    }
}
