package com.company.loaf.routinescheduler.utils;

import com.company.loaf.routinescheduler.Routine;
import com.google.gson.Gson;

public class JSONUtils {

    public static Routine[] jsonToRoutines(String json){
        return new Gson().fromJson(json, Routine[].class);
    }

    public static String routinesToJson(Routine[] routines){
        return new Gson().toJson(routines);
    }



}
