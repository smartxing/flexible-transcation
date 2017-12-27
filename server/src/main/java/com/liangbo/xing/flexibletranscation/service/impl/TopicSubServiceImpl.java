package com.liangbo.xing.flexibletranscation.service.impl;

import com.liangbo.xing.flexibletranscation.dao.TopicSubDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicSubDo;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmTopicSubDto;
import com.liangbo.xing.flexibletranscation.mapper.TopicSubDoMapper;
import com.liangbo.xing.flexibletranscation.service.TopicSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午10:47 xingliangbo Exp $
 */
@Service
public class TopicSubServiceImpl implements TopicSubService {


    @Autowired
    private TopicSubDao topicSubDao;

    @Override
    public void createTopicSub(FtmTopicSubDto ftmTopicSubDto) {
        TopicSubDo topicSubDo = new TopicSubDo();
        topicSubDo.setSubid(ftmTopicSubDto.getSubId());
        topicSubDo.setTopicid(ftmTopicSubDto.getTopicId());
        topicSubDo.setRountingkey(ftmTopicSubDto.getRountingKey());
        topicSubDo.setUpdatetime(new Date());
        topicSubDo.setCreatetime(new Date());
        topicSubDao.createTopicSub(topicSubDo);
    }

    @Override
    public Integer deleteTopicSub(TopicSubDo topicSubDo) {
        return topicSubDao.deleteTopicSub(topicSubDo);
    }

}
