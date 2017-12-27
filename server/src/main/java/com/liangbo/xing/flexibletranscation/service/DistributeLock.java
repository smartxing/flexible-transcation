package com.liangbo.xing.flexibletranscation.service;

public interface DistributeLock {

    boolean tryLock(String lock, long requestTimeout) throws Exception;

    boolean tryLock(String lock, long lockExpireTime, long requestTimeout) throws Exception;

    void unLock(String lock);

}
