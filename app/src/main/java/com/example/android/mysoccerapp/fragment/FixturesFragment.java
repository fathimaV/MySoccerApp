package com.example.android.mysoccerapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.mysoccerapp.R;
import com.example.android.mysoccerapp.adapter.FixturesAdapter;
import com.example.android.mysoccerapp.app.Api;
import com.example.android.mysoccerapp.app.App;
import com.example.android.mysoccerapp.model.fixtures.FixtureDatum;
import com.example.android.mysoccerapp.model.fixtures.FixturesResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FixturesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FixturesFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Api api;
    RecyclerView recyclerView;


    public FixturesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FixturesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FixturesFragment newInstance(String param1, String param2) {
        FixturesFragment fragment = new FixturesFragment();
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
        View view = inflater.inflate(R.layout.fragment_fixtures, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment
        api = App.getApi();
        getFixtures();
        return view;
    }

    private void getFixtures(){
        final int roundId = 129121;

        String include = "fixtures.localTeam,fixtures.visitorTeam";
        api.getRoundWithFixtures(roundId, include).enqueue(new Callback<FixturesResult>() {
            @Override
            public void onResponse(Call<FixturesResult> call, Response<FixturesResult> response) {
                FixturesResult result = response.body();
                showFixtures(result.getData().getFixtures().getData());
            }

            @Override
            public void onFailure(Call<FixturesResult> call, Throwable t) {

            }
        });

    }

    private void showFixtures(List<FixtureDatum> fixtures) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new FixturesAdapter(fixtures));
    }


}
