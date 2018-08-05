package com.company.loaf.routinescheduler.interact;

import android.content.Context;

import com.company.loaf.routinescheduler.Routine;

public class InteractPresenter {
    private InteractView mView;
    private InteractInteractor mInteractor;

    public InteractPresenter(InteractView view, InteractInteractor interactor){
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

    public String[] generateDays(String month, String year){
        return mInteractor.generateDays(month, Integer.parseInt(year));
    }

    public String[] generateYears(){
        return mInteractor.generateYears();
    }
}
