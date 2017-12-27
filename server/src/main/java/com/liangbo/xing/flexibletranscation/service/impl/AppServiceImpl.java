package com.liangbo.xing.flexibletranscation.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.liangbo.xing.flexibletranscation.dao.AppDao;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDo;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDoExample;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmAppApplyResp;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmAppDto;
import com.liangbo.xing.flexibletranscation.mapper.AppDoMapper;
import com.liangbo.xing.flexibletranscation.service.AppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/20 上午10:33 xingliangbo Exp $
 */
@Service
public class AppServiceImpl implements AppService {


    @Autowired
    private AppDao appDao;


    @Override
    public FtmAppApplyResp createApp(FtmAppDto ftmAppDto) {
        Preconditions.checkNotNull(ftmAppDto, "参数不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(ftmAppDto.getAppName()), "申请应用名字不能为空");
        AppDo appDo = new AppDo();
        appDo.setAppname(ftmAppDto.getAppName());
        appDo.setAppdesc(ftmAppDto.getAppDesc());
        appDo.setCallbackurl(ftmAppDto.getCallBackUrl());
        appDo.setUpdatetime(new Date());
        appDo.setCreatetime(new Date());
        AppDo result = appDao.saveAppAndReturnAppDo(appDo);
        FtmAppApplyResp ftmAppApplyResp = new FtmAppApplyResp();
        ftmAppApplyResp.setAppId(result.getAppid());
        ftmAppApplyResp.setAppName(appDo.getAppname());
        ftmAppApplyResp.setAppKey(appDo.getAppkey());
        return ftmAppApplyResp;
    }


    @Override
    public Optional<AppDo> selectAppById(String appId) {
        return appDao.selectAppByAppId(appId);
    }
}
