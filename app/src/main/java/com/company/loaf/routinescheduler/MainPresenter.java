package com.company.loaf.routinescheduler;

public class MainPresenter implements MainInteractor.OnAnalysisCompleteListener{

    private MainView mView;
    private MainInteractor mInteractor;

    public MainPresenter(MainView view, MainInteractor mInteractor){
        this.mView = view;
        this.mInteractor = mInteractor;
    }

    public void validateInfo(String interval, String numDaysAgo, String year, String month, String day){
        if(mView != null){
            mView.showProgress();
        }
        mInteractor.analyze(interval, numDaysAgo, year, month, day, this);
    }
    
    public String[] generateDays(String month, String year){
        return mInteractor.generateDays(month, Integer.parseInt(year));
    }
    
    public String[] generateYears(){
        return mInteractor.generateYears();
    }

    public void onDestroy(){
        mView = null;
    }

    @Override
    public void onFieldError() {
        if(mView != null){
            mView.hideProgress();
            mView.showFieldError();
        }

    }

    @Override
    public void onDateError() {
        if(mView != null){
            mView.hideProgress();
            mView.showDateError();
        }
    }

    @Override
    public void onSuccess(String result) {
        if(mView != null){
            mView.hideProgress();
            mView.hideFieldError();
            mView.hideDateError();
            mView.displayInfo(result);
        }
    }
}
