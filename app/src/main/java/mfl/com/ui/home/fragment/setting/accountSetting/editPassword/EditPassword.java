package mfl.com.ui.home.fragment.setting.accountSetting.editPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivityEditPasswordBinding;
import mfl.com.session.GeneralMethods;

public class EditPassword extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditPasswordBinding binding;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_password);
        binding.setLifecycleOwner(this);
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);

        binding.saveBtn.setOnClickListener(this::onClick);
        binding.backBtn.setOnClickListener(this::onClick);


    }

    @Override
    public void onClick(View v) {
        if (binding.backBtn.equals(v)) {
            onBackPressed();
        }
        if (binding.saveBtn.equals(v)) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
