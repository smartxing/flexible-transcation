package com.liangbo.xing.flexibletranscation.service;

import com.liangbo.xing.flexibletranscation.domain.ftm.TopicSubDo;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmTopicSubDto;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午10:47 xingliangbo Exp $
 */
public interface TopicSubService {

    public void createTopicSub(FtmTopicSubDto ftmTopicSubDto);

    public Integer deleteTopicSub(TopicSubDo topicSubDo);

}
