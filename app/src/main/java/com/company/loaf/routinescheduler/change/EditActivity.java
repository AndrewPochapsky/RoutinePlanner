package com.company.loaf.routinescheduler.change;

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
import com.company.loaf.routinescheduler.utils.ActivityUtils;

public class EditActivity extends AppCompatActivity implements ChangeView {

    ChangePresenter mPresenter;
    TextView mFieldErrorText;
    EditText mNameInput, mIntervalInput, mDaysAgoInput;
    ProgressBar mProgressBar;

    String mOldName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_routine);

        mPresenter = new ChangePresenter(this, new ChangeInteractor());
        mFieldErrorText = findViewById(R.id.empty_field_error);
        mNameInput = findViewById(R.id.name_input);
        mIntervalInput = findViewById(R.id.interval_input);
        mDaysAgoInput = findViewById(R.id.days_ago_input);
        mProgressBar = findViewById(R.id.create_progressbar);


        Intent previousActivityIntent = getIntent();
        if(previousActivityIntent.hasExtra("name")){
            mOldName = previousActivityIntent.getStringExtra("name");
            mNameInput.setText(mOldName);
        }
        if(previousActivityIntent.hasExtra("interval")){
            mIntervalInput.setText(previousActivityIntent.getStringExtra("interval"));
        }

        findViewById(R.id.confirm_button).setOnClickListener(v -> changeRoutine() );
        findViewById(R.id.create_back_button).setOnClickListener(v -> onBack());

        ActivityUtils.setupActionBar(this, "Edit Routine");
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
    public void showDuplicateNameError() {
        Toast.makeText(this, "Error: name must be unique", Toast.LENGTH_LONG).show();
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
        Toast.makeText(this, "Successfully edited routine " + "'" + name + "'", Toast.LENGTH_LONG).show();
        onBack();
    }

    @Override
    public void onBack() {
        finish();
    }
    @Override
    public void changeRoutine() {
        mPresenter.editRoutine(mOldName, mNameInput.getText().toString(), mIntervalInput.getText().toString(), mDaysAgoInput.getText().toString(), this);
    }
}
