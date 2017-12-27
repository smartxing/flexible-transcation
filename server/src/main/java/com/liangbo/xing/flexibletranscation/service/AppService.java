package com.liangbo.xing.flexibletranscation.service;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDo;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmAppApplyResp;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmAppDto;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/19 下午8:04 xingliangbo Exp $
 */
public interface AppService {

    /**
     * 创建应用
     */
    public FtmAppApplyResp createApp(FtmAppDto ftmAppDto);

    public Optional<AppDo> selectAppById(String appId);


}
