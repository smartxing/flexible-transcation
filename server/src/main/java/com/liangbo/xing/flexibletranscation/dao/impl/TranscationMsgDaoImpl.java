package com.liangbo.xing.flexibletranscation.dao.impl;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.dao.TranscationMsgDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDoExample;
import com.liangbo.xing.flexibletranscation.enums.PublishMsgStatusEnum;
import com.liangbo.xing.flexibletranscation.mapper.TranscationMsgDoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午11:10 xingliangbo Exp $
 */
@Component
public class TranscationMsgDaoImpl implements TranscationMsgDao {

    @Autowired
    private TranscationMsgDoMapper transcationMsgDoMapper;

    @Override
    public Integer saveTranscationMsg(TranscationMsgDo transcationMsgDo) {
        return transcationMsgDoMapper.insert(transcationMsgDo);
    }

    @Override
    public List<TranscationMsgDo> selectByStatusNoCofirm() {
        TranscationMsgDoExample transcationMsgDoExample = new TranscationMsgDoExample();
        //如果下次尝试时间大于当前时间的则需要重新发起确认
        transcationMsgDoExample.createCriteria()
                //如果是WAITING_CONFIM 或者是UNKOWN的 情况下需要重新确认
                .andStatusIn(Arrays.asList(PublishMsgStatusEnum.WAITING_CONFIRM.getVal(), PublishMsgStatusEnum.UNKOWN.getVal()))
                //取时间已经超时
                .andNextretrytimeLessThan(new Date())
                //重试次数小于最大次数
                .andRetryLessThanMaxRetry();
        return transcationMsgDoMapper.selectByExample(transcationMsgDoExample);
    }

    @Override
    public Optional<TranscationMsgDo> selectByMessageId(String messageId) {
        TranscationMsgDoExample transcationMsgDoExample = new TranscationMsgDoExample();
        transcationMsgDoExample.createCriteria().andMessageidEqualTo(messageId);
        List<TranscationMsgDo> transcationMsgDos = transcationMsgDoMapper.selectByExample(transcationMsgDoExample);
        Preconditions.checkNotNull(transcationMsgDos, "messageid 异常,查询不到message");
        Preconditions.checkArgument(transcationMsgDos.size() == 1, "messageid 异常，查询不到message");
        return Optional.fromNullable(transcationMsgDos.get(0));

    }

    @Override
    public Integer uptdateTranscation(TranscationMsgDo transcationMsgDo) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(transcationMsgDo.getMessageid()),"messageId主键不应该为空");
        return transcationMsgDoMapper.updateByPrimaryKey(transcationMsgDo);
    }


}
