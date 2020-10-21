package com.uc.katalog_film.ui.main.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.katalog_film.R;
import com.uc.katalog_film.model.local.Cast;
import com.uc.katalog_film.util.Constants;

import java.util.List;

public class DetailCastAdapter extends RecyclerView.Adapter<DetailCastAdapter.CardViewViewHolder>{
    private Context context;
    private List<Cast> casts;

    public DetailCastAdapter(Context context) {
        this.context = context;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_cast, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Cast cast = casts.get(position);
        Glide.with(context).load(Constants.BASE_IMAGE_URL + cast.getImg_url()).into(holder.img_cast);
        holder.cast_name.setText(cast.getName());
        holder.cast_role.setText(cast.getRole());
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_cast;
        TextView cast_name, cast_role;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cast = itemView.findViewById(R.id.img_cast);
            cast_name = itemView.findViewById(R.id.cast_name);
            cast_role = itemView.findViewById(R.id.cast_role);
        }
    }
}
