package com.company.loaf.routinescheduler.select;

import android.content.Context;

import com.company.loaf.routinescheduler.Routine;

public class SelectPresenter {
    SelectView mView;
    SelectInteractor mInteractor;

    public SelectPresenter(SelectView view, SelectInteractor interactor){
        mView = view;
        mInteractor = interactor;
    }
    public void onDestroy(){
        mView = null;
    }
    Routine[] getSavedRoutines(Context c){
        return mInteractor.getSavedRoutines(c);
    }
}
