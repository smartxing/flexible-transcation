package com.liangbo.xing.flexibletranscation.client;

import com.liangbo.xing.flexibletranscation.domain.*;
import com.liangbo.xing.flexibletranscation.exception.FtmClientException;
import com.liangbo.xing.flexibletranscation.util.FtmClient;

import java.io.IOException;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/26 上午12:13 xingliangbo Exp $
 */
public class TranscationMsgClient {

    private TranscationMsgApi transcationMsgApi;

    public TranscationMsgClient(String addr) {
        this.transcationMsgApi = FtmClient.create(addr, TranscationMsgApi.class);
    }

    public FtmCommonResponse ask(FtmAskingDto ftmAskingDto) {
        try {
            return transcationMsgApi.ask(ftmAskingDto).execute().body();
        } catch (IOException e) {
            throw new FtmClientException("ask error", e);
        }
    }

    public FtmResponse publish(FtmPublishDto ftmPublishDto) {
        try {
            return transcationMsgApi.publish(ftmPublishDto).execute().body();
        } catch (IOException e) {
            throw new FtmClientException("publish error", e);
        }
    }

    public FtmCommonResponse comfirm(FtmConfirmDto ftmConfirmDto) {
        try {
            return transcationMsgApi.comfirm(ftmConfirmDto).execute().body();
        } catch (IOException e) {
            throw new FtmClientException("confirm error", e);
        }
    }


}
