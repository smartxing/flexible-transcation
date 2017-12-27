package com.liangbo.xing.flexibletranscation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.concurrent.TimeUnit;

@Component
public class DistributeLockImpl implements DistributeLock {

    private static final Logger LOG = LoggerFactory.getLogger(DistributeLockImpl.class);

    private static final long DEFAULT_LOCK_EXPIRE_TIME = 10000;

    private static final long DEFAULT_SLEEP_TIME = 100;

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    public DistributeLockImpl() {

    }

    @Override
    public boolean tryLock(String lock, long requestTimeout) throws Exception {
        return tryLock(lock, DEFAULT_LOCK_EXPIRE_TIME, requestTimeout);
    }

    @Override
    public boolean tryLock(String lock, long lockExpireTime, long requestTimeout) throws Exception {
        if (lockExpireTime <= 0 || requestTimeout <= 0) {
            return false;
        }
        while (requestTimeout > 0) {
            String expire = String.valueOf(System.currentTimeMillis() + lockExpireTime + 1);
            Long result = jedisCluster.setnx(lock, expire);
            if (result == 1L) {
                return true;
            }
            String currentValue = jedisCluster.get(lock);
            if (currentValue == null) {
                continue;
            } else if (Long.parseLong(currentValue) < System.currentTimeMillis()) {
                String oldValue = jedisCluster.getSet(lock, expire);
                if (oldValue == null || oldValue.equals(currentValue)) {
                    return true;
                }
            }
            long sleepTime;
            if (requestTimeout > DEFAULT_SLEEP_TIME) {
                sleepTime = DEFAULT_SLEEP_TIME;
                requestTimeout -= DEFAULT_SLEEP_TIME;
            } else {
                sleepTime = requestTimeout;
                requestTimeout = 0;
            }
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        }
        return Boolean.FALSE;
    }

    @Override
    public void unLock(String lock) {
        String value = jedisCluster.get(lock);
        if (value != null && Long.parseLong(value) > System.currentTimeMillis()) {
            jedisCluster.del(lock);
        }
    }

}
