package com.github.gunin_igor75.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.gunin_igor75.movies.db.MovieDao;
import com.github.gunin_igor75.movies.db.MovieDataBase;
import com.github.gunin_igor75.movies.pojo.Movie;
import com.github.gunin_igor75.movies.pojo.Review;
import com.github.gunin_igor75.movies.pojo.ReviewResponse;
import com.github.gunin_igor75.movies.pojo.Trailer;
import com.github.gunin_igor75.movies.service.ApiFactory;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();

    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();

    private final MovieDao movieDao;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final String TAG = "MovieDetailViewModel";

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDataBase.getInstance(application).movieDao();
    }

    public LiveData<Movie> getFavoriteMovie(int movieId) {
        return movieDao.getMovieById(movieId);
    }

    public void loadTrailers(int id) {
        Disposable disposable = ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(trailersResponse -> trailersResponse.getVideos().getTrailers())
                .subscribe(trailers::setValue,
                        throwable -> Log.d(TAG, "Error load trailers " + throwable.toString()));
        compositeDisposable.add(disposable);
    }

    public void loadReviews(int id) {
        Disposable disposable = ApiFactory.apiService.loadReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ReviewResponse::getReviews)
                .subscribe(reviews::setValue,
                        throwable -> Log.e(TAG, "Error load reviews " + throwable.toString()));
        compositeDisposable.add(disposable);
    }

    public void saveMovie(Movie movie) {
        Disposable disposable = movieDao.saveMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.i(TAG, "Save movie: " + movie),
                        throwable -> Log.e(TAG, "Error save movie " + throwable.toString()));
        compositeDisposable.add(disposable);
    }

    public void deleteMovie(int movieId) {
        Disposable disposable = movieDao.deleteMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.i(TAG, "Delete movie: " + movieId),
                        throwable -> Log.e(TAG, "Error delete movie " + throwable.toString()));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
