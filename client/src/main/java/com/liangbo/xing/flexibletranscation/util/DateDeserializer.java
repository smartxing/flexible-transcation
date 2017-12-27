package com.liangbo.xing.flexibletranscation.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/26 上午10:46 xingliangbo Exp $
 */
public class DateDeserializer implements JsonDeserializer<Date> {
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
    }
}

