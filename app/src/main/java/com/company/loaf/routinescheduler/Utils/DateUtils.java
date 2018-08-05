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

    public static int monthToInteger(String month){
        switch (month){
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
        }
        return -1;
    }

    public static boolean isOldDate(int year, int month, int day){
        LocalDate date = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        return date.isBefore(today);
    }

}
