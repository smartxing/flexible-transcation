package com.liangbo.xing.flexibletranscation.client;


import com.liangbo.xing.flexibletranscation.domain.*;
import com.liangbo.xing.flexibletranscation.enums.TranscationStatus;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 上午10:32 xingliangbo Exp $
 */
public interface TranscationMsgApi {

    @POST("/flexible-transcation/api/v1/manager/ask")
    Call<FtmCommonResponse> ask(@Body FtmAskingDto ftmAskingDto);

    @POST("/flexible-transcation/api/v1/manager/msg")
    Call<FtmResponse> publish(@Body FtmPublishDto ftmPublishDto);

    @POST("/flexible-transcation/api/v1/manager/msg/confirmed")
    Call<FtmCommonResponse> comfirm(@Body FtmConfirmDto ftmConfirmDto);

}
