package com.liangbo.xing.transcation.unit;

import com.google.gson.GsonBuilder;
import com.liangbo.xing.AbstractTranscationTest;
import com.liangbo.xing.flexibletranscation.domain.ftm.AppDo;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmAppApplyResp;
import com.liangbo.xing.flexibletranscation.domain.msg.FtmAppDto;
import com.liangbo.xing.flexibletranscation.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/24 下午9:21 xingliangbo Exp $
 */
public class AppServiceTest extends AbstractTranscationTest {

    private Logger logger = LoggerFactory.getLogger(AppServiceTest.class);

    @Autowired
    private AppService appService;

    @DataProvider
    public Object[][] generateApp() {
        FtmAppDto ftmAppDto = new FtmAppDto();
        ftmAppDto.setAppName("test");
        ftmAppDto.setAppDesc("test 123");
        ftmAppDto.setCallBackUrl("http://127.0.0.1:8080/");
        return new Object[][]{{ftmAppDto}};
    }


    @Transactional
    @Rollback
    @Test(dataProvider = "generateApp")
    public void testCreateApp(FtmAppDto ftmAppDto) {
        FtmAppApplyResp ftmAppApplyResp = appService.createApp(ftmAppDto);
        logger.info(new GsonBuilder().create().toJson(ftmAppApplyResp));
        AppDo appDo = appService.selectAppById(ftmAppApplyResp.getAppId()).get();
        Assert.assertEquals(appDo.getAppid(), ftmAppApplyResp.getAppId());
        Assert.assertEquals(appDo.getAppname(), ftmAppApplyResp.getAppName());
        Assert.assertEquals(appDo.getAppdesc(), ftmAppDto.getAppDesc());
    }
}
