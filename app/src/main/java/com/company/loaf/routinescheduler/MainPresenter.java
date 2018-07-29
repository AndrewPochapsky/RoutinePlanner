package com.company.loaf.routinescheduler;

public class MainPresenter implements MainInteractor.OnAnalysisCompleteListener{

    private MainView view;
    private MainInteractor interactor;

    public MainPresenter(MainView view, MainInteractor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    public void validateInfo(String interval, String numDaysAgo){
        if(view != null){
            view.showProgress();
        }
        interactor.analyze(interval, numDaysAgo, this);
    }

    public void onDestroy(){
        view = null;
    }

    @Override
    public void onError() {
        if(view != null){
            view.hideProgress();
            view.showError();
        }

    }

    @Override
    public void onSuccess(String result) {
        if(view != null){
            view.hideProgress();
            view.displayInfo(result);
        }
    }
}
