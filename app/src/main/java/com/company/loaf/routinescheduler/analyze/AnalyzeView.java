package com.company.loaf.routinescheduler.analyze;

//Implemented by the ViewHolder found in MyAdapter
public interface AnalyzeView {
    void analyze();
    void displayResult(String result);
    void displayErrorText(String text);
}
