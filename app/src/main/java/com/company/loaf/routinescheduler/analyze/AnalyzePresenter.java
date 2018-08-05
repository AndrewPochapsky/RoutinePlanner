package com.company.loaf.routinescheduler.analyze;

import com.company.loaf.routinescheduler.Routine;

public class AnalyzePresenter implements AnalyzeInteractor.OnAnalysisCompleteListener{
    private AnalyzeView mView;
    private AnalyzeInteractor mInteractor;

    public AnalyzePresenter(AnalyzeView view, AnalyzeInteractor interactor){
        mView = view;
        mInteractor = interactor;
    }

    public void analyze(Routine[] routines, String name, String year, String month, String day){
        mInteractor.analyze(routines, name, year, month, day, this);
    }

    @Override
    public void onCompletion(String result){
        if(mView != null){
            mView.displayResult(result);
        }
    }
}
