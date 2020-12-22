package mfl.com.ui.start.signIn.signInSteps.stepsHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivitySignInStepsHomeBinding;
import mfl.com.helper.SignInSectionPagerAdapter;
import mfl.com.session.GeneralMethods;

public class SignInStepsHome extends AppCompatActivity implements View.OnClickListener {
    private ActivitySignInStepsHomeBinding binding;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_steps_home);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in_steps_home);
        binding.setLifecycleOwner(this);

        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        binding.backBtn.setOnClickListener(this::onClick);


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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
