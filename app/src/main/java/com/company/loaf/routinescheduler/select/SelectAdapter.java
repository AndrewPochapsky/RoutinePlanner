package com.company.loaf.routinescheduler.select;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.company.loaf.routinescheduler.R;
import com.company.loaf.routinescheduler.Routine;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.RoutineViewHolder> {

    final private ExpandableButtonClickedListener mListener;

    public interface ExpandableButtonClickedListener{
        void onDeleteButtonPressed(String name);
    }

    private Routine[] mRoutines;


    public SelectAdapter(Routine[] routines, ExpandableButtonClickedListener listener){
        mRoutines = routines;
        mListener = listener;
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

    class RoutineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mName, mInterval;
        LinearLayout mExpandableButtons;
        CardView mCardView;
        ImageView mArrowImage;

        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.routine_name);
            mInterval = itemView.findViewById(R.id.routine_interval);
            mExpandableButtons = itemView.findViewById(R.id.expandable_buttons);
            mCardView = itemView.findViewById(R.id.routine_cardview);
            mArrowImage = itemView.findViewById(R.id.cardview_arrow);

            itemView.findViewById(R.id.routine_delete_button).setOnClickListener(v -> mListener.onDeleteButtonPressed(mName.getText().toString()));

            mExpandableButtons.setVisibility(View.GONE);
            itemView.setOnClickListener(this);
        }

        void bind(String name, int interval){
            mName.setText(name);
            mInterval.setText(interval + " day(s)");
        }

        @Override
        public void onClick(View view) {
            //TODO: try to add some animations if possible, current issue is that the retract animation looks awful
            //TODO: consider only allowing for one cardview to be expanded at a time
            int visibility = mExpandableButtons.getVisibility();
            if(visibility == View.VISIBLE){
                mArrowImage.setImageResource(R.drawable.ic_keyboard_arrow_up_black);
                mExpandableButtons.setVisibility(View.GONE);
            }else{
                mArrowImage.setImageResource(R.drawable.ic_keyboard_arrow_down_black);
                mExpandableButtons.setVisibility(View.VISIBLE);
            }
        }
    }
}
