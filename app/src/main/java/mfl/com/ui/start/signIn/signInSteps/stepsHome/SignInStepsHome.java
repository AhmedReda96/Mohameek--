package mfl.com.ui.start.signIn.signInSteps.stepsHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivitySignInStepsHomeBinding;
import mfl.com.helper.SignInSectionPagerAdapter;

public class SignInStepsHome extends AppCompatActivity implements View.OnClickListener {
    private ActivitySignInStepsHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_steps_home);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in_steps_home);
        binding.setLifecycleOwner(this);

        binding.viewPager.setAdapter(new SignInSectionPagerAdapter(this));

        binding.viewPager.setUserInputEnabled(false);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                binding.stepView.go(position, true);
            }
        });


    }

    public void selectIndex(int newIndex) {
        binding.viewPager.setCurrentItem(newIndex);
    }

    @Override
    public void onClick(View v) {
        if (binding.backBtn.equals(v)) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }
}
