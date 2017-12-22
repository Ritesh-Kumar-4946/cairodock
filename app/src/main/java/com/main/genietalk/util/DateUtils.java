package com.main.genietalk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gt9 on 6/12/17.
 */

public class DateUtils {
    public static String getCurrentDate(){
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(d);
        return dateStr;
    }
    public static String getCurrentDateForDisplay(){
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String dateStr = simpleDateFormat.format(d);
        return dateStr;
    }
    public static String getCurrentTimeForDisplay(){
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String dateStr = simpleDateFormat.format(d);
        return dateStr;
    }
    public static String getCurrentTime(){
        Date d = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        String dateStr = simpleDateFormat.format(d);
        return dateStr;
    }
    public static String convertSqlDateToDisplayDate(String inputDate){
        String resultDate= "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = simpleDateFormat.parse(inputDate);
            resultDate = setCurrentDateForDisplay(d) +" at "+setCurrentTimeForDisplay(d);
        }catch (Exception e){

        }
        return resultDate;
    }
    public static String getCurrentDateTimeForDisplay(){
        String resultStr = getCurrentDateForDisplay() + " at "+ getCurrentTimeForDisplay();
        return resultStr;
    }
    public static String setCurrentDateForDisplay(Date d){
        String resultStr = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            resultStr = simpleDateFormat.format(d);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultStr;
    }
    public static String setCurrentTimeForDisplay(Date d){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String dateStr = simpleDateFormat.format(d);
        return dateStr;
    }
}
