package com.liangbo.xing.flexibletranscation.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "ftm.redis")
public class RedisProperties {
    private List<String> addressList;

    private int          minIdle;

    private int          maxIdle;

    private int          maxTotal;

    private long         maxWaitMillis;

    /**
     * Getter method for property <tt>addressList</tt>.
     *
     * @return property value of addressList
     */
    public List<String> getAddressList() {
        return addressList;
    }

    /**
     * Setter method for property <tt>addressList</tt>.
     *
     * @param addressList value to be assigned to property addressList
     */
    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }

    /**
     * Getter method for property <tt>minIdle</tt>.
     *
     * @return property value of minIdle
     */
    public int getMinIdle() {
        return minIdle;
    }

    /**
     * Setter method for property <tt>minIdle</tt>.
     *
     * @param minIdle value to be assigned to property minIdle
     */
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    /**
     * Getter method for property <tt>maxIdle</tt>.
     *
     * @return property value of maxIdle
     */
    public int getMaxIdle() {
        return maxIdle;
    }

    /**
     * Setter method for property <tt>maxIdle</tt>.
     *
     * @param maxIdle value to be assigned to property maxIdle
     */
    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    /**
     * Getter method for property <tt>maxTotal</tt>.
     *
     * @return property value of maxTotal
     */
    public int getMaxTotal() {
        return maxTotal;
    }

    /**
     * Setter method for property <tt>maxTotal</tt>.
     *
     * @param maxTotal value to be assigned to property maxTotal
     */
    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    /**
     * Getter method for property <tt>maxWaitMillis</tt>.
     *
     * @return property value of maxWaitMillis
     */
    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    /**
     * Setter method for property <tt>maxWaitMillis</tt>.
     *
     * @param maxWaitMillis value to be assigned to property maxWaitMillis
     */
    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }
}
