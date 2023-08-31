package com.github.gunin_igor75.movies.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.gunin_igor75.movies.R;
import com.github.gunin_igor75.movies.pojo.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Movie> movies = new ArrayList<>();

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.itemView.getContext())
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);
        double rating = movie.getRating().getKp();
        rating = Math.round(rating * 10.0) / 10.0;
        int backgroundId = getBackgroundId(rating);
        Drawable backGround = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewRating.setBackground(backGround);
        holder.textViewRating.setText(String.valueOf(rating));
    }

    private int getBackgroundId(double rating) {
        int id;
        if (rating < 5) {
            id = R.drawable.circle_red;
        } else if (rating > 7) {
            id = R.drawable.circle_green;
        } else {
            id = R.drawable.circle_orange;
        }
        return id;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private final ImageView  imageViewPoster;
        private final TextView textViewRating;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
