package com.liangbo.xing.transcation.integration;

import com.liangbo.xing.AbstractTranscationTest;
import com.liangbo.xing.flexibletranscation.checker.MessageAskedChecker;
import com.liangbo.xing.flexibletranscation.checker.MessageConfirmChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/25 上午11:44 xingliangbo Exp $
 */
public class ChecherTest extends AbstractTranscationTest {


    @Autowired
    private MessageConfirmChecker messageConfirmChecker;

    @Autowired
    private MessageAskedChecker messageAskedChecker;


    @Test
    @Transactional
    @Rollback(value = false)
    public void testConfirmChecker() throws Exception {
        messageConfirmChecker.execute();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAskChecker() {
        messageAskedChecker.execute();
    }
}
