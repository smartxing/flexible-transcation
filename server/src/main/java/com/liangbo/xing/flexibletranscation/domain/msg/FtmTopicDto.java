package com.liangbo.xing.flexibletranscation.domain.msg;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/23 下午10:48 xingliangbo Exp $
 */
public class FtmTopicDto {

    @NotEmpty(message = "topic cane be empty")
    private String topic;//topicId uuid一个即可
    private String topicDesc;
    @NotEmpty(message = "maxRetryTime cane be empty")
    private Integer maxRetryTime;//最大重试次数
    @NotEmpty(message = "retryInterval cane be empty")
    private Integer retryInterval;//重试间隔

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getMaxRetryTime() {
        return maxRetryTime;
    }

    public void setMaxRetryTime(Integer maxRetryTime) {
        this.maxRetryTime = maxRetryTime;
    }

    public Integer getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }


    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }
}
