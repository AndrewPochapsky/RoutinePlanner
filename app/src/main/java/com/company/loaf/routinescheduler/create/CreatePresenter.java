package com.company.loaf.routinescheduler.create;

import android.content.Context;

public class CreatePresenter implements CreateInteractor.OnCompleteListener{

    private CreateView mView;
    private CreateInteractor mInteractor;

    public CreatePresenter(CreateView view, CreateInteractor interactor){
        mView = view;
        mInteractor = interactor;
    }


    public void createRoutine(String name, String interval, String daysAgo, Context context){
        if(mView != null){
            mView.showProgress();
        }
        mInteractor.createRoutine(name, interval, daysAgo, this, context);
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
