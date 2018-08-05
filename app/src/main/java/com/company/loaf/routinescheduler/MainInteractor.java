package com.company.loaf.routinescheduler;

import android.util.Log;

import com.company.loaf.routinescheduler.Utils.DateUtils;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

import static com.company.loaf.routinescheduler.Utils.DateUtils.isOldDate;
import static java.time.temporal.ChronoUnit.DAYS;

public class MainInteractor {

    interface OnAnalysisCompleteListener{
        void onFieldError();
        void onDateError();
        void onSuccess(String result);
    }

    public void analyze(String interval, String daysAgo, String year, String month, String day, OnAnalysisCompleteListener listener){

        if(interval.isEmpty() || daysAgo.isEmpty()){
            listener.onFieldError();
            return;
        }

        int intervalNum = Integer.parseInt(interval);
        int numDaysAgo = Integer.parseInt(daysAgo);
        int yearNum = Integer.parseInt(year);
        int monthNum = DateUtils.monthToInteger(month);
        int dayNum = Integer.parseInt(day);

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
        YearMonth yearMonth = YearMonth.of(year, DateUtils.monthToInteger(month));
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

}
