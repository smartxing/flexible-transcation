package com.liangbo.xing.flexibletranscation.checker;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.domain.FtmMqContent;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgSubDo;
import com.liangbo.xing.flexibletranscation.enums.PublishMsgStatusEnum;
import com.liangbo.xing.flexibletranscation.mapper.TranscationMsgSubDoMapper;
import com.liangbo.xing.flexibletranscation.service.MqCommpontService;
import com.liangbo.xing.flexibletranscation.service.TopicService;
import com.liangbo.xing.flexibletranscation.service.TranscationMsgService;
import com.liangbo.xing.flexibletranscation.service.TranscationMsgSubService;
import com.liangbo.xing.flexibletranscation.util.TimeUtils;
import jdk.nashorn.internal.runtime.options.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 消息订阅 检查线程
 *
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午10:53 xingliangbo Exp $
 */
@Component
public class MessageAskedChecker {

    private Logger logger = LoggerFactory.getLogger(MessageAskedChecker.class);
    @Autowired
    private TranscationMsgSubService transcationMsgSubService;

    @Autowired
    private TranscationMsgService transcationMsgService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private MqCommpontService mqCommpontService;

    public void execute() {
        List<TranscationMsgSubDo> transcationMsgSubDoList = transcationMsgSubService.selectByStatusNoAsk();
        String taskId = UUID.randomUUID().toString();
        if (!CollectionUtils.isEmpty(transcationMsgSubDoList)) {
            logger.info("find {} message needs to be dealt with ,taskId {} ", transcationMsgSubDoList.size(), taskId);
            transcationMsgSubDoList.stream().forEach(transcationMsgSubDo -> {
                //重新pushmq
                String messageId = transcationMsgSubDo.getMessageid();
                logger.info("begin to deal messageId {} taskId {}", messageId, taskId);
                try {
                    Optional<TranscationMsgDo> transcationMsgDoOption = transcationMsgService.selectByMessageId(messageId);
                    Optional<TopicDo> topicDoOptional = topicService.selectTopicByTopicId(transcationMsgSubDo.getTopicid());
                    if (topicDoOptional.isPresent() && topicDoOptional.isPresent()) {
                        TopicDo topicDo = topicDoOptional.get();
                        TranscationMsgDo transcationMsgDo = transcationMsgDoOption.get();
                        // 先更新处理状态，重新发送mq 等待重新确认
                        int retry = transcationMsgSubDo.getRetry() + 1;
                        if (retry >= transcationMsgSubDo.getMaxretry()) {
                            transcationMsgSubDo.setStatus(PublishMsgStatusEnum.DIE.getVal());
                        } else {
                            transcationMsgSubDo.setRetry(transcationMsgSubDo.getRetry() + 1);
                            transcationMsgSubDo.setNextretrytime(TimeUtils.addSecond(new Date(), transcationMsgSubDo.getRetryinterval()));
                        }
                        transcationMsgSubDo.setUpdatetime(new Date());
                        transcationMsgSubService.updateTranscationMsgSub(transcationMsgSubDo);

                        FtmMqContent ftmMqContent = getFtmMqContent(topicDo, transcationMsgDo);
                        mqCommpontService.send(topicDo.getTopic(), transcationMsgDo.getRoutingkey(), ftmMqContent);

                    } else {
                        logger.error("事物处理异常.... messageId {}", messageId);
                        transcationMsgSubDo.setStatus(PublishMsgStatusEnum.WAITING_INTERVENTION.getVal());//数据异常 等待人工干预
                        transcationMsgSubService.updateTranscationMsgSub(transcationMsgSubDo);
                    }
                } catch (Exception e) {
                    logger.error("MessageAskedChecker occur error...", e);
                    throw e;
                }
                logger.info("end to deal messageId {} taskId {}", messageId, taskId);
            });
        }
        logger.info("end task ,taskId {} ", taskId);
    }

    private FtmMqContent getFtmMqContent(TopicDo topicDo, TranscationMsgDo transcationMsgDo) {
        FtmMqContent ftmMqContent = new FtmMqContent();
        ftmMqContent.setMessageContext(transcationMsgDo.getMessagebody());
        ftmMqContent.setMessageId(transcationMsgDo.getMessageid());
        ftmMqContent.setTopic(topicDo.getTopic());
        ftmMqContent.setRoutingKey(transcationMsgDo.getRoutingkey());
        ftmMqContent.setPubId(transcationMsgDo.getPubid());
        ftmMqContent.setCreateTime(new Date());
        return ftmMqContent;
    }
}
