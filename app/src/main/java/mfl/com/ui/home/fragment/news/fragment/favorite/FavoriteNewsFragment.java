package mfl.com.ui.home.fragment.news.fragment.favorite;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.List;

import mfl.com.R;
import mfl.com.databinding.FragmentFavoriteNewsBinding;
import mfl.com.db.news.NewsEntity;
import mfl.com.helper.FavoriteNewsAdapter;
import mfl.com.helper.NewNewsAdapter;
import mfl.com.ui.home.fragment.news.fragment.New.NewNewsVM;


public class FavoriteNewsFragment extends Fragment {
    private static final String TAG = FavoriteNewsFragment.class.getSimpleName();

    private FragmentFavoriteNewsBinding binding;
    private FavoriteNewsVM viewModel;
    private FavoriteNewsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFavoriteNewsBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        viewModel = ViewModelProviders.of(getActivity()).get(FavoriteNewsVM.class);

        binding.favNewsRV.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
        binding.favNewsRV.setItemAnimator(new DefaultItemAnimator());
        binding.favNewsRV.setHasFixedSize(true);

        viewModel.initVM(getActivity());

        binding.swipe.setRefreshing(true);
        listenOnData();
        viewModel.getData();
    }

    private void listenOnData() {

        viewModel.favoriteNewsList.observe(getActivity(), new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(List<NewsEntity> newsEntities) {
                Log.d(TAG, "Mohameek onChanged:  " + String.valueOf(newsEntities.size()));
                adapter = new FavoriteNewsAdapter(getActivity(), newsEntities);
                adapter.notifyDataSetChanged();
                binding.favNewsRV.setAdapter(adapter);
                binding.swipe.setRefreshing(false);
                binding.swipe.setEnabled(false);

            }
        });


    }
}
