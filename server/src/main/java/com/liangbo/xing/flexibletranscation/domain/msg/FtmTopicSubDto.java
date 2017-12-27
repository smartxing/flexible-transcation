package com.liangbo.xing.flexibletranscation.domain.msg;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/24 下午7:13 xingliangbo Exp $
 */
public class FtmTopicSubDto {

    @NotEmpty(message = "subId can not be empty")
    private String subId;
    @NotEmpty(message = "rountingKey can not be empty")
    private String rountingKey;
    @NotEmpty(message = "topicId can not be empty")
    private String topicId;

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getRountingKey() {
        return rountingKey;
    }

    public void setRountingKey(String rountingKey) {
        this.rountingKey = rountingKey;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
