package com.github.gunin_igor75.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.gunin_igor75.movies.adapter.MoviesAdapter;
import com.github.gunin_igor75.movies.pojo.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  MainViewModel viewModel;

    private RecyclerView recyclerviewMovies;

    private MoviesAdapter moviesAdapter;

    private ProgressBar progressBarLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        moviesAdapter = new MoviesAdapter();
        recyclerviewMovies.setAdapter(moviesAdapter);
        recyclerviewMovies.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBarLoading.setVisibility(View.VISIBLE);
                } else {
                    progressBarLoading.setVisibility(View.GONE);
                }
            }
        });

        moviesAdapter.setOnReachEndListener(() -> viewModel.loadMovies());

        moviesAdapter.setOnClickMovieListener(new MoviesAdapter.OnClickMovieListener() {
            @Override
            public void onClickMovie(Movie movie) {
                Intent intent = ActivityMovieDetail.newIntent(MainActivity.this, movie);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void init() {
        recyclerviewMovies = findViewById(R.id.recyclerviewMovies);
        progressBarLoading = findViewById(R.id.progressBarLoading);
    }

}