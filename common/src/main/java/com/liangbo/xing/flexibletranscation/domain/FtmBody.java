package com.liangbo.xing.flexibletranscation.domain;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 下午7:34 xingliangbo Exp $
 */
public class FtmBody {

    private int pubId;
    private String pubkey;
    private Integer topicId;
    private String routeKey;
    private String messageBody;

    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
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
