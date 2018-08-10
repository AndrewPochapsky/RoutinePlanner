package com.company.loaf.routinescheduler.analyze;

import com.company.loaf.routinescheduler.Routine;
import com.company.loaf.routinescheduler.utils.DateUtils;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class AnalyzeInteractor {

    interface OnAnalysisCompleteListener{
        void onCompletion(String result, boolean isError);
    }

    private Routine getRoutineByName(Routine[] routines, String name){
        for(Routine r: routines){
            if(r.getName().equals(name)){
                return r;
            }
        }
        return null;
    }

    public void analyze(Routine[] routines, String name, String year, String month, String day, OnAnalysisCompleteListener listener){

        Routine routine = getRoutineByName(routines, name);

        int yearNum = Integer.parseInt(year);
        int monthNum = DateUtils.monthToInteger(month);
        int dayNum = Integer.parseInt(day);

        if(DateUtils.isOldDate(yearNum, monthNum, dayNum)){
            listener.onCompletion("Invalid date", true);
            return;
        }

        LocalDate lastCompletedDate = DateUtils.stringToDate(routine.getSavedDate());
        LocalDate projectedDate = LocalDate.of(yearNum, monthNum, dayNum);

        long daysBetween = DAYS.between(lastCompletedDate, projectedDate);

        String result = "";
        if(daysBetween % routine.getInterval() == 0){
            result = "Yes";
        }else{
            result = "No";
        }

        listener.onCompletion(result, false);
    }
}
