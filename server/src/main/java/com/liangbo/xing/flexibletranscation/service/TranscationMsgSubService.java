package com.liangbo.xing.flexibletranscation.service;

import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgSubDo;

import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午10:49 xingliangbo Exp $
 */
public interface TranscationMsgSubService {


    public Integer updateTranscationMsgSub(TranscationMsgSubDo transcationMsgSubDo);



    public List<TranscationMsgSubDo> selectByStatusNoAsk();
}
