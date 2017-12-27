package com.liangbo.xing.flexibletranscation.schedule;

import com.liangbo.xing.flexibletranscation.checker.MessageAskedChecker;
import com.liangbo.xing.flexibletranscation.checker.MessageConfirmChecker;
import com.liangbo.xing.flexibletranscation.service.DistributeLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午11:44 xingliangbo Exp $
 */
@EnableScheduling
@Component
public class ScheduledTasks {

    private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private MessageAskedChecker messageAskedChecker;

    @Autowired
    private MessageConfirmChecker messageConfirmChecker;

    @Autowired
    private DistributeLock distributeLock;


    @Scheduled(fixedRate = 10000)
    public void startMessageAskedChecker() {
        boolean acquired = false;
        String lock = "redis:lock:message:ask:checker";
        try {
            acquired = distributeLock.tryLock(lock, 3000);
            if (acquired) {
                logger.info("start ask checker ");
                messageAskedChecker.execute();
            } else {
                logger.info("未获取到执行权限...");
            }
        } catch (Exception e) {
            logger.error("ask checker error ", e);
        } finally {
            if (acquired) {
                distributeLock.unLock(lock);
            }
        }
    }


    @Scheduled(fixedRate = 10000)
    public void startMessageConfirmChecker() {

        boolean acquired = false;
        String lock = "redis:lock:message:confirm:checker";
        try {
            acquired = distributeLock.tryLock(lock, 3000);
            if (acquired) {
                logger.info("start confirm checker... ");
                messageConfirmChecker.execute();
            } else {
                logger.info("未获取到执行权限...");
            }
        } catch (Exception e) {
            logger.error("ask checker error ", e);
        } finally {
            if (acquired) {
                distributeLock.unLock(lock);
            }
        }
    }
}
