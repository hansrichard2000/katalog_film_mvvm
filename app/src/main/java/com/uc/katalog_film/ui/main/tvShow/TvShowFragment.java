package com.uc.katalog_film.ui.main.tvShow;

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

import com.uc.katalog_film.R;
import com.uc.katalog_film.model.local.TvShow;
import com.uc.katalog_film.ui.main.movie.MovieAdapter;
import com.uc.katalog_film.ui.main.movie.MovieViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TvShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TvShowFragment extends Fragment {

    @BindView(R.id.rv_TvShow)
    RecyclerView rv_tvshow;

    TvShowAdapter tvShowAdapter;

    private TvShowViewModel viewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TvShowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TvShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TvShowFragment newInstance(String param1, String param2) {
        TvShowFragment fragment = new TvShowFragment();
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
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        tvShowAdapter = new TvShowAdapter(getContext());
        rv_tvshow.setLayoutManager(new LinearLayoutManager(getActivity()));


        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel.class);
        viewModel.getTvShowCollection().observe(requireActivity(), observeViewModel);
    }

    private Observer<List<TvShow>> observeViewModel = tvShows -> {
       if (tvShows != null){
           tvShowAdapter.setTvShows(tvShows);
           tvShowAdapter.notifyDataSetChanged();
           rv_tvshow.setAdapter(tvShowAdapter);
       }
    };
}