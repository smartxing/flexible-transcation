package com.liangbo.xing.flexibletranscation.controller;

import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.domain.*;
import com.liangbo.xing.flexibletranscation.domain.msg.*;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmResponse;
import com.liangbo.xing.flexibletranscation.exception.FtmApiException;
import com.liangbo.xing.flexibletranscation.exception.FtmClientException;
import com.liangbo.xing.flexibletranscation.exception.FtmBizException;
import com.liangbo.xing.flexibletranscation.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午11:13 xingliangbo Exp $
 */
@RestController
@RequestMapping(value = FlexibleTranscationConstants.BASE_PATH + "/v1/manager", produces = "application/json")
public class MessageManagerController {


    @Autowired
    private TranscationMessageService transcationMessageService;

    @Autowired
    private MessagePublishService messagePublishService;

    @Autowired
    private AppService appService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicSubService topicSubService;


    @RequestMapping(value = "/ask", method = RequestMethod.POST)
    public FtmCommonResponse ask(@RequestBody FtmAskingDto ftmAskingDto) {
        //如果 httpcode 是200 则确认成功,否则一律返回400
        try {
            transcationMessageService.subscribeMsgToAskedStatus(ftmAskingDto);
        } catch (FtmBizException e) {
            throw new FtmApiException(e);
        }
        return FtmRespKey.SUCCESS;
    }


    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    public FtmResponse publish(@RequestBody FtmPublishDto ftmPublishDto) {
        try {
            return messagePublishService.publish(ftmPublishDto);
        } catch (FtmBizException e) {
            throw new FtmApiException(e);
        }
    }


    @RequestMapping(value = "/msg/confirmed", method = RequestMethod.POST)
    public FtmCommonResponse confirm(@RequestBody FtmConfirmDto ftmConfirmDto) {
        //如果 httpcode 是200 则确认成功,否则一律返回400
        try {
            messagePublishService.confirm(ftmConfirmDto.getMessageId());
        } catch (FtmBizException e) {
            throw new FtmApiException(e);
        }
        return FtmRespKey.SUCCESS;
    }


    @RequestMapping(value = "/app", method = RequestMethod.POST)
    public FtmAppApplyResp createApplication(@RequestBody FtmAppDto ftmAppDto) {
        //创建生产者& 创建消费者
        Preconditions.checkArgument(StringUtils.isNotEmpty(ftmAppDto.getAppName()), "appName 不允许为空");
        return appService.createApp(ftmAppDto);
    }


    @RequestMapping(value = "/topic", method = RequestMethod.POST)
    public FtmTopicApplyResp createTopic(@RequestBody FtmTopicDto ftmTopicDto) {
        // 创建主题 & 订阅关系
        return topicService.createTopic(ftmTopicDto);
    }


    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public FtmVoidResponse createTopicSub(@RequestBody FtmTopicSubDto ftmTopicSubDto) {
        // 创建主题 & 订阅关系
        topicSubService.createTopicSub(ftmTopicSubDto);
        return new FtmVoidResponse();
    }


}
