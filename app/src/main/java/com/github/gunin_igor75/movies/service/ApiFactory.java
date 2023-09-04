package com.github.gunin_igor75.movies.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava3.*;

public class ApiFactory {

    private static final String BASE_URL = "https://api.kinopoisk.dev/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();
    public static final ApiService apiService = retrofit.create(ApiService.class);
}
