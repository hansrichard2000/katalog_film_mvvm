package com.uc.katalog_film.ui.main.tvShow;

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
import com.uc.katalog_film.model.local.TvShow;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewViewHolder>{

    private Context context;
    private List<TvShow> tvShows;

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<TvShow> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_movie, parent, false);
        return new TvShowAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        TvShow tvShow = tvShows.get(position);
        Glide.with(context).load(tvShow.getCover()).centerCrop().into(holder.mvImage);
        holder.title.setText(tvShow.getTitle());
        holder.popularity.setText(tvShow.getPopularity());
        holder.date.setText(tvShow.getReleaseDate());
        holder.score.setText(tvShow.getScore());
        holder.itemView.setOnClickListener(view -> {
            TvShowFragmentDirections.ActionTvShowFragmentToDetailFragment action = TvShowFragmentDirections.actionTvShowFragmentToDetailFragment(null, tvShow);
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return getTvShows().size();
    }


    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView mvImage;
        TextView title, popularity, date, score;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            mvImage = itemView.findViewById(R.id.mv_picture);
            title = itemView.findViewById(R.id.mv_title);
            popularity = itemView.findViewById(R.id.mv_popularity);
            date = itemView.findViewById(R.id.mv_date);
            score = itemView.findViewById(R.id.mv_score);
        }
    }
}
