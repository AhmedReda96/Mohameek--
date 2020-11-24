package mfl.com.ui.start.signIn.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivityForgetPasswordScreenBinding;
import mfl.com.ui.start.signIn.resetPassword.ResetPasswordScreen;

public class ForgetPasswordScreen extends AppCompatActivity implements View.OnClickListener{
    private ActivityForgetPasswordScreenBinding binding;

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
    }

    @Override
    public void onClick(View v) {
        if (binding.backBtn.equals(v)) {

        }
        if (binding.forgetPasswordBtn.equals(v)) {
            startActivity(new Intent(this, ResetPasswordScreen.class));
        }
    }
}
