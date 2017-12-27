package com.liangbo.xing.flexibletranscation.dao;

import com.google.common.base.Optional;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDo;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午3:58 xingliangbo Exp $
 */
public interface AppDao {

    public Optional<AppDo> selectAppByAppIdAndAppKey(String appId, String appKey);


    public Optional<AppDo> selectAppByAppId(String appId);

    public AppDo saveAppAndReturnAppDo(AppDo appDo);

    public void deleteApp(AppDo appDo);
}
