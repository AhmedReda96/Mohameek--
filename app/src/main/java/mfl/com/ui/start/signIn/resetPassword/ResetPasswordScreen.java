package mfl.com.ui.start.signIn.resetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivityResetPasswordScreenBinding;
import mfl.com.session.GeneralMethods;

public class ResetPasswordScreen extends AppCompatActivity implements View.OnClickListener {

    private ActivityResetPasswordScreenBinding binding;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_screen);

        init();
    }

    private void init() {
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password_screen);
        binding.setLifecycleOwner(this);
        generalMethods.setDirection(binding.mainLin);


        binding.resetPassword.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (binding.backBtn.equals(v)) {

        }
        if (binding.resetPassword.equals(v)) {
            // startActivity(new Intent(this, ResetPasswordScreen.class));
        }
    }
}
