package com.liangbo.xing.flexibletranscation.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisTaskConfigurations {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTaskConfigurations.class);


    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        for (String hostAndPort : redisProperties.getAddressList()) {
            String[] hp = hostAndPort.split(":");
            jedisClusterNodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
        }
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(redisProperties.getMaxIdle());
        config.setMaxTotal(redisProperties.getMaxTotal());
        config.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        config.setMinIdle(redisProperties.getMinIdle());
        config.setTestWhileIdle(true);
        JedisCluster jc = new JedisCluster(jedisClusterNodes, Protocol.DEFAULT_TIMEOUT, config);
        return jc;
    }
}
