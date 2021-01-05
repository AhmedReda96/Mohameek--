package mfl.com.ui.start.signIn.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivityForgetPasswordScreenBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.start.signIn.resetPassword.ResetPasswordScreen;

public class ForgetPasswordScreen extends AppCompatActivity implements View.OnClickListener {
    private ActivityForgetPasswordScreenBinding binding;
    private GeneralMethods generalMethods;
    private ForgetPasswordVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_screen);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password_screen);
        binding.setLifecycleOwner(this);

        binding.forgetPasswordBtn.setOnClickListener(this::onClick);
        binding.backBtn.setOnClickListener(this::onClick);

        viewModel = ViewModelProviders.of(this).get(ForgetPasswordVM.class);
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        viewModel.initVM(this);
        listenerOnLiveData();

    }

    private void listenerOnLiveData() {

        viewModel.resultLD.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                binding.error.setVisibility(View.VISIBLE);
                switch (result) {
                    case "invalid Phone":
                        binding.error.setText(getResources().getString(R.string.invalidId));
                        break;
                    case "noInternetConnection":
                        binding.error.setText(getResources().getString(R.string.noInternetConnection));
                        break;
                    case "error":
                        binding.error.setText("");
                        break;
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (binding.backBtn.equals(v)) {
            onBackPressed();
        }
        if (binding.forgetPasswordBtn.equals(v)) {
            startActivity(new Intent(this, ResetPasswordScreen.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
