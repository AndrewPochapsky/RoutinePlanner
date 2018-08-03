package com.company.loaf.routinescheduler.select;

import com.company.loaf.routinescheduler.Routine;

public interface SelectView {
    Routine[] getSavedRoutines();
    void onShowRoutine();
    void onCreateRoutine();
}
