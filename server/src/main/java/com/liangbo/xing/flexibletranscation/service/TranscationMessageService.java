package com.liangbo.xing.flexibletranscation.service;

import com.liangbo.xing.flexibletranscation.domain.FtmAskingDto;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.exception.FtmBizException;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 上午11:27 xingliangbo Exp $
 */
public interface TranscationMessageService {


    public boolean subscribeMsgToAskedStatus(FtmAskingDto ftmAskingDto) throws FtmBizException;

    /**
     * 预存储消息.
     */
    public int saveMessageWaitingConfirm(TranscationMsgDo rpTransactionMessage);


    /**
     * 确认并发送消息.
     */
    public void confirmAndSendMessage(String messageId);


    /**
     * 存储并发送消息.
     */
    public int saveAndSendMessage(TranscationMsgDo rpTransactionMessage);


    /**
     * 直接发送消息.
     */
    public void directSendMessage(TranscationMsgDo rpTransactionMessage);


    /**
     * 重发消息.
     */
    public void reSendMessage(TranscationMsgDo rpTransactionMessage);


    /**
     * 根据messageId重发某条消息.
     */
    public void reSendMessageByMessageId(String messageId);


    /**
     * 将消息标记为死亡消息.
     */
    public void setMessageToAreadlyDead(String messageId);


    /**
     * 根据消息ID获取消息
     */
    public TranscationMsgDo getMessageByMessageId(String messageId);

    /**
     * 根据消息ID删除消息
     */
    public void deleteMessageByMessageId(String messageId);


    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize);

}
