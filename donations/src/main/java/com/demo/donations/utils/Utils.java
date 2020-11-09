package com.demo.donations.utils;

import java.util.Date;
import java.util.LinkedHashMap;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;

public class Utils {
    
    public static String CODE_OK = "200";
    public static String CODE_ERROR = "500";
    public static String CODE_NOT_FOUND = "404";
    public static String CODE_UNAUTORIZED = "403";
    public static String CODE_BAD_REQUEST = "400";

    public static LinkedHashMap<String, Integer> getDateDiff(Date start, Date end) {
        LinkedHashMap<String, Integer> hashmap = new LinkedHashMap<String, Integer>();
        DateTime startDT = new DateTime(start);
        DateTime endDT = new DateTime(end);
        hashmap.put("years", Years.yearsBetween(startDT, endDT).getYears());
        hashmap.put("months", Months.monthsBetween(startDT, endDT).getMonths());
        hashmap.put("weeks", Weeks.weeksBetween(startDT, endDT).getWeeks());
        hashmap.put("days", Days.daysBetween(startDT, endDT).getDays());
        return hashmap;
    }
}
