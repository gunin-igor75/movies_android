package com.github.gunin_igor75.movies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.github.gunin_igor75.movies.R;
import com.github.gunin_igor75.movies.pojo.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    public static final String NEUTRAL = "Нейтральный";
    private static final String POSITIVE = "Позитивный";
    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewAuthor.setText(review.getAuthor());
        holder.textViewReview.setText(review.getReview());
        int idColor = getIdColor(review.getType(), holder);
        holder.linearLayoutReview.setBackgroundColor(idColor);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewViewHolder extends RecyclerView.ViewHolder{

        private final LinearLayout linearLayoutReview;
        private final TextView textViewAuthor;
        private final TextView textViewReview;


        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            linearLayoutReview = itemView.findViewById(R.id.linearLayoutReview);
        }
    }

    private int getIdColor(String type, ReviewViewHolder holder) {
        int idColor;
        switch (type) {
            case POSITIVE:
                idColor = android.R.color.holo_green_light;
                break;
            case NEUTRAL:
                idColor = android.R.color.holo_orange_light;
                break;
            default:
                idColor = android.R.color.holo_red_light;
        }
        return ContextCompat.getColor(holder.itemView.getContext(), idColor);
    }
}
