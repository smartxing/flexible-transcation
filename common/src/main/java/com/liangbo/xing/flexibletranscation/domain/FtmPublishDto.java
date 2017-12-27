package com.liangbo.xing.flexibletranscation.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/24 下午6:56 xingliangbo Exp $
 */
public class FtmPublishDto {

    @NotEmpty(message =" pubId can not be empty")
    private String pubId;
    @NotEmpty(message =" pubkey can not be empty")
    private String pubkey;
    @NotEmpty(message =" topicId can not be empty")
    private String topicId;
    @NotEmpty(message =" routeKey can not be empty")
    private String routeKey;
    @NotEmpty(message =" messageBody can not be empty")
    private String messageBody;

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
