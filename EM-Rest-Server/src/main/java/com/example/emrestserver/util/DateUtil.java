package com.example.emrestserver.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

    public static String DateToString(Date date){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(date);
    }
}
