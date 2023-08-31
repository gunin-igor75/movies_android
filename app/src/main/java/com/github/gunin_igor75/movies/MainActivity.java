package com.github.gunin_igor75.movies;

import android.os.Bundle;
import android.util.Log;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerviewMovies = findViewById(R.id.recyclerviewMovies);
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

        viewModel.loadMovies();
    }

}