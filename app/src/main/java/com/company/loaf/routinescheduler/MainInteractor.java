package com.company.loaf.routinescheduler;

import android.util.Log;

import java.time.Year;
import java.time.YearMonth;

public class MainInteractor {

    interface OnAnalysisCompleteListener{
        void onError();
        void onSuccess(String result);
    }

    //https://stackoverflow.com/questions/27005861/calculate-days-between-two-dates-in-java-8
    public void analyze(String interval, String daysAgo, OnAnalysisCompleteListener listener){
        if(interval.isEmpty() || daysAgo.isEmpty()){
            listener.onError();
            return;
        }
        int intervalNum = Integer.parseInt(interval);
        int numDaysAgo = Integer.parseInt(daysAgo);

        //some analysis:
        listener.onSuccess("Success!");
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
        Log.e("Generate", "current year: " + currentYear);
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
}
