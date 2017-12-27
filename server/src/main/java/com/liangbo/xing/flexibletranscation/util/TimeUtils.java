package com.liangbo.xing.flexibletranscation.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/23 下午4:28 xingliangbo Exp $
 */
public class TimeUtils {

    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
        return calendar.getTime();
    }
}
