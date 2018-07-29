package com.company.loaf.routinescheduler;

public class MainInteractor {

    interface OnAnalysisCompleteListener{
        void onError();
        void onSuccess(String result);
    }

    public void analyze(String interval, String numDaysAgo, OnAnalysisCompleteListener listener){
        if(interval.isEmpty() || numDaysAgo.isEmpty()){
            listener.onError();
            return;
        }
        //some analysis:
        listener.onSuccess("Success!");
    }
}
