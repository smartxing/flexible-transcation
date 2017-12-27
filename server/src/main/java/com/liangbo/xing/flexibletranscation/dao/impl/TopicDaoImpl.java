package com.liangbo.xing.flexibletranscation.dao.impl;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.dao.TopicDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicDoExample;
import com.liangbo.xing.flexibletranscation.mapper.TopicDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午4:58 xingliangbo Exp $
 */
@Component
public class TopicDaoImpl implements TopicDao {

    @Autowired
    private TopicDoMapper topicDoMapper;

    public Optional<TopicDo> selectTopicByTopicId(String topicId) {
        TopicDoExample topicDoExample = new TopicDoExample();
        topicDoExample.createCriteria().andTopicidEqualTo(topicId);
        List<TopicDo> topicDos = topicDoMapper.selectByExample(topicDoExample);
        if (!CollectionUtils.isEmpty(topicDos) && topicDos.size() > 0) {
            return Optional.fromNullable(topicDos.get(0));
        }
        return Optional.absent();
    }

    @Override
    public void saveTopic(TopicDo topicDo) {
        topicDoMapper.insert(topicDo);
    }

    @Override
    public String saveTopicAndReturnId(TopicDo topicDo) {
        String uuid = UUID.randomUUID().toString();
        topicDo.setTopicid(uuid);
        topicDoMapper.insert(topicDo);
        return uuid;
    }

    @Override
    public Integer deleteTopic(TopicDo topicDo) {
        return topicDoMapper.deleteByPrimaryKey(topicDo.getId());
    }
}
