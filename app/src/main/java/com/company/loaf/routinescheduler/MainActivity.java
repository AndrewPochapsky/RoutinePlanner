package com.company.loaf.routinescheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{

    private TextView mErrorText, mResultText;
    private ProgressBar mProgressBar;
    private EditText mInterval, mDaysAgo;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorText = findViewById(R.id.error_text);
        mResultText = findViewById(R.id.result_text);
        mInterval = findViewById(R.id.interval_input);
        mDaysAgo = findViewById(R.id.days_ago_input);


        findViewById(R.id.submit_button).setOnClickListener(v -> validateInfo());

        mPresenter = new MainPresenter(this, new MainInteractor());

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showError() {
        mErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        mErrorText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void displayInfo(String info) {
        mResultText.setText(info);
    }

    @Override
    public void validateInfo() {
        mPresenter.validateInfo(mInterval.getText().toString(), mDaysAgo.getText().toString());
    }
}
