package com.company.loaf.routinescheduler.select;

import android.content.Context;

import com.company.loaf.routinescheduler.JSONConverter;
import com.company.loaf.routinescheduler.Routine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SelectInteractor {

    private final String FILE_NAME = "routines.json";



    public Routine[] getSavedRoutines(Context context){
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json =  sb.toString();

            Routine[] arr = JSONConverter.jsonToRoutines(json);

            if(arr == null){
                return new Routine[0];
            }else{
                return arr;
            }

        } catch (IOException ioException) {
            return new Routine[0];
        }
    }
}
