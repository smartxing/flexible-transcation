package com.liangbo.xing.flexibletranscation.service;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicDo;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmTopicApplyResp;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmTopicDto;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午8:04 xingliangbo Exp $
 */
public interface TopicService {

    public void createTopic(TopicDo topicDo);

    public FtmTopicApplyResp createTopic(FtmTopicDto ftmTopicDto);

    public Integer deleteTopic(TopicDo topicDo);

    public Optional<TopicDo> selectTopicByTopicId(String topicId);


}
