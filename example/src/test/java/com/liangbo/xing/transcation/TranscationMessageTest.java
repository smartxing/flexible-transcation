package com.liangbo.xing.transcation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liangbo.xing.AbstractTranscationTest;
import com.liangbo.xing.flexibletranscation.client.TranscationMsgClient;
import com.liangbo.xing.flexibletranscation.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/26 上午9:48 xingliangbo Exp $
 */
public class TranscationMessageTest extends AbstractTranscationTest {

    @Autowired
    private TranscationMsgClient transcationMsgClient;
    private Gson gson = new GsonBuilder().serializeNulls().create();

    @Test
    public void testPrepare() {
        FtmPublishDto ftmPublishDto = new FtmPublishDto();
        ftmPublishDto.setMessageBody("hello world");
        ftmPublishDto.setRouteKey("mytest-routingkey");
        ftmPublishDto.setTopicId("d2fed037-649e-4e69-8906-69c5932d8369");
        ftmPublishDto.setPubId("d37137cc-f3d4-4af8-8dc1-e7e4aa904555");
        ftmPublishDto.setPubkey("4442fad0-5b45-4ac1-9d62-b10b7a607bad");
        FtmResponse ftmResponse = transcationMsgClient.publish(ftmPublishDto);
        System.out.println(gson.toJson(ftmResponse));
    }


    @Test
    public void testConfirm() {
        FtmConfirmDto ftmConfirmDto = new FtmConfirmDto();
        ftmConfirmDto.setMessageId("8c0add9a-a4e0-4e1b-8d1b-baf4b319feff");
        FtmCommonResponse ftmCommonResponse = transcationMsgClient.comfirm(ftmConfirmDto);
        System.out.println(gson.toJson(ftmCommonResponse));
    }


    @Test
    public void testAsk() {
        FtmAskingDto ftmAskingDto = new FtmAskingDto();
        ftmAskingDto.setSubId("8b441880-8f37-431c-b8b8-3abed6f51858");
        ftmAskingDto.setMessageId("8c0add9a-a4e0-4e1b-8d1b-baf4b319feff");
        ftmAskingDto.setAppKey("8fef4f7a-0928-45aa-9bd6-74e1092abb8d");
        FtmCommonResponse ftmCommonResponse = transcationMsgClient.ask(ftmAskingDto);
        System.out.println(gson.toJson(ftmCommonResponse));
    }


}
