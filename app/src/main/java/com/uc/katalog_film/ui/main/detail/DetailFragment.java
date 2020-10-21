package com.uc.katalog_film.ui.main.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.katalog_film.R;
import com.uc.katalog_film.model.Movie;
import com.uc.katalog_film.model.local.Cast;
import com.uc.katalog_film.model.local.Genre;
import com.uc.katalog_film.model.local.TvShow;
import com.uc.katalog_film.ui.MainActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.detail_cover)
    ImageView detail_cover;

    @BindView(R.id.detail_poster)
    ImageView detail_poster;

    @BindView(R.id.detail_mv_title)
    TextView detail_title;

    @BindView(R.id.detail_mv_popularity)
    TextView detail_popular;

    @BindView(R.id.detail_description)
    TextView detail_desc;

    @BindView(R.id.detail_genres)
    TextView detail_genres;

    @BindView(R.id.rv_cast)
    RecyclerView rv_cast;

    private Movie movie;
    private TvShow tvShow;
    private DetailViewModel detailViewModel;
    private DetailCastAdapter detailCastAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        detailViewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);

        rv_cast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        detailCastAdapter = new DetailCastAdapter(getActivity());

        if (getArguments() != null){
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvShow();

            if (movie != null){
                initMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getId_movie()));
            } else {
                initShow(tvShow);
                observeTvShowViewModel(Integer.parseInt(tvShow.getId_show()));
            }
        }
    }

    private void initMovie(Movie movie) {
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle(movie.getTitle());
        Glide.with(getActivity()).load(movie.getCover()).into(detail_cover);
        Glide.with(getActivity()).load(movie.getPoster()).into(detail_poster);
        detail_title.setText(movie.getTitle());
        detail_popular.setText(movie.getPopularity());
        detail_desc.setText(movie.getDescription());
    }

    private void observeMovieViewModel(int parseInt) {
        detailViewModel.getMovieGenre(parseInt).observe(requireActivity(), genres -> {
            if (genres != null){
                for (int i = 0; i < genres.size(); i++){
                    Genre genre = genres.get(i);
                    if (i < genres.size() - 1){
                        detail_genres.append(genre.getName()+ " | ");
                    } else {
                        detail_genres.append(genre.getName());
                    }
                }
            }
        });
        detailViewModel.getMovieCast(parseInt).observe(requireActivity(), casts -> {
            if (casts != null){
                detailCastAdapter.setCasts(casts);
                detailCastAdapter.notifyDataSetChanged();
                rv_cast.setAdapter(detailCastAdapter);
            }
        });
    }

    private void initShow(TvShow tvShow) {
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle(tvShow.getTitle());
        Glide.with(getActivity()).load(tvShow.getCover()).into(detail_cover);
        Glide.with(getActivity()).load(tvShow.getPoster()).into(detail_poster);
        detail_title.setText(tvShow.getTitle());
        detail_popular.setText(tvShow.getPopularity());
        detail_desc.setText(tvShow.getDescription());
    }

    private void observeTvShowViewModel(int parseInt) {
        detailViewModel.getMovieGenre(parseInt).observe(requireActivity(), genres -> {
            if (genres != null){
                for (int i = 0; i < genres.size(); i++){
                    Genre genre = genres.get(i);
                    if (i < genres.size() - 1){
                        detail_genres.append(genre.getName()+ " | ");
                    } else {
                        detail_genres.append(genre.getName());
                    }
                }
            }
        });
        detailViewModel.getMovieCast(parseInt).observe(requireActivity(), casts -> {
            if (casts != null){
                detailCastAdapter.setCasts(casts);
                detailCastAdapter.notifyDataSetChanged();
                rv_cast.setAdapter(detailCastAdapter);
            }
        });
    }




}