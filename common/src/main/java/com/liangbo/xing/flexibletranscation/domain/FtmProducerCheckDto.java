package com.liangbo.xing.flexibletranscation.domain;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午9:30 xingliangbo Exp $
 */
public class FtmProducerCheckDto {

    private String topic;
    private String messageId;
    private String rountingKey;
    private String messageBody;

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

    public String getRountingKey() {
        return rountingKey;
    }

    public void setRountingKey(String rountingKey) {
        this.rountingKey = rountingKey;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
