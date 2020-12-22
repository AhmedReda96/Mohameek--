package mfl.com.ui.home.fragment.offer.main;

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
import mfl.com.databinding.FragmentNewsBinding;
import mfl.com.databinding.FragmentOfferBinding;
import mfl.com.helper.NewsSectionPagerAdapter;
import mfl.com.helper.OfferSectionPagerAdapter;
import mfl.com.session.GeneralMethods;


public class OfferFragment extends Fragment {
    private TabLayoutMediator tabLayoutMediator;
    private GeneralMethods generalMethods;
    private FragmentOfferBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOfferBinding.inflate(inflater, container, false);
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
        binding.offerViewPager.setAdapter(new OfferSectionPagerAdapter(getActivity()));

        tabLayoutMediator = new TabLayoutMediator(binding.tab, binding.offerViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:

                        tab.setText(getActivity().getResources().getString(R.string.currentOffer));
                        break;

                    case 1:
                        tab.setText(getActivity().getResources().getString(R.string.myOffer));
                        break;

                }

            }
        });

        tabLayoutMediator.attach();
    }
}

