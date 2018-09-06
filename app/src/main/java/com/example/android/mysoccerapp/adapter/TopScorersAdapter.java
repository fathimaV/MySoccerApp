package com.example.android.mysoccerapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.mysoccerapp.R;
import com.example.android.mysoccerapp.model.topScorers.GoalscorerDatum;

import java.util.List;

public class TopScorersAdapter extends RecyclerView.Adapter<TopScorersAdapter.ViewHolder> {

    List<GoalscorerDatum> goalscorers;

    public TopScorersAdapter(List<GoalscorerDatum> goalscorers) {
        this.goalscorers = goalscorers;
    }


    @NonNull
    @Override
    public TopScorersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_topscorers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopScorersAdapter.ViewHolder holder, int position) {
        GoalscorerDatum goalscorerDatum = goalscorers.get(position);
        holder.position.setText(String.valueOf(goalscorerDatum.getPosition()));
        holder.name.setText(goalscorerDatum.getPlayer().getData().getCommonName());
        holder.goals.setText(String.valueOf(goalscorerDatum.getGoals()));
    }

    @Override
    public int getItemCount() {
        return goalscorers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView position;
        TextView name;
        TextView goals;
        public ViewHolder(View view) {
            super(view);
            position = (TextView) view.findViewById(R.id.position);
            name = (TextView) view.findViewById(R.id.name);
            goals = (TextView) view.findViewById(R.id.goals);
        }
    }
}
