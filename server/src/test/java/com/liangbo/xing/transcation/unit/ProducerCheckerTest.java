package com.liangbo.xing.transcation.unit;

import com.liangbo.xing.flexibletranscation.client.ProducerRecheckApi;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckDto;
import com.liangbo.xing.flexibletranscation.domain.FtmProducerCheckResp;
import com.liangbo.xing.flexibletranscation.enums.TranscationStatus;
import com.liangbo.xing.flexibletranscation.util.FtmClient;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Action1;

import java.io.IOException;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/25 下午11:16 xingliangbo Exp $
 */
public class ProducerCheckerTest {

    @Test
    public void test() throws IOException {
        FtmProducerCheckDto ftmProducerCheckDto = new FtmProducerCheckDto();
        ftmProducerCheckDto.setTopic("kaka");
        ftmProducerCheckDto.setMessageBody("lala");
        ftmProducerCheckDto.setRountingKey("huhu");
        ProducerRecheckApi producerRecheckApi = FtmClient.create("http://127.0.0.1:8081/", ProducerRecheckApi.class);
        Call<String> status = producerRecheckApi.recheck(ftmProducerCheckDto);
        System.out.println(status.execute().body());
    }
}
