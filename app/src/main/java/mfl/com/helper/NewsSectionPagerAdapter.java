package mfl.com.helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import mfl.com.ui.home.fragment.news.fragment.New.NewNewsFragment;
import mfl.com.ui.home.fragment.news.fragment.favorite.FavoriteNewsFragment;

public class NewsSectionPagerAdapter extends FragmentStateAdapter {
    public NewsSectionPagerAdapter(FragmentActivity fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new NewNewsFragment();
            case 1:
                return new FavoriteNewsFragment();
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
