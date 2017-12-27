package com.liangbo.xing.flexibletranscation.dao.impl;

import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.checker.MessageAskedChecker;
import com.liangbo.xing.flexibletranscation.dao.TranscationSubDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDoExample;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgSubDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgSubDoExample;
import com.liangbo.xing.flexibletranscation.enums.PublishMsgStatusEnum;
import com.liangbo.xing.flexibletranscation.enums.SubscribeMsgStatusEnum;
import com.liangbo.xing.flexibletranscation.mapper.TranscationMsgSubDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午4:17 xingliangbo Exp $
 */
@Component
public class TranscationSubDaoImpl implements TranscationSubDao {

    @Autowired
    private TranscationMsgSubDoMapper transcationMsgSubDoMapper;

    @Override
    public void saveOrUpdateTranscationSubDao(TranscationMsgSubDo transcationMsgSubDo) {
        TranscationMsgSubDoExample transcationMsgSubDoExample = new TranscationMsgSubDoExample();
        transcationMsgSubDoExample.createCriteria().andMessageidEqualTo(transcationMsgSubDo.getMessageid());
        List<TranscationMsgSubDo> msgSubDoList = transcationMsgSubDoMapper.selectByExample(transcationMsgSubDoExample);
        if (msgSubDoList != null && msgSubDoList.size() > 0) {
            TranscationMsgSubDo record = msgSubDoList.get(0);
            transcationMsgSubDo.setId(record.getId());
            transcationMsgSubDoMapper.updateByPrimaryKey(record);
        } else {
            transcationMsgSubDoMapper.insert(transcationMsgSubDo);
        }
    }

    @Override
    public void updateTranscationSubByMessageAndSubId(String subId, String messageId) {
        //此处不需要事物
        TranscationMsgSubDoExample transcationMsgSubDoExample = new TranscationMsgSubDoExample();
        transcationMsgSubDoExample.createCriteria().andSubidEqualTo(subId).andMessageidEqualTo(messageId);
        List<TranscationMsgSubDo> transcationMsgSubDoList = transcationMsgSubDoMapper.selectByExample(transcationMsgSubDoExample);
        //必须有一条数据才满足
        Preconditions.checkNotNull(transcationMsgSubDoList, "message 不存在...");
        Preconditions.checkArgument(transcationMsgSubDoList.size() == 1, "message 不存在...");
        TranscationMsgSubDo transcationMsgSubDo = transcationMsgSubDoList.get(0);
        //跟新状态为已确认状态
        transcationMsgSubDo.setStatus(SubscribeMsgStatusEnum.ASKED.getVal());
        transcationMsgSubDoMapper.updateByPrimaryKey(transcationMsgSubDo);
    }

    @Override
    public List<TranscationMsgSubDo> selectByStatusNoAsk() {
        TranscationMsgSubDoExample transcationMsgSubDoExample = new TranscationMsgSubDoExample();
        //如果下次尝试时间大于当前时间的则需要重新发起确认
        transcationMsgSubDoExample.createCriteria()
                //如果是WAITING_CONFIM 或者是UNKOWN的 情况下需要重新确认
                .andStatusIn(Arrays.asList(SubscribeMsgStatusEnum.UNKOWN.getVal(), SubscribeMsgStatusEnum.WAITINT_ASK.getVal()))
                //取认时间已经超时
                .andNextretrytimeLessThan(new Date())
                //重试次数小于最大次数
                .andRetryLessThanMaxRetry();
        return transcationMsgSubDoMapper.selectByExample(transcationMsgSubDoExample);
    }

    @Override
    public Integer updateByPrimaryKey(TranscationMsgSubDo transcationMsgSubDo) {
        return transcationMsgSubDoMapper.updateByPrimaryKey(transcationMsgSubDo);
    }
}
