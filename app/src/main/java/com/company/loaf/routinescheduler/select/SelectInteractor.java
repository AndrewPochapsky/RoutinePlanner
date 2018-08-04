package com.company.loaf.routinescheduler.select;

import android.content.Context;

import com.company.loaf.routinescheduler.Routine;
import com.company.loaf.routinescheduler.Utils.FileUtils;
import com.company.loaf.routinescheduler.Utils.JSONUtils;


public class SelectInteractor {

    public Routine[] getSavedRoutines(Context context){
        return FileUtils.getSavedRoutines(context);
    }

    //TODO: Potential issue of duplicate routines
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
}
