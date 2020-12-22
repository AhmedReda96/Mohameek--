package mfl.com.ui.home.fragment.offer.fragments.currentOffer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;

import mfl.com.R;
import mfl.com.databinding.FragmentCurrentOfferBinding;
import mfl.com.databinding.FragmentNewNewsBinding;
import mfl.com.databinding.FragmentOfferBinding;
import mfl.com.helper.OfferSectionPagerAdapter;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.home.fragment.offer.addOffer.AddOfferScreen;
import mfl.com.ui.home.fragment.offer.offerDetails.OfferDetailsScreen;

public class CurrentOfferFragment extends Fragment {
    private GeneralMethods generalMethods;
    private FragmentCurrentOfferBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrentOfferBinding.inflate(inflater, container, false);
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


        binding.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddOfferScreen.class));
            }
        });

        //  binding.currentOfferRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }
}