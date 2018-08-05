package com.company.loaf.routinescheduler.change;

import android.content.Context;

import com.company.loaf.routinescheduler.Routine;
import com.company.loaf.routinescheduler.Utils.DateUtils;
import com.company.loaf.routinescheduler.Utils.FileUtils;
import com.company.loaf.routinescheduler.Utils.JSONUtils;

import java.time.LocalDate;
import java.util.Arrays;

public class ChangeInteractor {

    public interface OnCompleteListener{
        void onFieldError();
        void onSaveError();
        void onSuccess(String name);
    }

    void createRoutine(String name, String interval, String daysAgo, OnCompleteListener listener, Context context){

        if(name.isEmpty() || interval.isEmpty() || daysAgo.isEmpty()){
            listener.onFieldError();
            return;
        }

        int intervalNum = Integer.parseInt(interval);
        int numDaysAgo = Integer.parseInt(daysAgo);

        //Create a new routine object based on user input
        String dateStr = DateUtils.dateToString(LocalDate.now().minusDays(numDaysAgo));
        Routine routine = new Routine(name, intervalNum, dateStr);

        //Retrieve the existing routines from internal storage
        Routine[] existingRoutines = FileUtils.getSavedRoutines(context);

        //Create a copy of that returned array and create a new one with one more space and put the new routine in that space
        Routine[] newRoutines = Arrays.copyOf(existingRoutines, existingRoutines.length + 1);
        newRoutines[existingRoutines.length] = routine;

        //Write the new array into internal storage
        String json = JSONUtils.routinesToJson(newRoutines);

        boolean successfulSave = FileUtils.saveRoutines(context, json);

        if(successfulSave){
            listener.onSuccess(routine.getName());
        }else{
            listener.onSaveError();
        }
    }

    void editRoutine(String oldName, String name, String interval, String daysAgo, ChangeInteractor.OnCompleteListener listener, Context context){

        if(name.isEmpty() || interval.isEmpty() || daysAgo.isEmpty()){
            listener.onFieldError();
            return;
        }

        int intervalNum = Integer.parseInt(interval);
        int numDaysAgo = Integer.parseInt(daysAgo);
        String dateStr = DateUtils.dateToString(LocalDate.now().minusDays(numDaysAgo));

        Routine routineToEdit = null;

        //Retrieve the existing routines from internal storage
        Routine[] existingRoutines = FileUtils.getSavedRoutines(context);

        for(Routine r : existingRoutines){
            if(r.getName().equals(oldName)){
                routineToEdit = r;
                break;
            }
        }

        routineToEdit.setInterval(intervalNum);
        routineToEdit.setName(name);
        routineToEdit.setSavedDate(dateStr);

        //Write the new array into internal storage
        String json = JSONUtils.routinesToJson(existingRoutines);

        boolean successfulSave = FileUtils.saveRoutines(context, json);

        if(successfulSave){
            listener.onSuccess(routineToEdit.getName());
        }else{
            listener.onSaveError();
        }
    }
}
