package com.company.loaf.routinescheduler.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.company.loaf.routinescheduler.R;
import com.company.loaf.routinescheduler.interact.InteractActivity;

public class CreateActivity extends AppCompatActivity implements CreateView {

    CreatePresenter mPresenter;

    ProgressBar mProgressBar;
    TextView mFieldErrorText;
    EditText mNameText, mIntervalText, mDaysAgoText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        mPresenter = new CreatePresenter(this, new CreateInteractor());

        mNameText = findViewById(R.id.name_input);
        mIntervalText = findViewById(R.id.interval_input);
        mDaysAgoText = findViewById(R.id.days_ago_input);
        mFieldErrorText = findViewById(R.id.empty_field_error);
        mProgressBar = findViewById(R.id.create_progressbar);

        findViewById(R.id.create_button).setOnClickListener(v -> createRoutine());
        findViewById(R.id.create_back_button).setOnClickListener(v -> onBack());

    }

    @Override
    public void showFieldError() {
        mFieldErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFieldError() {
        mFieldErrorText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSaveError() {
        Toast.makeText(this, R.string.create_routine_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccess(String name) {
        Toast.makeText(this, "Successfully created routine " + "'" + name + "'", Toast.LENGTH_LONG).show();
        onBack();
    }

    @Override
    public void onBack() {
        Intent intent = new Intent(CreateActivity.this, InteractActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void createRoutine() {
        mPresenter.createRoutine(mNameText.getText().toString(), mIntervalText.getText().toString(), mDaysAgoText.getText().toString(), this);
    }
}
