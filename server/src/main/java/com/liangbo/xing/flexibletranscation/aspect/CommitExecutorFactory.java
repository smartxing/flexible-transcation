package com.liangbo.xing.flexibletranscation.aspect;


/**
 * 
 * 
 * @author xingliangbo
 * @version $Id: CommitExecutorFactory.java, v 0.1 2016年8月18日 下午2:31:55 xingliangbo Exp $
 */
public class CommitExecutorFactory {

    /**
     * @return
     */
    public static CommitExecutor getAfterCommitDefaultImpl() {
        return new AfterCommitExecutorDefaultImpl();
    }

    /**
     * @return
     */
    public static CommitExecutor getBeforeCommitDefaultImpl() {
        return new BeforeCommitExecutorDefaultImpl();
    }



}