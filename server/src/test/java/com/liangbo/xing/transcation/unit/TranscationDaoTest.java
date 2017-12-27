package com.liangbo.xing.transcation.unit;

import com.liangbo.xing.AbstractTranscationTest;
import com.liangbo.xing.flexibletranscation.dao.TranscationMsgDao;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckDto;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/25 下午10:56 xingliangbo Exp $
 */
public class TranscationDaoTest extends AbstractTranscationTest {
    @Autowired
    private TranscationMsgDao transcationMsgDao;

    @Test
    @Rollback(value = false)
    public void test(){
        TranscationMsgDo transcationMsgDo = transcationMsgDao.selectByMessageId("7db3df5a-2419-4bfe-899c-e1f19ac2358a").get();
        transcationMsgDo.setStatus(1);
        transcationMsgDao.uptdateTranscation(transcationMsgDo);

    }
}
