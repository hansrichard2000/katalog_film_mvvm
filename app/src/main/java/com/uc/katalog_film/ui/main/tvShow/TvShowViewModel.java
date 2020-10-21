package com.uc.katalog_film.ui.main.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.katalog_film.model.local.TvShow;
import com.uc.katalog_film.repository.TvShowRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    private final TvShowRepository repository;

    public TvShowViewModel(){
        repository = TvShowRepository.getInstance();
    }

    public LiveData<List<TvShow>> getTvShowCollection(){
        return repository.getTvShowCollection();
    }
}
