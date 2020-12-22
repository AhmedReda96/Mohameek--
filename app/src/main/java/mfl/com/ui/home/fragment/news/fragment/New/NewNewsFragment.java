package mfl.com.ui.home.fragment.news.fragment.New;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mfl.com.R;
import mfl.com.databinding.FragmentNewNewsBinding;
import mfl.com.databinding.FragmentNewsBinding;
import mfl.com.databinding.NewsItemModelBinding;
import mfl.com.ui.home.fragment.news.details.NewsDetailsScreen;

public class NewNewsFragment extends Fragment {
    private FragmentNewNewsBinding binding;


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
        binding.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewsDetailsScreen.class
                ));
            }
        });
    }
}
