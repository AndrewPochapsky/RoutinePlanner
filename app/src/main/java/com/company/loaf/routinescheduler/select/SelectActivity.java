package com.company.loaf.routinescheduler.select;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.company.loaf.routinescheduler.R;
import com.company.loaf.routinescheduler.Routine;

public class SelectActivity extends AppCompatActivity implements SelectView {

    SelectPresenter mPresenter;

    RecyclerView mRecyclerView;
    SelectAdapter mAdapter;

    Routine[] mRoutines;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_routine);
        Log.d("Select", "this");

        mRecyclerView = findViewById(R.id.rv_select);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mPresenter = new SelectPresenter(this, new SelectInteractor());

        mRoutines = getSavedRoutines();

        mAdapter = new SelectAdapter(mRoutines);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public Routine[] getSavedRoutines() {
        return mPresenter.getSavedRoutines(this);
    }

    @Override
    public void onShowRoutine() {

    }

    @Override
    public void onCreateRoutine() {
        
    }
}
