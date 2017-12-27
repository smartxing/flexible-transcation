package com.liangbo.xing.flexibletranscation.dao;

import com.liangbo.xing.flexibletranscation.domain.ftm.TopicSubDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TranscationMsgDo;

import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/23 下午3:32 xingliangbo Exp $
 */
public interface TopicSubDao {


    public List<TopicSubDo> selectByTopicIdAndRountingKey(String topicId,String routingKey);

    public void createTopicSub(TopicSubDo topicSubDo);


    public Integer deleteTopicSub(TopicSubDo topicSubDo);
}
