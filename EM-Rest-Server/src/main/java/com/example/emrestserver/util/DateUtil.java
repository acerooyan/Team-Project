package com.example.emrestserver.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

    public static String DateToString(Date date){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(date);
    }
}
