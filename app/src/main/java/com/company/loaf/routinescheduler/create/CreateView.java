package com.company.loaf.routinescheduler.create;

public interface CreateView {

    //if some EditText is left blank
    void showFieldError();
    void hideFieldError();

    void showProgress();
    void hideProgress();

    void showSaveError();

    void showSuccess(String name);

    void onBack();

    void changeRoutine();
}
