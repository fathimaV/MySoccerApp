package com.example.android.mysoccerapp.app;

import com.example.android.mysoccerapp.model.fixtures.FixturesResult;
import com.example.android.mysoccerapp.model.topScorers.TopscorersResult;
import com.example.android.mysoccerapp.standings.StandingsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("standings/season/{seasonId}")
    Call<StandingsResult> getStandings(@Path("seasonId") int seasonId);

    @GET("rounds/{roundId}")
    Call<FixturesResult> getRoundWithFixtures(@Path("roundId") int roundId, @Query("include") String include);

    @GET("topscorers/season/{seasonId}")
    Call<TopscorersResult> getTopScorers(@Path("seasonId") int seasonId, @Query("include") String include);
}
