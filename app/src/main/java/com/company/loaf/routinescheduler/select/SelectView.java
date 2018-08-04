package com.company.loaf.routinescheduler.select;

import com.company.loaf.routinescheduler.Routine;

public interface SelectView {
    void getSavedRoutines();
    void onCreateRoutine();
    void populateRecyclerView(Routine[] routines);
    void onSuccessfulDeletion(String name);
}
