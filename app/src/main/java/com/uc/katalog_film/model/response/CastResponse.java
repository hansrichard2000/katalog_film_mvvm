package com.uc.katalog_film.model.response;

import com.google.gson.annotations.SerializedName;
import com.uc.katalog_film.model.local.Cast;

import java.util.List;

public class CastResponse {
    @SerializedName("cast")
    private List<Cast> cast;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
