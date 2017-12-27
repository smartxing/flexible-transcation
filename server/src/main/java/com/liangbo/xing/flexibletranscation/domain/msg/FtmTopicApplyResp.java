package com.liangbo.xing.flexibletranscation.domain.msg;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/23 下午10:49 xingliangbo Exp $
 */
public class FtmTopicApplyResp {
    private String topicId;
    private String topicName;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public FtmTopicApplyResp() {
    }
}
