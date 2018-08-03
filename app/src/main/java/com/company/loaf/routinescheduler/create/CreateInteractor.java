package com.company.loaf.routinescheduler.create;

import com.company.loaf.routinescheduler.Routine;
import com.company.loaf.routinescheduler.Utils.DateUtils;

import java.time.LocalDate;

public class CreateInteractor {

    interface OnCompleteListener{
        void onFieldError();
        void onSuccess();
    }


    void createRoutine(String name, String interval, String daysAgo, OnCompleteListener listener){

        if(name.isEmpty() || interval.isEmpty() || daysAgo.isEmpty()){
            listener.onFieldError();
            return;
        }

        int intervalNum = Integer.parseInt(interval);
        int numDaysAgo = Integer.parseInt(daysAgo);

        String dateStr = DateUtils.dateToString(LocalDate.now().minusDays(numDaysAgo));
        Routine routine = new Routine(name, intervalNum, dateStr);


    }
}
