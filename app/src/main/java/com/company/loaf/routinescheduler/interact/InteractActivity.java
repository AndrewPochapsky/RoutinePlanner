package com.company.loaf.routinescheduler.interact;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.company.loaf.routinescheduler.R;
import com.company.loaf.routinescheduler.Routine;
import com.company.loaf.routinescheduler.MyAdapter;
import com.company.loaf.routinescheduler.create.CreateActivity;
import com.company.loaf.routinescheduler.edit.EditActivity;

public class InteractActivity extends AppCompatActivity implements InteractView, MyAdapter.ExpandableButtonClickedListener, MyAdapter.SpinnerView{

    InteractPresenter mPresenter;

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

        mPresenter = new InteractPresenter(this, new InteractInteractor());

        getSavedRoutines();
        findViewById(R.id.create_routine_button).setOnClickListener(v -> onCreateRoutine());
    }

    @Override
    public void getSavedRoutines() {
         mPresenter.getSavedRoutines(this);
    }

    @Override
    public void onCreateRoutine() {
        Intent intent = new Intent(InteractActivity.this, CreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void populateRecyclerView(Routine[] routines) {
        mRoutines = routines;
        mRecyclerView.setAdapter(new MyAdapter(routines, this, this));
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete " + "'" + name +"'?")
                .setTitle("Are you sure?");

        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            mPresenter.deleteRoutine(mRoutines, name, this);
            dialogInterface.cancel();
        });

        builder.setNegativeButton("No", ((dialogInterface, i) -> {
            dialogInterface.cancel();
        }));

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onEditButtonPressed(String name, String interval) {
        Intent intent = new Intent(InteractActivity.this, EditActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("interval", interval);
        startActivity(intent);
    }

    @Override
    public void generateYears(Spinner spinner) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,  mPresenter.generateYears());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void generateMonths(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void generateDays(Spinner spinner, String month, String year) {
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,  mPresenter.generateDays(month, year));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
