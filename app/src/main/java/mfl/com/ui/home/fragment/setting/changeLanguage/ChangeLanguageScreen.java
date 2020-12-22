package mfl.com.ui.home.fragment.setting.changeLanguage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivityChangeLanguageScreenBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.splash.SplashScreen;

public class ChangeLanguageScreen extends AppCompatActivity implements View.OnClickListener {

    private ActivityChangeLanguageScreenBinding binding;
    private GeneralMethods generalMethods;
    private StoreLanguageData storeLanguageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_language_screen);
        binding.setLifecycleOwner(this);

        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        storeLanguageData = new StoreLanguageData(this);

        setLanguage();

        binding.arabicBtn.setOnClickListener(this::onClick);
        binding.englishBtn.setOnClickListener(this::onClick);
        binding.arabicLin.setOnClickListener(this::onClick);
        binding.englishLin.setOnClickListener(this::onClick);
        binding.saveBtn.setOnClickListener(this::onClick);
        binding.backBtn.setOnClickListener(this::onClick);

    }

    private void setLanguage() {
        if (storeLanguageData.getAppLanguage().equals("ar")) {

            binding.arabicBtn.setChecked(true);

        } else {
            binding.englishBtn.setChecked(true);


        }
    }

    @Override
    public void onClick(View v) {
        if (binding.saveBtn.equals(v)) {
            changeAppLanguage();
        }
        if (binding.backBtn.equals(v)) {
            onBackPressed();
        }
        if (binding.arabicLin.equals(v)) {

            binding.arabicBtn.setChecked(true);
            binding.englishBtn.setChecked(false);

        }
        if (binding.englishLin.equals(v)) {
            binding.arabicBtn.setChecked(false);
            binding.englishBtn.setChecked(true);
        }
        if (binding.arabicBtn.equals(v)) {

            binding.arabicBtn.setChecked(true);
            binding.englishBtn.setChecked(false);

        }
        if (binding.englishBtn.equals(v)) {
            binding.arabicBtn.setChecked(false);
            binding.englishBtn.setChecked(true);
        }
    }

    private void changeAppLanguage() {
        if (binding.arabicBtn.isChecked()) {
            storeLanguageData.setAppLanguage("ar");
            startActivity(new Intent(this, SplashScreen.class));
            this.finish();
        } else {

            storeLanguageData.setAppLanguage("en");
            startActivity(new Intent(this, SplashScreen.class));
            this.finish();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
