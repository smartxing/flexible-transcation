package com.liangbo.xing.flexibletranscation.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/26 上午10:49 xingliangbo Exp $
 */
public class GsonUtil {

    private static Gson instance = null;

    public synchronized static Gson getGson() {
        if (instance == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
            builder.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
            instance = builder.create();
        }
        return instance;
    }

}
