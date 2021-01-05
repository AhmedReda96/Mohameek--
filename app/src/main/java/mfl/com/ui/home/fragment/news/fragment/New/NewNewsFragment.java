package mfl.com.ui.home.fragment.news.fragment.New;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import mfl.com.R;
import mfl.com.databinding.FragmentNewNewsBinding;
import mfl.com.helper.NewNewsAdapter;
import mfl.com.pojo.news.NewNewsList;

public class NewNewsFragment extends Fragment {
    private FragmentNewNewsBinding binding;
    private NewNewsVM viewModel;
    private NewNewsAdapter adapter;
    private static int PAGE = 1;
    private List<NewNewsList> lists = new ArrayList<>();
    private boolean firstTime = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewNewsBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        viewModel = ViewModelProviders.of(getActivity()).get(NewNewsVM.class);

        binding.newNewsRV.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));
        binding.newNewsRV.setItemAnimator(new DefaultItemAnimator());
        binding.newNewsRV.setHasFixedSize(true);

        viewModel.initVM(getActivity());

        binding.swipe.setRefreshing(true);
        viewModel.checkConnection();
        listenerOnLiveData();
        listenerOnListOfData();


        binding.scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (binding.scroll.getChildAt(0).getBottom() <= (binding.scroll.getHeight() + binding.scroll.getScrollY())) {
                    viewModel.checkConnection();

                }
            }
        });


        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lists.clear();
                PAGE = 1;
                viewModel.checkConnection();
            }
        });


    }

    private void listenerOnListOfData() {
        viewModel.newsList.observe(getActivity(), new Observer<List<NewNewsList>>() {
            @Override
            public void onChanged(List<NewNewsList> newNewsLists) {
                lists.addAll(newNewsLists);
                adapter = new NewNewsAdapter(getActivity(), lists);
                adapter.notifyDataSetChanged();
                binding.newNewsRV.setAdapter(adapter);
                binding.progress.setVisibility(View.GONE);
                binding.swipe.setRefreshing(false);
                firstTime = false;

            }
        });


    }


    private void listenerOnLiveData() {
        viewModel.resultLD.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String result) {
                switch (result) {
                    case "InternetConnection":
                        binding.noInternetLin.setVisibility(View.GONE);

                        if (firstTime) {
                            binding.progress.setVisibility(View.GONE);
                            viewModel.getData(PAGE, binding.progress);

                        } else {
                            PAGE++;
                            viewModel.getData(PAGE, binding.progress);
                            binding.progress.setVisibility(View.VISIBLE);
                        }
                        break;
                    case "noInternetConnection":
                        binding.progress.setVisibility(View.GONE);
                        binding.noInternetLin.setVisibility(View.VISIBLE);
                        binding.swipe.setRefreshing(false);
                        break;
                }
            }
        });
    }



}
