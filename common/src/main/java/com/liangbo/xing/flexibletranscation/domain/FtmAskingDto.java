package com.liangbo.xing.flexibletranscation.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午11:30 xingliangbo Exp $
 */
public class FtmAskingDto {

    @NotEmpty(message =" messageId can not be empty")
    private String messageId;
    @NotEmpty(message =" subId can not be empty")
    private String subId;
    @NotEmpty(message =" appKey can not be empty")
    private String appKey;

    public FtmAskingDto() {
    }

    public FtmAskingDto(String messageId, String subId, String appKey) {
        this.messageId = messageId;
        this.subId = subId;
        this.appKey = appKey;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
