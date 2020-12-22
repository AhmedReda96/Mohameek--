package mfl.com.helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import mfl.com.ui.home.fragment.offer.fragments.currentOffer.CurrentOfferFragment;
import mfl.com.ui.home.fragment.offer.fragments.myOffer.MyOffersFragment;

public class OfferSectionPagerAdapter extends FragmentStateAdapter {
    public OfferSectionPagerAdapter(FragmentActivity fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new CurrentOfferFragment();
            case 1:
                return new MyOffersFragment();
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
