package com.liangbo.xing.flexibletranscation.check;

import com.liangbo.xing.flexibletranscation.domain.FtmMqContent;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/21 下午8:46 xingliangbo Exp $
 */
public abstract class ConsumerChecker {

    public abstract boolean consume(FtmMqContent ftmMqContent);

}
