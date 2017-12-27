package com.liangbo.xing.flexibletranscation.dao;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicDo;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午4:58 xingliangbo Exp $
 */
public interface TopicDao {

    public Optional<TopicDo> selectTopicByTopicId(String topicId);

    public void saveTopic(TopicDo topicDo);

    public String saveTopicAndReturnId(TopicDo topicDo);

    public Integer deleteTopic(TopicDo topicDo);
}
