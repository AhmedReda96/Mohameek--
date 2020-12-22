package mfl.com.helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import mfl.com.ui.home.fragment.news.NewsFragment;
import mfl.com.ui.home.fragment.offer.main.OfferFragment;
import mfl.com.ui.home.fragment.profile.ProfileFragment;
import mfl.com.ui.home.fragment.schedule.mainSchedule.ScheduleFragment;
import mfl.com.ui.home.fragment.setting.mainSetting.SettingFragment;

public class HomeSectionPagerAdapter extends FragmentStateAdapter {
    public HomeSectionPagerAdapter(FragmentActivity fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new NewsFragment();
            case 1:
                return new ScheduleFragment();
            case 2:
                return new OfferFragment();
            case 3:
                return new ProfileFragment();
            case 4:
                return new SettingFragment();
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
