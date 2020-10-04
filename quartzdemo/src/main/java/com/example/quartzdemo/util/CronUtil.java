package com.example.quartzdemo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/4 17:48
 */
public class CronUtil {

    public static String formatDateByPattern(Date date,String dateFormat){
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null ) {
            formatTimeStr = format.format(date);
        }
        return formatTimeStr;
    }

    public static String getCron(java.util.Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

}
