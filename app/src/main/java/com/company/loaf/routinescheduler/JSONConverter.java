package com.company.loaf.routinescheduler;

import com.google.gson.Gson;

public class JSONConverter {

    public static Routine[] jsonToRoutines(String json){
        return new Gson().fromJson(json, Routine[].class);
    }



}
