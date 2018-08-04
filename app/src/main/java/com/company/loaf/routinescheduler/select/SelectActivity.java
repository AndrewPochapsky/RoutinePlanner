package com.company.loaf.routinescheduler.select;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.company.loaf.routinescheduler.R;
import com.company.loaf.routinescheduler.Routine;
import com.company.loaf.routinescheduler.create.CreateActivity;

public class SelectActivity extends AppCompatActivity implements SelectView, SelectAdapter.ExpandableButtonClickedListener{

    SelectPresenter mPresenter;

    RecyclerView mRecyclerView;
    Routine[] mRoutines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_routine);
        Log.d("Select", "this");

        mRecyclerView = findViewById(R.id.rv_select);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mPresenter = new SelectPresenter(this, new SelectInteractor());

        getSavedRoutines();
        findViewById(R.id.create_routine_button).setOnClickListener(v -> onCreateRoutine());
    }

    @Override
    public void getSavedRoutines() {
         mPresenter.getSavedRoutines(this);
    }

    @Override
    public void onCreateRoutine() {
        Intent intent = new Intent(SelectActivity.this, CreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void populateRecyclerView(Routine[] routines) {
        mRoutines = routines;
        mRecyclerView.setAdapter(new SelectAdapter(routines, this));
    }

    @Override
    public void onSuccessfulDeletion(String name) {
        Toast.makeText(this, "Successfully deleted routine " + "'" + name + "'", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDeleteButtonPressed(String name) {
        mPresenter.deleteRoutine(mRoutines, name, this);
    }
}
