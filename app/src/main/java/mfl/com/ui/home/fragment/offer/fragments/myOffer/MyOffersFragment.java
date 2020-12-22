package mfl.com.ui.home.fragment.offer.fragments.myOffer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mfl.com.R;
import mfl.com.databinding.FragmentCurrentOfferBinding;
import mfl.com.databinding.FragmentMyOffersBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.home.fragment.offer.offerDetails.OfferDetailsScreen;

public class MyOffersFragment extends Fragment {

    private FragmentMyOffersBinding binding;
    private GeneralMethods generalMethods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyOffersBinding.inflate(inflater, container, false);
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
                startActivity(new Intent(getActivity(), OfferDetailsScreen.class));
            }
        });

    }
}
