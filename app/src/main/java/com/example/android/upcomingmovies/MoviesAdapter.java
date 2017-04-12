package com.example.android.upcomingmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.upcomingmovies.Model.Movie;

import java.util.List;

/**
 * Created by S on 04/04/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private Context mContext;
    private List<Movie> mMovieList;
    private ItemClickListener clickListener;


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_title, tv_releaseDate, tv_isAdult;
        public ImageView img_thumbnail;

        public MovieViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_releaseDate = (TextView) view.findViewById(R.id.tv_releaseDate);
            tv_isAdult = (TextView) view.findViewById(R.id.tv_isAdult);
            img_thumbnail = (ImageView) view.findViewById(R.id.img_thumbnail);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (clickListener != null) clickListener.onClick(v, position);
        }
    }

    public MoviesAdapter(Context mContext, List<Movie> movieList) {
        this.mContext = mContext;
        this.mMovieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);

        holder.tv_title.setText(movie.getmTitle());
        holder.tv_releaseDate.setText(movie.getmReleaseDate());
        holder.tv_isAdult.setText(movie.ismIsAdult() ? "(A)" : "(U/A)");

        Glide.with(mContext).load("http://image.tmdb.org/t/p/w500/" + movie.getmPosterPath()).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }

}
