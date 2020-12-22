package mfl.com.ui.home.fragment.setting.accountSetting.editBookingPrice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.suke.widget.SwitchButton;

import mfl.com.R;
import mfl.com.databinding.ActivityEditBookingPriceBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class EditBookingPrice extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditBookingPriceBinding binding;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_booking_price);
        binding.setLifecycleOwner(this);
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        binding.backBtn.setOnClickListener(this::onClick);


        binding.saveBtn.setOnClickListener(this::onClick);

        listenOnSwitchers();

    }

    @Override
    public void onClick(View v) {

        if (binding.saveBtn.equals(v)) {
            onBackPressed();
        }

        if (binding.backBtn.equals(v)) {
            onBackPressed();
        }


    }

    private void listenOnSwitchers() {

        binding.voiceCallLin.setVisibility(View.GONE);

        binding.voiceCallSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.voiceCallLin.setVisibility(View.VISIBLE);

                } else {
                    binding.voiceCallLin.setVisibility(View.GONE);
                }
            }
        });
    }
}
