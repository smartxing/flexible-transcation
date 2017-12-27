package com.liangbo.xing.flexibletranscation.service;

import com.liangbo.xing.flexibletranscation.domain.FtmCommonResponse;
import com.liangbo.xing.flexibletranscation.domain.FtmPublishDto;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmBody;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmResponse;
import com.liangbo.xing.flexibletranscation.exception.FtmBizException;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午11:15 xingliangbo Exp $
 */
public interface MessagePublishService {

    public FtmResponse publish(FtmPublishDto ftmBody) throws FtmBizException;

    public FtmCommonResponse confirm(String messageId) throws FtmBizException;

}
