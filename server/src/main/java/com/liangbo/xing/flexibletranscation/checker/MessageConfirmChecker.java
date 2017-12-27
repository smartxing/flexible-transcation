package com.liangbo.xing.flexibletranscation.checker;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckDto;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.enums.SubscribeMsgStatusEnum;
import com.liangbo.xing.flexibletranscation.enums.TranscationStatus;
import com.liangbo.xing.flexibletranscation.service.AppService;
import com.liangbo.xing.flexibletranscation.service.MessagePublishService;
import com.liangbo.xing.flexibletranscation.service.TopicService;
import com.liangbo.xing.flexibletranscation.service.TranscationMsgService;
import com.liangbo.xing.flexibletranscation.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 扫面msg表，拉出确认超时的消息，回调发布者 检查状态
 *
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午10:21 xingliangbo Exp $
 */
@Component
public class MessageConfirmChecker {

    private Logger LOGGER = LoggerFactory.getLogger(MessageAskedChecker.class);
    @Autowired
    private TranscationMsgService transcationMsgService;

    @Autowired
    private AppService appService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private MessagePublishService messagePublishService;


    public void execute() throws Exception {
        //需要分布式锁
        List<TranscationMsgDo> transcationMsgDos = transcationMsgService.selectByStatusNoCofirm();
        String taskId = UUID.randomUUID().toString();
        if (!CollectionUtils.isEmpty(transcationMsgDos)) {
            LOGGER.info("find {} message needs to be dealt with ,taskId {} ", transcationMsgDos.size(), taskId);
            transcationMsgDos.stream().forEach(transcationMsgDo -> {
                LOGGER.info("begin to deal messageId {} taskId {}", transcationMsgDo.getMessageid(), taskId);
                String pubId = transcationMsgDo.getPubid();
                try {
                    Optional<AppDo> appDoOptional = appService.selectAppById(pubId);
                    Optional<TopicDo> topicDoOptional = topicService.selectTopicByTopicId(transcationMsgDo.getTopicid());
                    if (appDoOptional.isPresent() && topicDoOptional.isPresent()) {
                        AppDo appDo = appDoOptional.get();
                        TopicDo topicDo = topicDoOptional.get();
                        FtmProducerCheckDto ftmProducerCheckDto = buildFtmProducerCheckDto(topicDo, transcationMsgDo);
                        int retry = transcationMsgDo.getRetry() + 1;
                        if (retry >= transcationMsgDo.getMaxretry()) {
                            transcationMsgDo.setStatus(SubscribeMsgStatusEnum.DIE.getVal());//超过最大次数 设置为消息死亡
                        } else {
                            transcationMsgDo.setRetry(retry);
                            transcationMsgDo.setNextretrytime(TimeUtils.addSecond(new Date(), transcationMsgDo.getRetryinterval()));
                        }
                        transcationMsgDo.setUpdatetime(new Date());
                        transcationMsgService.updateTranscation(transcationMsgDo);
                        TranscationStatus transcationStatus = callProducerToComfirm(appDo, ftmProducerCheckDto);
                        switch (transcationStatus) {
                            case OK:
                                messagePublishService.confirm(transcationMsgDo.getMessageid());//重新确认消息
                                break;
                            case FAIL:
                            case UNKNOW:
                                break;
                            default:
                                break;
                        }

                    } else {
                        transcationMsgDo.setStatus(SubscribeMsgStatusEnum.WAITING_INTERVENTION.getVal());//数据异常 等待人工干预
                        transcationMsgService.updateTranscation(transcationMsgDo);
                    }
                } catch (Exception e) {
                    LOGGER.error("MessageConfirmChecker occur error...", e);
                    throw e;
                }
                LOGGER.info("end to deal messageId {} taskId {}", transcationMsgDo.getMessageid(), taskId);

            });
            LOGGER.info("end task ,taskId {} ", taskId);
        }
    }

    /**
     * 这块逻辑不应该是这么实现的，因为这边没有使用服务发现所以 临时写着。如果真正用于线上环境 那肯定需要修改的
     */
    private TranscationStatus callProducerToComfirm(AppDo appDo, FtmProducerCheckDto ftmProducerCheckDto) {
        return TranscationStatus.OK;
        //        try {
//            ProducerRecheckApi producerRecheckApi = FtmClient.create(appDo.getCallbackurl(), ProducerRecheckApi.class);
//            FtmProducerCheckResp ftmProducerCheckResp = producerRecheckApi.recheck(ftmProducerCheckDto);
//            return TranscationStatus.valueOf(ftmProducerCheckResp.getTranscationStatus());
//        } catch (Exception e) {
//            LOGGER.error("check producer error", e);
//            return TranscationStatus.UNKNOW;
//        }
    }

    public FtmProducerCheckDto buildFtmProducerCheckDto(TopicDo topicDo, TranscationMsgDo transcationMsgDo) {
        FtmProducerCheckDto ftmProducerCheckDto = new FtmProducerCheckDto();
        ftmProducerCheckDto.setTopic(topicDo.getTopic());
        ftmProducerCheckDto.setMessageBody(transcationMsgDo.getMessagebody());
        ftmProducerCheckDto.setRountingKey(transcationMsgDo.getRoutingkey());
        return ftmProducerCheckDto;
    }


}

