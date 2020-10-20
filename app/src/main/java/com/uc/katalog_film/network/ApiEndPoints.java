package com.uc.katalog_film.network;

import com.uc.katalog_film.model.response.CastResponse;
import com.uc.katalog_film.model.response.GenreResponse;
import com.uc.katalog_film.model.response.MovieResponse;
import com.uc.katalog_film.model.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoints {
    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<TvShowResponse> getTvShow(@Query("api_key") String apiKey);

//    @GET("{type}/{id}")
//    Call<GenreResponse> getGenres(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKay);
//
//    @GET("{type}/{id}/credits")
//    Call<CastResponse> getCasts(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);


//    @GET("movie/{movie_id}")
//    Call<MovieResponse> getDetailMovie(@Path("movie_id")int movieId, @Query("api_key") String apiKey);

}
