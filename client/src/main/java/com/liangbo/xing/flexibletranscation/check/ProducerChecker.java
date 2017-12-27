package com.liangbo.xing.flexibletranscation.check;

import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckDto;
import com.liangbo.xing.flexibletranscation.enums.TranscationStatus;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午7:56 xingliangbo Exp $
 */
public abstract class ProducerChecker {

    //状态检查接口
    public abstract String check(FtmProducerCheckDto ftmProducerCheckDto);


}
