package com.liangbo.xing.flexibletranscation.client;

import com.liangbo.xing.flexibletranscation.domain.FtmAskingDto;
import com.liangbo.xing.flexibletranscation.domain.FtmCommonResponse;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckDto;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckResp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/23 下午2:25 xingliangbo Exp $
 */
public interface ProducerRecheckApi {

    @POST("/transcation/check")
    Call<String> recheck(@Body FtmProducerCheckDto ftmProducerCheckDto);

}
