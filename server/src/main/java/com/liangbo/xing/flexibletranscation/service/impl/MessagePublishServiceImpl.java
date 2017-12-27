package com.liangbo.xing.flexibletranscation.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.aspect.CommitExecutorFactory;
import com.liangbo.xing.flexibletranscation.config.GlobalConstants;
import com.liangbo.xing.flexibletranscation.dao.TopicDao;
import com.liangbo.xing.flexibletranscation.dao.TopicSubDao;
import com.liangbo.xing.flexibletranscation.dao.TranscationMsgDao;
import com.liangbo.xing.flexibletranscation.dao.TranscationSubDao;
import com.liangbo.xing.flexibletranscation.domain.FtmCommonResponse;
import com.liangbo.xing.flexibletranscation.domain.FtmMqContent;
import com.liangbo.xing.flexibletranscation.domain.FtmPublishDto;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicSubDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgSubDo;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmBody;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmResponse;
import com.liangbo.xing.flexibletranscation.enums.PublishMsgStatusEnum;
import com.liangbo.xing.flexibletranscation.enums.SubscribeMsgStatusEnum;
import com.liangbo.xing.flexibletranscation.exception.FtmBizException;
import com.liangbo.xing.flexibletranscation.exception.FtmIllegalAccessError;
import com.liangbo.xing.flexibletranscation.service.MessagePublishService;
import com.liangbo.xing.flexibletranscation.service.MqCommpontService;
import com.liangbo.xing.flexibletranscation.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 下午7:37 xingliangbo Exp $
 */
@Service
public class MessagePublishServiceImpl implements MessagePublishService {

    @Autowired
    private TranscationMsgDao transcationMsgDao;

    @Autowired
    private TopicSubDao topicSubDao;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TranscationSubDao transcationSubDao;

    @Autowired
    private MqCommpontService mqCommpontService;


    @Override
    public FtmResponse publish(FtmPublishDto ftmPublishDto) throws FtmBizException {
        FtmBody ftmBody = builderFtmBody(ftmPublishDto);
        TranscationMsgDo transcationMsgDo = builderTMsgDo(ftmBody);
        transcationMsgDao.saveTranscationMsg(transcationMsgDo);
        FtmResponse ftmResponse = new FtmResponse();
        ftmResponse.setMessageId(transcationMsgDo.getMessageid());
        ftmResponse.setVersion(transcationMsgDo.getVersion());
        ftmResponse.setNextRetryTime(transcationMsgDo.getNextretrytime());
        ftmResponse.setMessageStatus(transcationMsgDo.getStatus() + "");
        ftmResponse.setMaxRetry(transcationMsgDo.getMaxretry());
        return ftmResponse;

    }

    private FtmBody builderFtmBody(FtmPublishDto ftmPublishDto) {
        FtmBody ftmBody = new FtmBody();
        String topicId = ftmPublishDto.getTopicId();
        Optional<TopicDo> topicDoOptional = topicDao.selectTopicByTopicId(topicId);
        Preconditions.checkArgument(topicDoOptional.isPresent(), "非法topicId");
        TopicDo topicDo = topicDoOptional.get();
        ftmBody.setMaxRetry(topicDo.getMaxretrytime());
        ftmBody.setRetryInterval(topicDo.getRetryinterval());
        ftmBody.setMessageBody(ftmPublishDto.getMessageBody());
        ftmBody.setPubId(ftmPublishDto.getPubId());
        ftmBody.setPubkey(ftmPublishDto.getPubkey());
        ftmBody.setRouteKey(ftmPublishDto.getRouteKey());
        ftmBody.setTopicId(ftmPublishDto.getTopicId());
        return ftmBody;
    }

    @Transactional
    @Override
    public FtmCommonResponse confirm(String messageId) throws FtmBizException {

        //查询改消息主题有多少订阅者
        Preconditions.checkArgument(StringUtils.isNotEmpty(messageId), "messageid 不能为空");
        Optional<TranscationMsgDo> transcationMsgDoOptional = transcationMsgDao.selectByMessageId(messageId);
        if (!transcationMsgDoOptional.isPresent()) {
            throw new FtmBizException(FtmIllegalAccessError.ILLEGAL_REQUEST, "未发现对应事物消息 messageId:" + messageId);
        }
        TranscationMsgDo transcationMsgDo = transcationMsgDoOptional.get();
        //查询订阅者
        String topicId = transcationMsgDo.getTopicid();
        Optional<TopicDo> topicDoOptional = topicDao.selectTopicByTopicId(topicId);
        if (!topicDoOptional.isPresent()) {
            throw new FtmBizException(FtmIllegalAccessError.ILLEGAL_REQUEST, "未发现对应主题 messageId:" + messageId);
        }
        TopicDo topicDo = topicDoOptional.get();

        List<TopicSubDo> topicSubDoList = topicSubDao.selectByTopicIdAndRountingKey(topicId, transcationMsgDo.getRoutingkey());
        if (CollectionUtils.isEmpty(topicSubDoList)) {
            throw new FtmBizException(FtmIllegalAccessError.ILLEGAL_REQUEST, "未发现对应主题订阅 messageId:" + messageId);
        }
        //查入订阅者表,保存订阅消息
        topicSubDoList.stream().forEach(topicSubDo -> {
            TranscationMsgSubDo transcationMsgSubDo = builderTMsgSubDo(topicSubDo, transcationMsgDo);
            transcationSubDao.saveOrUpdateTranscationSubDao(transcationMsgSubDo);
        });
        //跟新发布者状态
        transcationMsgDo.setStatus(PublishMsgStatusEnum.SENDING.getVal());
        transcationMsgDao.uptdateTranscation(transcationMsgDo);
        //事物提交成功后 真正发送 mq消息
        CommitExecutorFactory.getAfterCommitDefaultImpl().execute(() -> {

            FtmMqContent ftmMqContent = getFtmMqContent(topicDo, transcationMsgDo);
            mqCommpontService.send(topicDo.getTopic(), transcationMsgDo.getRoutingkey(), ftmMqContent);
        });
        return new FtmCommonResponse("200", "SUCCESS");

    }

    private FtmMqContent getFtmMqContent(TopicDo topicDo, TranscationMsgDo transcationMsgDo) {
        FtmMqContent ftmMqContent = new FtmMqContent();
        ftmMqContent.setMessageContext(transcationMsgDo.getMessagebody());
        ftmMqContent.setPubId(transcationMsgDo.getPubid());
        ftmMqContent.setMessageId(transcationMsgDo.getMessageid());
        ftmMqContent.setTopic(topicDo.getTopic());
        ftmMqContent.setRoutingKey(transcationMsgDo.getRoutingkey());
        ftmMqContent.setCreateTime(new Date());
        return ftmMqContent;
    }


    public TranscationMsgSubDo builderTMsgSubDo(TopicSubDo topicSubDo, TranscationMsgDo transcationMsgDo) {
        TranscationMsgSubDo transcationMsgSubDo = new TranscationMsgSubDo();
        transcationMsgSubDo.setMessageid(transcationMsgDo.getMessageid());
        transcationMsgSubDo.setSubid(topicSubDo.getSubid());
        transcationMsgSubDo.setTopicid(topicSubDo.getTopicid());
        transcationMsgSubDo.setRetry(transcationMsgDo.getRetry());
        transcationMsgSubDo.setMaxretry(transcationMsgDo.getMaxretry());
        transcationMsgSubDo.setRetryinterval(transcationMsgDo.getRetryinterval());
        transcationMsgSubDo.setVersion(GlobalConstants.DEFAULT_VERSION);
        transcationMsgSubDo.setCreatetime(new Date());
        transcationMsgSubDo.setUpdatetime(new Date());
        transcationMsgSubDo.setNextretrytime(TimeUtils.addSecond(new Date(), transcationMsgDo.getRetryinterval()));
        transcationMsgSubDo.setStatus(SubscribeMsgStatusEnum.WAITINT_ASK.getVal());
        return transcationMsgSubDo;
    }


    public TranscationMsgDo builderTMsgDo(FtmBody ftmBody) {
        TranscationMsgDo transcationMsgDo = new TranscationMsgDo();
        transcationMsgDo.setMessageid(UUID.randomUUID().toString());
        transcationMsgDo.setMessagebody(ftmBody.getMessageBody());
        transcationMsgDo.setRoutingkey(ftmBody.getRouteKey());
        transcationMsgDo.setVersion(GlobalConstants.DEFAULT_VERSION);
        transcationMsgDo.setStatus(PublishMsgStatusEnum.WAITING_CONFIRM.getVal());
        transcationMsgDo.setPubid(ftmBody.getPubId());
        transcationMsgDo.setTopicid(ftmBody.getTopicId());
        transcationMsgDo.setRetry(GlobalConstants.DEFAULT_RETRY);
        transcationMsgDo.setMaxretry(ftmBody.getMaxRetry());
        transcationMsgDo.setRetryinterval(ftmBody.getRetryInterval());
        transcationMsgDo.setNextretrytime(TimeUtils.addSecond(new Date(), transcationMsgDo.getRetryinterval()));
        transcationMsgDo.setMessagebody(ftmBody.getMessageBody());
        transcationMsgDo.setCreatetime(new Date());
        transcationMsgDo.setUpdatetime(new Date());
        return transcationMsgDo;
    }
}
