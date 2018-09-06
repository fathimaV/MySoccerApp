package com.example.android.mysoccerapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.mysoccerapp.R;
import com.example.android.mysoccerapp.adapter.TopScorersAdapter;
import com.example.android.mysoccerapp.app.Api;
import com.example.android.mysoccerapp.app.App;
import com.example.android.mysoccerapp.model.topScorers.GoalscorerDatum;
import com.example.android.mysoccerapp.model.topScorers.TopscorersResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopscorersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopscorersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Api api;
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public TopscorersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopscorersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopscorersFragment newInstance(String param1, String param2) {
        TopscorersFragment fragment = new TopscorersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topscorers, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        getTopScorers();
        return view;
    }

    private void getTopScorers(){
         int SEASON_ID = 7953;
         String INCLUDE = "goalscorers.player";

        api = App.getApi();
        api.getTopScorers(SEASON_ID, INCLUDE).enqueue(new Callback<TopscorersResult>() {
            @Override
            public void onResponse(Call<TopscorersResult> call, Response<TopscorersResult> response) {
                TopscorersResult result = response.body();
                showTopScorers(result.getData().getGoalscorers().getData());

            }

            @Override
            public void onFailure(Call<TopscorersResult> call, Throwable t) {

            }
        });
    }

    private void showTopScorers(List<GoalscorerDatum> goalscorers){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TopScorersAdapter adapter = new TopScorersAdapter(goalscorers);
        recyclerView.setAdapter(adapter);

    }

}
