package mfl.com.ui.home.fragment.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import mfl.com.R;
import mfl.com.databinding.FragmentAddLocationBinding;
import mfl.com.databinding.FragmentNewsBinding;
import mfl.com.helper.NewsSectionPagerAdapter;
import mfl.com.helper.SignInSectionPagerAdapter;
import mfl.com.session.GeneralMethods;


public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;
    private TabLayoutMediator tabLayoutMediator;
    private GeneralMethods generalMethods;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }


    private void init() {
        generalMethods = new GeneralMethods(getActivity());
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        binding.newsViewPager.setAdapter(new NewsSectionPagerAdapter(getActivity()));

        tabLayoutMediator = new TabLayoutMediator(binding.tab, binding.newsViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setIcon(R.drawable.ic_new);

                        break;

                    case 1:
                        tab.setIcon(R.drawable.ic_favorite);
                        break;

                }

            }
        });

        tabLayoutMediator.attach();
    }
}
