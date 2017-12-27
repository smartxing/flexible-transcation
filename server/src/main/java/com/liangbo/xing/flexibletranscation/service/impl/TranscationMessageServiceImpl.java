package com.liangbo.xing.flexibletranscation.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.dao.AppDao;
import com.liangbo.xing.flexibletranscation.dao.TranscationSubDao;
import com.liangbo.xing.flexibletranscation.domain.FtmAskingDto;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.exception.FtmBizException;
import com.liangbo.xing.flexibletranscation.exception.FtmIllegalAccessError;
import com.liangbo.xing.flexibletranscation.mapper.TranscationMsgSubDoMapper;
import com.liangbo.xing.flexibletranscation.service.TranscationMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午3:45 xingliangbo Exp $
 */
@Service
public class TranscationMessageServiceImpl implements TranscationMessageService {


    @Autowired
    private AppDao appDao;
    @Autowired
    private TranscationSubDao transcationSubDao;

    @Override
    public boolean subscribeMsgToAskedStatus(FtmAskingDto ftmAskingDto) throws FtmBizException {
        Optional<AppDo> appDo = appDao.selectAppByAppIdAndAppKey(ftmAskingDto.getSubId(), ftmAskingDto.getAppKey());
        if (!appDo.isPresent()) {
            throw new FtmBizException(FtmIllegalAccessError.ILLEGAL_REQUEST);
        }
        transcationSubDao.updateTranscationSubByMessageAndSubId(ftmAskingDto.getSubId(), ftmAskingDto.getMessageId());
        return true;
    }

    @Override
    public int saveMessageWaitingConfirm(TranscationMsgDo rpTransactionMessage) {
        return 0;
    }

    @Override
    public void confirmAndSendMessage(String messageId) {

    }

    @Override
    public int saveAndSendMessage(TranscationMsgDo rpTransactionMessage) {
        return 0;
    }

    @Override
    public void directSendMessage(TranscationMsgDo rpTransactionMessage) {

    }

    @Override
    public void reSendMessage(TranscationMsgDo rpTransactionMessage) {

    }

    @Override
    public void reSendMessageByMessageId(String messageId) {

    }

    @Override
    public void setMessageToAreadlyDead(String messageId) {

    }

    @Override
    public TranscationMsgDo getMessageByMessageId(String messageId) {
        return null;
    }

    @Override
    public void deleteMessageByMessageId(String messageId) {

    }

    @Override
    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) {

    }
}
