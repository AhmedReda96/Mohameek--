package mfl.com.ui.start.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivitySignUpScreenBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.home.HomeActivity;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {
    private ActivitySignUpScreenBinding binding;
    private StoreLanguageData storeLanguageData;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        storeLanguageData = new StoreLanguageData(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_screen);
        binding.setLifecycleOwner(this);

        generalMethods.setDirection(binding.mainLin);
        checkLanguage();

        binding.signUpBtn.setOnClickListener(this::onClick);
    }

    private void checkLanguage() {
        if (storeLanguageData.getAppLanguage().equals("en")) {
            binding.joinUsImg.setImageResource(R.drawable.en_subscribe_ic);
        }
        if (storeLanguageData.getAppLanguage().equals("ar")) {
            binding.joinUsImg.setImageResource(R.drawable.ar_subscribe_ic);
        }
    }

    @Override
    public void onClick(View v) {
        if (binding.signUpBtn.equals(v)) {
            startActivity(new Intent(this, HomeActivity.class));

        }
    }
}
