package com.liangbo.xing.flexibletranscation.dao.impl;

import com.liangbo.xing.flexibletranscation.dao.TopicSubDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicSubDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicSubDoExample;
import com.liangbo.xing.flexibletranscation.mapper.TopicSubDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/23 下午3:33 xingliangbo Exp $
 */
@Component
public class TopicSubDaoImpl implements TopicSubDao {

    @Autowired
    private TopicSubDoMapper topicSubDoMapper;

    @Override
    public List<TopicSubDo> selectByTopicIdAndRountingKey(String topicId,String rountingKey) {
        TopicSubDoExample topicSubDoExample = new TopicSubDoExample();
        topicSubDoExample.createCriteria().andTopicidEqualTo(topicId).andRountingkeyEqualTo(rountingKey);
        return topicSubDoMapper.selectByExample(topicSubDoExample);
    }

    @Override
    public void createTopicSub(TopicSubDo topicSubDo) {
        topicSubDoMapper.insert(topicSubDo);
    }

    @Override
    public Integer deleteTopicSub(TopicSubDo topicSubDo) {
        return topicSubDoMapper.deleteByPrimaryKey(topicSubDo.getId());
    }
}
