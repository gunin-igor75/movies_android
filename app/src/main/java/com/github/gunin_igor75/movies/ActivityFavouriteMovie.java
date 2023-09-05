package com.github.gunin_igor75.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.gunin_igor75.movies.adapter.MoviesAdapter;
import com.github.gunin_igor75.movies.pojo.Movie;

public class ActivityFavouriteMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movie);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFavoriteMovie);
        MoviesAdapter adapter = new MoviesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        FavouriteMovieViewModel viewModel =
                new ViewModelProvider(this).get(FavouriteMovieViewModel.class);
        viewModel.getFavouriteMovies().observe(this, adapter::setMovies);

        adapter.setOnClickMovieListener(new MoviesAdapter.OnClickMovieListener() {
            @Override
            public void onClickMovie(Movie movie) {
                Intent intent = ActivityMovieDetail.newIntent(
                        ActivityFavouriteMovie.this,
                        movie);
                startActivity(intent);
            }
        });
    }

    public static Intent newInstant(Context context) {
        return new Intent(context, ActivityFavouriteMovie.class);
    }
}