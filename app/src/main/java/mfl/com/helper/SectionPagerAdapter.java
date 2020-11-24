package mfl.com.helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import mfl.com.ui.start.signIn.signInSteps.addImage.AddImageFragment;
import mfl.com.ui.start.signIn.signInSteps.addLocation.AddLocationFragment;
import mfl.com.ui.start.signIn.signInSteps.changePassword.ChangePasswordFragment;
import mfl.com.ui.start.signIn.signInSteps.payment.PaymentFragment;

public class SectionPagerAdapter extends FragmentStateAdapter {
    public SectionPagerAdapter(FragmentActivity fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new ChangePasswordFragment();
            case 1:
                return new AddImageFragment();


            case 2:
                return new AddLocationFragment();

            case 3:
                return new PaymentFragment();
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
