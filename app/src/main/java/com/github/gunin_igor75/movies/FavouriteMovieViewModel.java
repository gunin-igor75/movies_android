package com.github.gunin_igor75.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.gunin_igor75.movies.db.MovieDao;
import com.github.gunin_igor75.movies.db.MovieDataBase;
import com.github.gunin_igor75.movies.pojo.Movie;

import java.util.List;

public class FavouriteMovieViewModel extends AndroidViewModel {

    private final MovieDao movieDao;

    public FavouriteMovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDataBase.getInstance(application).movieDao();
    }


    public LiveData<List<Movie>> getFavouriteMovies() {
        return movieDao.getAllMovies();
    }
}
