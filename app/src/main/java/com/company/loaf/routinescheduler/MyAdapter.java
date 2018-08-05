package com.company.loaf.routinescheduler;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.company.loaf.routinescheduler.analyze.AnalyzeInteractor;
import com.company.loaf.routinescheduler.analyze.AnalyzePresenter;
import com.company.loaf.routinescheduler.analyze.AnalyzeView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RoutineViewHolder>{

    final private ExpandableButtonClickedListener mListener;
    final private SpinnerView mSpinnerView;

    public interface ExpandableButtonClickedListener{
        void onDeleteButtonPressed(String name);
        void onEditButtonPressed(String name, String interval);
    }

    public interface SpinnerView{
        void generateYears(Spinner spinner);
        void generateMonths(Spinner spinner);
        void generateDays(Spinner spinner, String month, String year);
    }

    private Routine[] mRoutines;


    public MyAdapter(Routine[] routines, ExpandableButtonClickedListener listener, SpinnerView view){
        mRoutines = routines;
        mListener = listener;
        mSpinnerView = view;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.routine_list_item, viewGroup, false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder routineViewHolder, int i) {
        routineViewHolder.bind(mRoutines[i].getName(), mRoutines[i].getInterval());
    }

    @Override
    public int getItemCount() {
        return mRoutines.length;
    }

    class RoutineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, AdapterView.OnItemSelectedListener, AnalyzeView {

        AnalyzePresenter mPresenter;

        TextView mName, mInterval, mResult;
        LinearLayout mExpandableButtons;
        CardView mCardView;
        ImageView mArrowImage;
        Spinner mYearSpinner, mMonthSpinner, mDaySpinner;

        String mIntervalString;

        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            mPresenter = new AnalyzePresenter(this, new AnalyzeInteractor());

            mName = itemView.findViewById(R.id.routine_name);
            mInterval = itemView.findViewById(R.id.routine_interval);
            mResult = itemView.findViewById(R.id.result_text);
            mExpandableButtons = itemView.findViewById(R.id.expandable_buttons);
            mCardView = itemView.findViewById(R.id.routine_cardview);
            mYearSpinner = itemView.findViewById(R.id.year_spinner);
            mMonthSpinner = itemView.findViewById(R.id.month_spinner);
            mDaySpinner = itemView.findViewById(R.id.day_spinner);
            mArrowImage = itemView.findViewById(R.id.cardview_arrow);

            itemView.findViewById(R.id.routine_delete_button).setOnClickListener(v -> mListener.onDeleteButtonPressed(mName.getText().toString()));

            itemView.findViewById(R.id.routine_edit_button).setOnClickListener(v -> mListener.onEditButtonPressed(mName.getText().toString(), mIntervalString));

            mSpinnerView.generateYears(mYearSpinner);
            mSpinnerView.generateMonths(mMonthSpinner);
            mSpinnerView.generateDays(mDaySpinner, mMonthSpinner.getSelectedItem().toString(), mYearSpinner.getSelectedItem().toString());

            mYearSpinner.setOnItemSelectedListener(this);
            mMonthSpinner.setOnItemSelectedListener(this);
            mDaySpinner.setOnItemSelectedListener(this);

            mExpandableButtons.setVisibility(View.GONE);
            itemView.setOnClickListener(this);
        }

        void bind(String name, int interval){
            mName.setText(name);
            mInterval.setText(interval + " day(s)");
            mIntervalString = String.valueOf(interval);
        }

        @Override
        public void onClick(View view) {
            //TODO: try to add some animations if possible, current issue is that the retract animation looks awful
            //TODO: consider only allowing for one cardview to be expanded at a time
            int visibility = mExpandableButtons.getVisibility();
            if(visibility == View.VISIBLE){
                mArrowImage.setImageResource(R.drawable.ic_keyboard_arrow_down_black);
                mExpandableButtons.setVisibility(View.GONE);
            }else{
                mArrowImage.setImageResource(R.drawable.ic_keyboard_arrow_up_black);
                mExpandableButtons.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if(!adapterView.equals(mDaySpinner))
                mSpinnerView.generateDays(mDaySpinner, mMonthSpinner.getSelectedItem().toString(), mYearSpinner.getSelectedItem().toString());
            analyze();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

        @Override
        public void analyze() {
            mPresenter.analyze(mRoutines, mName.getText().toString(), mYearSpinner.getSelectedItem().toString(), mMonthSpinner.getSelectedItem().toString(), mDaySpinner.getSelectedItem().toString());
        }

        @Override
        public void displayResult(String result) {
            mResult.setText(result);
        }
    }
}
