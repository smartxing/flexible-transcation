package com.liangbo.xing.flexibletranscation.dao.impl;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.dao.AppDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDoExample;
import com.liangbo.xing.flexibletranscation.mapper.AppDoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 下午3:59 xingliangbo Exp $
 */
@Component
public class AppDaoImpl implements AppDao {

    @Autowired
    private AppDoMapper appDoMapper;

    @Override
    public Optional<AppDo> selectAppByAppIdAndAppKey(String appId, String appKey) {
        AppDoExample appDoExample = new AppDoExample();
        appDoExample.createCriteria().andAppidEqualTo(appId).andAppkeyEqualTo(appKey);
        List<AppDo> appDos = appDoMapper.selectByExample(appDoExample);
        if (!CollectionUtils.isEmpty(appDos) && appDos.size() > 0) {
            Preconditions.checkArgument(appDos.size() <= 1);
            return Optional.fromNullable(appDos.get(0));
        }
        return Optional.absent();
    }

    @Override
    public Optional<AppDo> selectAppByAppId(String appId) {
        AppDoExample appDoExample = new AppDoExample();
        appDoExample.createCriteria().andAppidEqualTo(appId);
        List<AppDo> appDos = appDoMapper.selectByExample(appDoExample);
        if (!CollectionUtils.isEmpty(appDos) && appDos.size() > 0) {
            return Optional.fromNullable(appDos.get(0));
        }
        return Optional.absent();
    }

    @Override
    public AppDo saveAppAndReturnAppDo(AppDo appDo) {
        appDo.setAppid(UUID.randomUUID().toString());
        appDo.setAppkey(UUID.randomUUID().toString());
        appDoMapper.insertSelective(appDo);
        return appDo;
    }

    @Override
    public void deleteApp(AppDo appDo) {
        appDoMapper.deleteByPrimaryKey(appDo.getId());
    }
}
