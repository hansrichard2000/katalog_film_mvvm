package com.uc.katalog_film.ui.main.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.katalog_film.model.local.Cast;
import com.uc.katalog_film.model.local.Genre;
import com.uc.katalog_film.repository.MovieRepository;
import com.uc.katalog_film.repository.TvShowRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private TvShowRepository tvShowRepository;

    public DetailViewModel() {
        movieRepository = MovieRepository.getInstance();
        tvShowRepository = TvShowRepository.getInstance();
    }

    public LiveData<List<Genre>> getMovieGenre(int id){
        return movieRepository.getGenres(id);
    }

    public LiveData<List<Genre>> getTvShowGenre(int id){
        return tvShowRepository.getGenres(id);
    }

    public LiveData<List<Cast>> getMovieCast(int id){
        return movieRepository.getCasts(id);
    }

    public LiveData<List<Cast>> getTvShowCast(int id){
        return tvShowRepository.getCasts(id);
    }
}