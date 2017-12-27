package com.liangbo.xing.flexibletranscation.service;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;

import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午10:49 xingliangbo Exp $
 */
public interface TranscationMsgService {

    public List<TranscationMsgDo> selectByStatusNoCofirm();

    public Optional<TranscationMsgDo> selectByMessageId(String messageId);

    public Integer updateTranscation(TranscationMsgDo transcationMsgDo);
}
