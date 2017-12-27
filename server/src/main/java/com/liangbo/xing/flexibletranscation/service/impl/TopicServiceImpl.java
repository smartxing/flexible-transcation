package com.liangbo.xing.flexibletranscation.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.dao.TopicDao;
import com.liangbo.xing.flexibletranscation.dao.TopicSubDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.TopicSubDo;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmTopicApplyResp;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmTopicDto;
import com.liangbo.xing.flexibletranscation.mapper.TopicDoMapper;
import com.liangbo.xing.flexibletranscation.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午10:36 xingliangbo Exp $
 */
@Service
public class TopicServiceImpl implements TopicService {


    @Autowired
    private TopicDao topicDao;
    @Autowired
    private TopicSubDao topicSubDao;

    @Override
    public void createTopic(TopicDo topicDo) {
        topicDao.saveTopic(topicDo);
    }

    @Override
    public FtmTopicApplyResp createTopic(FtmTopicDto ftmTopicDto) {
        FtmTopicApplyResp ftmTopicApplyResp = new FtmTopicApplyResp();
        TopicDo topicDo = buiderTopicDo(ftmTopicDto);
        String topicId = topicDao.saveTopicAndReturnId(topicDo);
        ftmTopicApplyResp.setTopicId(topicId);
        ftmTopicApplyResp.setTopicName(topicDo.getTopic());
        return ftmTopicApplyResp;
    }

    private TopicSubDo getTopicSubDo(String topicId, String subId) {
        TopicSubDo topicSubDo = new TopicSubDo();
        topicSubDo.setTopicid(topicId);
        topicSubDo.setCreatetime(new Date());
        topicSubDo.setUpdatetime(new Date());
        topicSubDo.setSubid(subId);
        return topicSubDo;
    }

    private TopicDo buiderTopicDo(FtmTopicDto ftmTopicDto) {
        TopicDo topicDo = new TopicDo();
        topicDo.setTopic(ftmTopicDto.getTopic());
        topicDo.setTopicdesc(ftmTopicDto.getTopicDesc());
        topicDo.setRetryinterval(ftmTopicDto.getRetryInterval());
        topicDo.setMaxretrytime(ftmTopicDto.getMaxRetryTime());
        topicDo.setCreatetime(new Date());
        topicDo.setUpdatetime(new Date());
        return topicDo;
    }

    @Override
    public Integer deleteTopic(TopicDo topicDo) {
        return topicDao.deleteTopic(topicDo);
    }


    @Override
    public Optional<TopicDo> selectTopicByTopicId(String topicId) {
        return topicDao.selectTopicByTopicId(topicId);
    }
}
