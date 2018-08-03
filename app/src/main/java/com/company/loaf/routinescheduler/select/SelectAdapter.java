package com.company.loaf.routinescheduler.select;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.company.loaf.routinescheduler.R;
import com.company.loaf.routinescheduler.Routine;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.RoutineViewHolder> {

    Routine[] mRoutines;

    public SelectAdapter(Routine[] routines){
        mRoutines = routines;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.routine_list_item, viewGroup, false);
        return new RoutineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder routineViewHolder, int i) {
        routineViewHolder.bind(mRoutines[i].getName());
    }

    @Override
    public int getItemCount() {
        return mRoutines.length;
    }

    class RoutineViewHolder extends RecyclerView.ViewHolder{

        TextView mName;

        public RoutineViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.routine_name);
        }

        void bind(String name){
            mName.setText(name);
        }
    }
}
