package com.uc.katalog_film.ui.main.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.katalog_film.R;
import com.uc.katalog_film.model.Movie;
import com.uc.katalog_film.util.Constants;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder> {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_movie, parent, false);
        return new MovieAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(context).load(movie.getCover()).centerCrop().into(holder.mvImage);
        holder.title.setText(movie.getTitle());
        holder.popularity.setText(movie.getPopularity());
        holder.date.setText(movie.getReleaseDate());
        holder.itemView.setOnClickListener(view -> {
            MovieFragmentDirections.ActionMovieFragmentToDetailFragment action = MovieFragmentDirections.actionMovieFragmentToDetailFragment(movie, null);
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }


    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView mvImage;
        TextView title, popularity, date;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            mvImage = itemView.findViewById(R.id.mv_picture);
            title = itemView.findViewById(R.id.mv_title);
            popularity = itemView.findViewById(R.id.mv_popularity);
            date = itemView.findViewById(R.id.mv_date);
        }
    }
}
