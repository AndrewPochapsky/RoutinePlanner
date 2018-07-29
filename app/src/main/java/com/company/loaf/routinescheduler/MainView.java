package com.company.loaf.routinescheduler;

public interface MainView {
    //if some EditText is left blank
    void showFieldError();
    void hideFieldError();
    void showDateError();
    void hideDateError();
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
