package com.github.gunin_igor75.movies.service;

import com.github.gunin_igor75.movies.pojo.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    String TOKEN = "F2A1G1M-2724ZFS-K1834H0-7GFQX84";

    @Headers({
            "Accept: application/json",
            "X-API-KEY: " + TOKEN
    })
    @GET("movie?field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);
}
