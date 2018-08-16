package com.company.loaf.routinescheduler.change;

import android.content.Context;

public class ChangePresenter implements ChangeInteractor.OnCompleteListener{

    private ChangeView mView;
    private ChangeInteractor mInteractor;

    public ChangePresenter(ChangeView view, ChangeInteractor interactor){
        mView = view;
        mInteractor = interactor;
    }

    public void createRoutine(String name, String interval, String daysAgo, Context context){
        if(mView != null){
            mView.showProgress();
        }
        mInteractor.createRoutine(name, interval, daysAgo, this, context);
    }

    public void editRoutine(String oldName, String name, String interval, String daysAgo, Context context){
        mInteractor.editRoutine(oldName, name, interval, daysAgo, this, context);
    }

    @Override
    public void onFieldError() {
        if(mView != null){
            mView.hideProgress();
            mView.showFieldError();
        }

    }

    @Override
    public void onSaveError() {
        if(mView != null){
            mView.hideProgress();
            mView.showSaveError();
        }
    }

    @Override
    public void onNameError() {
        if(mView != null){
            mView.hideProgress();
            mView.showDuplicateNameError();
        }
    }

    @Override
    public void onSuccess(String name) {
        if(mView != null){
            mView.hideFieldError();
            mView.showSuccess(name);
        }
    }

    public void onDestroy(){
        mView = null;
    }
}
