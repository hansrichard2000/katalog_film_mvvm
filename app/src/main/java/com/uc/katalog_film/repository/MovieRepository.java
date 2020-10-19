package com.uc.katalog_film.repository;

import android.util.Log;

import androidx.annotation.LongDef;
import androidx.lifecycle.MutableLiveData;

import com.uc.katalog_film.model.Movie;
import com.uc.katalog_film.model.MovieResponse;
import com.uc.katalog_film.network.ApiEndPoints;
import com.uc.katalog_film.network.RetrofitService;
import com.uc.katalog_film.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {
    private static MovieRepository movieRepository;
    private RetrofitService service;
    private static final String TAG = "MovieRepository";

    private MovieRepository(){
        service = RetrofitService.getInstance();
    }

    public static MovieRepository getInstance(){
        if (movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MutableLiveData<List<Movie>> getMovieCollection(){
        MutableLiveData<List<Movie>> listMovie = new MutableLiveData<>();

        service.getMovies().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listMovie.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listMovie;
    }
}
