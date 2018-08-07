package com.company.loaf.routinescheduler.change;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.company.loaf.routinescheduler.R;
import com.company.loaf.routinescheduler.utils.ActivityUtils;

public class CreateActivity extends AppCompatActivity implements ChangeView {

    ChangePresenter mPresenter;

    ProgressBar mProgressBar;
    TextView mFieldErrorText;
    EditText mNameText, mIntervalText, mDaysAgoText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        mPresenter = new ChangePresenter(this, new ChangeInteractor());

        mNameText = findViewById(R.id.name_input);
        mIntervalText = findViewById(R.id.interval_input);
        mDaysAgoText = findViewById(R.id.days_ago_input);
        mFieldErrorText = findViewById(R.id.empty_field_error);
        mProgressBar = findViewById(R.id.create_progressbar);

        findViewById(R.id.create_button).setOnClickListener(v -> changeRoutine());
        findViewById(R.id.create_back_button).setOnClickListener(v -> onBack());

        ActivityUtils.setupActionBar(this, "Create Routine");

        /*getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);

        View v = getSupportActionBar().getCustomView();
        TextView titleTxtView = v.findViewById(R.id.action_bar_title);
        titleTxtView.setText("Create Routine");*/

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
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
        finish();
    }

    @Override
    public void changeRoutine() {
        mPresenter.createRoutine(mNameText.getText().toString(), mIntervalText.getText().toString(), mDaysAgoText.getText().toString(), this);
    }
}
