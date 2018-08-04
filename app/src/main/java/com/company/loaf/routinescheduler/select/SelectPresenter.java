package com.company.loaf.routinescheduler.select;

import android.content.Context;

import com.company.loaf.routinescheduler.Routine;

public class SelectPresenter {
    private SelectView mView;
    private SelectInteractor mInteractor;

    public SelectPresenter(SelectView view, SelectInteractor interactor){
        mView = view;
        mInteractor = interactor;
    }
    public void onDestroy(){
        mView = null;
    }

    public void getSavedRoutines(Context c){
         Routine[] routines = mInteractor.getSavedRoutines(c);
        if(mView != null){
            mView.populateRecyclerView(routines);
        }
    }

    public void deleteRoutine(Routine[] routines, String routineToDelete, Context c){
         Routine[] newRoutines = mInteractor.deleteRoutine(routines, routineToDelete, c);
         if(mView != null){
             mView.onSuccessfulDeletion(routineToDelete);
             mView.populateRecyclerView(newRoutines);
         }
    }
}
