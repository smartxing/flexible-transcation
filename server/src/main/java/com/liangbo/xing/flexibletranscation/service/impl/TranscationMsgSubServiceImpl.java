package com.liangbo.xing.flexibletranscation.service.impl;

import com.liangbo.xing.flexibletranscation.dao.TranscationMsgDao;
import com.liangbo.xing.flexibletranscation.dao.TranscationSubDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgSubDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgSubDoExample;
import com.liangbo.xing.flexibletranscation.mapper.TranscationMsgSubDoMapper;
import com.liangbo.xing.flexibletranscation.service.TranscationMsgSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午11:12 xingliangbo Exp $
 */
@Service
public class TranscationMsgSubServiceImpl implements TranscationMsgSubService {

    @Autowired
    private TranscationSubDao transcationSubDao;

    @Override
    public Integer updateTranscationMsgSub(TranscationMsgSubDo transcationMsgSubDo) {
        return transcationSubDao.updateByPrimaryKey(transcationMsgSubDo);
    }

    @Override
    public List<TranscationMsgSubDo> selectByStatusNoAsk() {
        return transcationSubDao.selectByStatusNoAsk();
    }
}
