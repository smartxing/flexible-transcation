package com.liangbo.xing.flexibletranscation.dao;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;

import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午11:08 xingliangbo Exp $
 */
public interface TranscationMsgDao {

    public Integer saveTranscationMsg(TranscationMsgDo transcationMsgDo);

    public List<TranscationMsgDo> selectByStatusNoCofirm();

    public Optional<TranscationMsgDo> selectByMessageId(String messageId);

    public Integer uptdateTranscation(TranscationMsgDo transcationMsgDo);

}
