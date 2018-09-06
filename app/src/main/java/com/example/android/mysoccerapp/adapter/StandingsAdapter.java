package com.example.android.mysoccerapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.mysoccerapp.R;
import com.example.android.mysoccerapp.standings.StandingDatum;

import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.ViewHolder> {
    List<StandingDatum> standings;

    public StandingsAdapter(List<StandingDatum> standings) {
        this.standings = standings;
    }


    @NonNull
    @Override
    public StandingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_standing, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsAdapter.ViewHolder holder, int position) {
        StandingDatum standingsDatum = standings.get(position);
        holder.position.setText(String.valueOf(standingsDatum.getPosition()));
        holder.name.setText(standingsDatum.getTeamName());
        holder.matchesPlayed.setText(String.valueOf(standingsDatum.getOverall().getGamesPlayed()));
        holder.goalsFor.setText(String.valueOf(standingsDatum.getOverall().getGoalsScored()));
        holder.goalsAgainst.setText(String.valueOf(standingsDatum.getOverall().getGoalsAgainst()));
        holder.points.setText(String.valueOf(standingsDatum.getPoints()));

    }

    @Override
    public int getItemCount() {
        return standings.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{

        TextView position;
        TextView name;
        TextView matchesPlayed;
        TextView goalsFor;
        TextView goalsAgainst;
        TextView points;

        public ViewHolder(View itemView) {
            super(itemView);
            position = (TextView) itemView.findViewById(R.id.position);
            name = (TextView) itemView.findViewById(R.id.name);
            matchesPlayed = (TextView) itemView.findViewById(R.id.matches_played);
            goalsFor = (TextView) itemView.findViewById(R.id.goals_for);
            goalsAgainst = (TextView) itemView.findViewById(R.id.goals_against);
            points = (TextView) itemView.findViewById(R.id.points);
        }
    }
}
