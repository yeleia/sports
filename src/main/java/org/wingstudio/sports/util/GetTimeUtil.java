package org.wingstudio.sports.util;

import java.util.Calendar;

public class GetTimeUtil {
    public static String getTime(){
        Calendar calendar=Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String  month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String currentime=year+month;
        return currentime;
    }
}
