package com.company.loaf.routinescheduler.interact;

import com.company.loaf.routinescheduler.Routine;

public interface InteractView {
    void getSavedRoutines();
    void onCreateRoutine();
    void populateRecyclerView(Routine[] routines);
    void onSuccessfulDeletion(String name);
}
