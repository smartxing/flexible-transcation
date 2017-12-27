package com.liangbo.xing.flexibletranscation.domain;

import java.util.Date;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午8:51 xingliangbo Exp $
 */
public class FtmMqContent {


    private String pubId;
    private String topic;
    private String messageId;
    private String routingKey;
    private String messageContext;
    private Date createTime;

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    @Override
    public String toString() {
        return "FtmMqContent{" +
                "pubId='" + pubId + '\'' +
                ", topic='" + topic + '\'' +
                ", messageId='" + messageId + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", messageContext='" + messageContext + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
