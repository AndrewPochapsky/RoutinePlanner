package com.company.loaf.routinescheduler;

public interface MainView {
    //if some EditText is left blank
    void showError();
    void hideError();
    //show/hide loading icon when analyzing
    void showProgress();
    void hideProgress();
    //display the result to user
    void displayInfo(String info);
    void validateInfo();

    void generateYears();
    void generateDays();
    void generateMonths();
}
