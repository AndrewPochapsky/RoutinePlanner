package com.company.loaf.routinescheduler.Utils;

import android.content.Context;

import com.company.loaf.routinescheduler.JSONConverter;
import com.company.loaf.routinescheduler.Routine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtils {

    private static final String FILE_NAME = "routines.json";

    public static Routine[] getSavedRoutines(Context context){
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
