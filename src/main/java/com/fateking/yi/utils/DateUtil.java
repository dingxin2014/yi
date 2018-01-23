package com.fateking.yi.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final Date getUTCTime() {
        Calendar calendar = Calendar.getInstance();
        int offset = calendar.get(Calendar.ZONE_OFFSET);
        calendar.add(Calendar.MILLISECOND, -offset);
        return calendar.getTime();
    }
}
