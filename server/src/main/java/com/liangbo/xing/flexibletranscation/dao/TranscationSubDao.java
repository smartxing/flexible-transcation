package com.liangbo.xing.flexibletranscation.dao;

import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDoExample;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgSubDo;
import com.liangbo.xing.flexibletranscation.enums.PublishMsgStatusEnum;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午4:16 xingliangbo Exp $
 */
public interface TranscationSubDao {

    public void saveOrUpdateTranscationSubDao(TranscationMsgSubDo transcationMsgSubDo);

    public void updateTranscationSubByMessageAndSubId(String subId, String messageId);

    public List<TranscationMsgSubDo> selectByStatusNoAsk();

    public  Integer updateByPrimaryKey(TranscationMsgSubDo transcationMsgSubDo);
}
