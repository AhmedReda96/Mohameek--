package mfl.com.ui.home.fragment.setting.accountSetting.editStatus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.suke.widget.SwitchButton;

import mfl.com.R;
import mfl.com.databinding.ActivityEditAccountStatusBinding;
import mfl.com.session.GeneralMethods;

public class EditAccountStatus extends AppCompatActivity implements View.OnClickListener {
    private ActivityEditAccountStatusBinding binding;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_account_status);
        binding.setLifecycleOwner(this);
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        listenOnSwitchers();

        binding.disableAccountSwitch.setOnClickListener(this::onClick);
        binding.switchLin.setOnClickListener(this::onClick);

        binding.backBtn.setOnClickListener(this::onClick);

    }

    private void listenOnSwitchers() {
        binding.disableAccountSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {

                    //Todo set warring alert Dialog

                } else {
                    //Todo set warring alert Dialog


                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (binding.switchLin.equals(v)){
            if (binding.disableAccountSwitch.isChecked()){
                binding.disableAccountSwitch.setChecked(false);
            }
            else {

                binding.disableAccountSwitch.setChecked(true);

            }
        }
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
