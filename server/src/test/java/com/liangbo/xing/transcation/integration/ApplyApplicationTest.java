package com.liangbo.xing.transcation.integration;

import com.liangbo.xing.AbstractTranscationTest;
import com.liangbo.xing.flexibletranscation.domain.FtmAskingDto;
import com.liangbo.xing.flexibletranscation.domain.FtmPublishDto;
import com.liangbo.xing.flexibletranscation.domain.msg.*;
import com.liangbo.xing.flexibletranscation.service.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.testng.annotations.Test;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/24 下午10:10 xingliangbo Exp $
 */
public class ApplyApplicationTest extends AbstractTranscationTest {


    @Autowired
    private AppService appService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicSubService topicSubService;
    @Autowired
    private MessagePublishService messagePublishService;
    @Autowired
    private TranscationMessageService transcationMessageService;

    /**
     * 申请应用测试
     */


    @Test
    @Transactional
    @Rollback(value = false)
    public void applyAppTest() {
        //创建一个生成者应用
        FtmAppApplyResp producerApp = appService.createApp(initFtmAppDtoProducer());
        String producerAppAppId = producerApp.getAppId();//应用唯一标识
        String producerAppAppKey = producerApp.getAppKey();//应用key 用于权限
        //创建一个消费者应用
        FtmAppApplyResp cunsumerApp = appService.createApp(initFtmAppDtoConsumer());
        String consumerAppId = cunsumerApp.getAppId();//应用唯一标识
        String consumerAppKey = cunsumerApp.getAppKey();//应用key 用于权限
        //创建一个主题
        FtmTopicApplyResp topicApplyResp = topicService.createTopic(initTopic());
        String topicId = topicApplyResp.getTopicId();
        //订阅者执行bingding
        FtmTopicSubDto ftmTopicSubDto = new FtmTopicSubDto();
        ftmTopicSubDto.setRountingKey("rountingKey-test");
        ftmTopicSubDto.setSubId(consumerAppId);
        ftmTopicSubDto.setTopicId(topicId);
        topicSubService.createTopicSub(ftmTopicSubDto);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testPublish() {
        //发布一条 消息
        FtmPublishDto ftmPublishDto = new FtmPublishDto();
        //生产者 配置
        ftmPublishDto.setPubId("48d6bd1a-606f-461e-bb4e-0e93bee9919e");
        ftmPublishDto.setPubkey("3c088d8a-4fc7-4ebf-9cc3-dca444abdd19");
        //主题配置
        ftmPublishDto.setTopicId("9e6486d1-af39-45d3-9b56-6d0756d87b7f");
        ftmPublishDto.setRouteKey("rountingKey-test");
        ftmPublishDto.setMessageBody(Base64Utils.encodeToString("test hello word".getBytes()));
        FtmResponse ftmResponse = messagePublishService.publish(ftmPublishDto);
        Assert.assertNotNull(ftmResponse.getMessageId());
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void testConfirm() {
        //确认发送
        messagePublishService.confirm("0e1b6d4b-b00e-4213-84be-0b8d2e7d78f7");
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void ask() {
        FtmAskingDto ftmAskingDto = new FtmAskingDto();
        ftmAskingDto.setAppKey("92dcc356-9bc6-4b20-acbe-31202f100234");
        ftmAskingDto.setSubId("513e1cd5-1f9f-4a2c-a3c4-bd5ef19cecb0");
        ftmAskingDto.setMessageId("0e1b6d4b-b00e-4213-84be-0b8d2e7d78f7");
        transcationMessageService.subscribeMsgToAskedStatus(ftmAskingDto);

    }


    public FtmAppDto initFtmAppDtoProducer() {
        FtmAppDto ftmAppDto = new FtmAppDto();
        ftmAppDto.setAppName("producer-test-1");
        ftmAppDto.setAppDesc(" producer test  123");
        ftmAppDto.setCallBackUrl("http://127.0.0.1:8080/");
        return ftmAppDto;
    }

    public FtmAppDto initFtmAppDtoConsumer() {
        FtmAppDto ftmAppDto = new FtmAppDto();
        ftmAppDto.setAppName("consumer-test-1");
        ftmAppDto.setAppDesc(" consumer test 123");
        ftmAppDto.setCallBackUrl("http://127.0.0.1:8080/");
        return ftmAppDto;
    }

    public FtmTopicDto initTopic() {
        FtmTopicDto ftmTopicDto = new FtmTopicDto();
        ftmTopicDto.setTopic("test-1");
        ftmTopicDto.setTopicDesc("test topic");
        ftmTopicDto.setMaxRetryTime(3);//最大3次
        ftmTopicDto.setRetryInterval(30);//30s执行一次
        return ftmTopicDto;
    }
}
