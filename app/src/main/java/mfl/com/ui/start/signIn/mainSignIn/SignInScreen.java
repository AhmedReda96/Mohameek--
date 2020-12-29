package mfl.com.ui.start.signIn.mainSignIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import mfl.com.R;
import mfl.com.databinding.ActivitySignInScreenBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.start.signIn.forgetPassword.ForgetPasswordScreen;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;
import mfl.com.ui.start.signUp.SignUpScreen;
import mfl.com.ui.start.signUp.SignUpScreenVM;

public class SignInScreen extends AppCompatActivity implements View.OnClickListener {

    private ActivitySignInScreenBinding binding;
    private StoreLanguageData storeLanguageData;
    private GeneralMethods generalMethods;
    private boolean doubleBackToExitPressedOnce = false;
    private SignInScreenVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        init();

    }

    private void init() {
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in_screen);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(SignInScreenVM.class);
        viewModel.initVM(this);

        binding.signUpBtn.setPaintFlags(binding.signUpBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        storeLanguageData = new StoreLanguageData(this);
        binding.languageBtn.setOnClickListener(this);
        binding.signInBtn.setOnClickListener(this);
        binding.forgetPassword.setOnClickListener(this);
        binding.signUpBtn.setOnClickListener(this);


        generalMethods.setDirection(binding.mainLin);
        checkLanguage();

        listenerOnLiveData();
    }


    private void listenerOnLiveData() {
        viewModel.resultLD.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                binding.error.setVisibility(View.VISIBLE);
                switch (result) {
                    case "invalid id":
                        binding.error.setText(getResources().getString(R.string.invalidId));
                        break;
                    case "invalid password":
                        binding.error.setText(getResources().getString(R.string.invalidPassword));
                        break;
                    case "invalid idOrPass":
                        binding.error.setText(getResources().getString(R.string.invalidIdOrPassword));
                        break;
                    case "noInternetConnection":
                        binding.error.setText(getResources().getString(R.string.noInternetConnection));
                        break;

                }
            }
        });


    }

    private void checkLanguage() {
        if (storeLanguageData.getAppLanguage().equals("en")) {
            binding.languageBtn.setBackgroundResource(R.mipmap.ar_btn_ic);
        }
        if (storeLanguageData.getAppLanguage().equals("ar")) {
            binding.languageBtn.setBackgroundResource(R.mipmap.en_btn_ic);
        }
    }

    @Override
    public void onClick(View view) {
        if (binding.forgetPassword.equals(view)) {

            startActivity(new Intent(this, ForgetPasswordScreen.class));
        }

        if (binding.signInBtn.equals(view)) {
            binding.error.setText("");
            viewModel.checkData(binding.id.getText().toString().trim(), binding.password.getText().toString().trim());
        }
        if (binding.signUpBtn.equals(view)) {

            startActivity(new Intent(this, SignUpScreen.class));
        }
        if (binding.languageBtn.equals(view)) {
            if (storeLanguageData.getAppLanguage().equals("ar")) {
                binding.languageBtn.setBackgroundResource(R.mipmap.ar_btn_ic);
                storeLanguageData.setAppLanguage("en");
                generalMethods.changeLanguage();
                generalMethods.setDirection(binding.mainLin);
                updateActivity();


            } else {
                binding.languageBtn.setBackgroundResource(R.mipmap.en_btn_ic);
                storeLanguageData.setAppLanguage("ar");
                generalMethods.changeLanguage();
                generalMethods.setDirection(binding.mainLin);
                updateActivity();

            }

        }
    }

    private void updateActivity() {
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }


    @Override
    public void onBackPressed() {


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            this.finishAffinity();

            return;
        }

        this.doubleBackToExitPressedOnce = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
