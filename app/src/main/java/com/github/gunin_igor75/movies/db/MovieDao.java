package com.github.gunin_igor75.movies.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.github.gunin_igor75.movies.pojo.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao{

    @Query("SELECT * FROM  movies")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movies WHERE id =:movieId ")
    LiveData<Movie> getMovieById(int movieId);

    @Insert
    Completable saveMovie(Movie movie);

    @Query("DELETE FROM movies WHERE id =:movieId")
    Completable deleteMovie(int movieId);
}
