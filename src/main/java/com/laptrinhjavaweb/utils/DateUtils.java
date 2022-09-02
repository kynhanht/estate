package com.laptrinhjavaweb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String convertDateToString(Date date) {
        if(date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static String convertFullDateToString(Date date) {
        if(date == null) return null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }
}
