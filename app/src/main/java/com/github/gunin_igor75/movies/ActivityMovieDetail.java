package com.github.gunin_igor75.movies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.gunin_igor75.movies.adapter.ReviewAdapter;
import com.github.gunin_igor75.movies.adapter.TrailerAdapter;
import com.github.gunin_igor75.movies.pojo.Movie;
import com.github.gunin_igor75.movies.pojo.Review;
import com.github.gunin_igor75.movies.pojo.Trailer;

import java.util.List;

public class ActivityMovieDetail extends AppCompatActivity {

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewTrailers;
    private RecyclerView recyclerViewReviews;

    private MovieDetailViewModel viewModel;

    private static String MOVIE = "movie";

    private static String TAG = "ActivityMovieDetail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detal);
        init();
        TrailerAdapter trailerAdapter = new TrailerAdapter();
        recyclerViewTrailers.setAdapter(trailerAdapter);

        ReviewAdapter reviewAdapter = new ReviewAdapter();
        recyclerViewReviews.setAdapter(reviewAdapter);

        Intent intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra(MOVIE);
        int id = movie.getId();

        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);

        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        viewModel.loadTrailers(id);
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailerAdapter.setTrailers(trailers);
            }
        });

        trailerAdapter.setOnClickTrailerListener(new TrailerAdapter.OnClickTrailerListener() {
            @Override
            public void onClickTrailer(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });

        viewModel.loadReviews(id);
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewAdapter.setReviews(reviews);
            }
        });
    }

    public void init() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, ActivityMovieDetail.class);
        intent.putExtra(MOVIE, movie);
        return intent;
    }

}