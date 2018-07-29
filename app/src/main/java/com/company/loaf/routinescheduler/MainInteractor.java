package com.company.loaf.routinescheduler;

import android.util.Log;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import static java.time.temporal.ChronoUnit.DAYS;

public class MainInteractor {

    interface OnAnalysisCompleteListener{
        void onFieldError();
        void onDateError();
        void onSuccess(String result);
    }

    //https://stackoverflow.com/questions/27005861/calculate-days-between-two-dates-in-java-8
    public void analyze(String interval, String daysAgo, String year, String month, String day, OnAnalysisCompleteListener listener){

        int intervalNum = Integer.parseInt(interval);
        int numDaysAgo = Integer.parseInt(daysAgo);
        int yearNum = Integer.parseInt(year);
        int monthNum = monthToInteger(month);
        int dayNum = Integer.parseInt(day);

        if(interval.isEmpty() || daysAgo.isEmpty()){
            listener.onFieldError();
            return;
        }
        if(isOldDate(yearNum, monthNum, dayNum)){
            listener.onDateError();
            return;
        }

        LocalDate lastCompletedDate = LocalDate.now().minusDays(numDaysAgo);
        LocalDate projectedDate = LocalDate.of(yearNum, monthNum, dayNum);

        long daysBetween = DAYS.between(lastCompletedDate, projectedDate);

        String result = "";
        if(daysBetween % intervalNum == 0){
            result = "Yes";
        }else{
            result = "No";
        }

        listener.onSuccess(result);
    }

    public String[] generateDays(String month, int year){
        YearMonth yearMonth = YearMonth.of(year, monthToInteger(month));
        int daysInMonth = yearMonth.lengthOfMonth();
        String[] days = new String[daysInMonth];
        for(int i = 1; i <= daysInMonth; i++){
            days[i - 1] = String.valueOf(i);
        }
        return days;
    }

    public String[] generateYears(){
        int yearAmount = 10;
        int currentYear = Year.now().getValue();
        String[] years = new String[yearAmount];
        for(int i = 0; i < yearAmount; i++){
            years[i] = String.valueOf(i + currentYear);
        }
        return years;
    }

    private int monthToInteger(String month){
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

    private boolean isOldDate(int year, int month, int day){
        LocalDate date = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        return date.isBefore(today);
    }
}
