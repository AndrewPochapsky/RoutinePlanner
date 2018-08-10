package com.company.loaf.routinescheduler.interact;

import android.content.Context;

import com.company.loaf.routinescheduler.Routine;
import com.company.loaf.routinescheduler.utils.DateUtils;
import com.company.loaf.routinescheduler.utils.FileUtils;
import com.company.loaf.routinescheduler.utils.JSONUtils;

import java.time.Year;
import java.time.YearMonth;


public class InteractInteractor {

    public Routine[] getSavedRoutines(Context context){
        return FileUtils.getSavedRoutines(context);
    }

    public Routine[] deleteRoutine(Routine[] routines, String routineToDelete, Context context){
        Routine[] newRoutines = new Routine[routines.length - 1];
        int index = 0;
        for(Routine r : routines){
            if(!r.getName().equals(routineToDelete)){
                newRoutines[index] = r;
                index++;
            }
        }

        FileUtils.saveRoutines(context, JSONUtils.routinesToJson(newRoutines));

        return newRoutines;
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
