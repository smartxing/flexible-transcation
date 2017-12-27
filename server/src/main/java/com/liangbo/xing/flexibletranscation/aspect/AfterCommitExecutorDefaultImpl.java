package com.liangbo.xing.flexibletranscation.aspect;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提交之后处理
 * 
 * @author xingliangbo
 * @version $Id: AfterCommitExecutorDefaultImpl.java, v 0.1 2016年8月18日 下午2:31:00 xingliangbo Exp $
 */
public class AfterCommitExecutorDefaultImpl extends CommitExecutorBaseImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AfterCommitExecutorDefaultImpl.class);

    /**
     * 只有事务成功时才会调用。 如果本函数 执行失败 不会回滚了
     */
    @Override
    public void afterCommit() {

        List<Runnable> threadRunnables = RUNNABLES.get();

        LOGGER.debug("Transaction successfully committed, executing {} runnables", threadRunnables.size());

        for (int i = 0; i < threadRunnables.size(); i++) {

            Runnable runnable = threadRunnables.get(i);
            LOGGER.debug("Executing runnable {}", runnable);
            try {
                runnable.run();
            } catch (RuntimeException e) {
                LOGGER.error("Failed to execute runnable " + runnable, e);
            }
        }
    }

    /**
     * 不管回滚还是提交，都会被执行
     *
     * @param status
     */
    @Override
    public void afterCompletion(int status) {

        LOGGER.debug("Transaction completed with status {}", status == STATUS_COMMITTED ? "COMMITTED" : "ROLLED_BACK");
        RUNNABLES.remove();
    }

}
