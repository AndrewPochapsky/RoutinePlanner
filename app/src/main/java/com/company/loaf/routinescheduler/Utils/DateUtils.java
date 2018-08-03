package com.company.loaf.routinescheduler.Utils;

import java.time.LocalDate;

public class DateUtils {
     public static String dateToString(LocalDate date){
         return date.getYear() + "|" + date.getMonthValue() + "|" + date.getDayOfMonth();
     }
     
     public static LocalDate stringToDate(String s){
         String year = s.substring(0, s.indexOf("|"));
         s = s.substring(s.indexOf("|") + 1, s.length());

         String month = s.substring(0, s.indexOf("|"));
         s = s.substring(s.indexOf("|") + 1, s.length());

         return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(s));
     }
}
