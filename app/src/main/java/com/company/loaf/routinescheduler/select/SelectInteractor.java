package com.company.loaf.routinescheduler.select;

import android.content.Context;

import com.company.loaf.routinescheduler.JSONConverter;
import com.company.loaf.routinescheduler.Routine;
import com.company.loaf.routinescheduler.Utils.FileUtils;


public class SelectInteractor {

    private final String FILE_NAME = "routines.json";


    //TODO: refactor this to another class as it is also needed elsewhere
    public Routine[] getSavedRoutines(Context context){
        return FileUtils.getSavedRoutines(context);
    }
}
