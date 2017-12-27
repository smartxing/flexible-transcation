package com.liangbo.xing.flexibletranscation.domain.msg;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午11:20 xingliangbo Exp $
 */
public class FtmResponse {

    private String messageId;
    private String version;
    private String messageStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date nextRetryTime;
    private Integer maxRetry;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getNextRetryTime() {
        return nextRetryTime;
    }

    public void setNextRetryTime(Date nextRetryTime) {
        this.nextRetryTime = nextRetryTime;
    }

    public Integer getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(Integer maxRetry) {
        this.maxRetry = maxRetry;
    }
}
