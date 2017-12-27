package com.liangbo.xing.flexibletranscation.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liangbo.xing.flexibletranscation.client.TranscationMsgApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/22 上午11:30 xingliangbo Exp $
 */
public class FtmClient {

    public static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    public static <T> T create(String targetServer, Class<T> aClass) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(targetServer)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        T api = retrofit.create(aClass);
        return api;
    }


}
