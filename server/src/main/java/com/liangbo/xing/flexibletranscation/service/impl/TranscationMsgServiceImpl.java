package com.liangbo.xing.flexibletranscation.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.dao.TranscationMsgDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDoExample;
import com.liangbo.xing.flexibletranscation.mapper.TranscationMsgDoMapper;
import com.liangbo.xing.flexibletranscation.service.TranscationMsgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午10:51 xingliangbo Exp $
 */
@Service
public class TranscationMsgServiceImpl implements TranscationMsgService {

    @Autowired
    private TranscationMsgDao transcationMsgDao;

    @Override
    public List<TranscationMsgDo> selectByStatusNoCofirm() {
        return transcationMsgDao.selectByStatusNoCofirm();
    }

    @Override
    public Optional<TranscationMsgDo> selectByMessageId(String messageId) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(messageId), "消息id不能为空");
        return transcationMsgDao.selectByMessageId(messageId);
    }

    @Override
    public Integer updateTranscation(TranscationMsgDo transcationMsgDo) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(transcationMsgDo.getMessageid()),"messageId主键不应该为空");
        return transcationMsgDao.uptdateTranscation(transcationMsgDo);
    }


}
