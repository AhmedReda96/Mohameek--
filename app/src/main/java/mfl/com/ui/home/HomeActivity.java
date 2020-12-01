package mfl.com.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import mfl.com.R;
import mfl.com.databinding.ActivityHomeBinding;
import mfl.com.helper.HomeSectionPagerAdapter;
import mfl.com.session.GeneralMethods;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private TabLayoutMediator tabLayoutMediator;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setLifecycleOwner(this);
        generalMethods = new GeneralMethods(this);
        generalMethods.setDirection(binding.mainLin);
        binding.viewPager.setAdapter(new HomeSectionPagerAdapter(this));
        binding.viewPager.setUserInputEnabled(false);
        tabLayoutMediator = new TabLayoutMediator(binding.tab, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setIcon(R.drawable.news_ic);

                        break;

                    case 1:
                        tab.setIcon(R.drawable.schedule_ic);
                        break;

                    case 2:
                        tab.setIcon(R.drawable.offer_ic);
                        break;
                    case 3:
                        tab.setIcon(R.drawable.person_ic);
                        break;
                    case 4:
                        tab.setIcon(R.drawable.setting_ic);
                        break;
                }

            }
        });

        tabLayoutMediator.attach();
    }
}