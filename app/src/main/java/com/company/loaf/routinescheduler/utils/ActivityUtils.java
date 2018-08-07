package com.company.loaf.routinescheduler.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.company.loaf.routinescheduler.R;

public class ActivityUtils {

    public static void setupActionBar(@NonNull AppCompatActivity activity, String title){
        activity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        activity.getSupportActionBar().setCustomView(R.layout.custom_actionbar);

        View v = activity.getSupportActionBar().getCustomView();
        TextView titleTxtView = v.findViewById(R.id.action_bar_title);
        titleTxtView.setText(title);
    }

}
