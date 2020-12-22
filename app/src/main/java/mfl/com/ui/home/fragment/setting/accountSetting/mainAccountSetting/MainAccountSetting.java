package mfl.com.ui.home.fragment.setting.accountSetting.mainAccountSetting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivityMainAccountSettingBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.home.fragment.setting.accountSetting.editBookingPrice.EditBookingPrice;
import mfl.com.ui.home.fragment.setting.accountSetting.editOfficeLocation.EditOfficeLocation;
import mfl.com.ui.home.fragment.setting.accountSetting.editOfficeWorkingHours.EditOfficeWorkingHours;
import mfl.com.ui.home.fragment.setting.accountSetting.editPassword.EditPassword;
import mfl.com.ui.home.fragment.setting.accountSetting.editProfileInfo.EditProfileInformation;
import mfl.com.ui.home.fragment.setting.accountSetting.editStatus.EditAccountStatus;

public class MainAccountSetting extends AppCompatActivity implements View.OnClickListener {
    private GeneralMethods generalMethods;
    private StoreLanguageData storeLanguageData;
    private Drawable arArrow, enArrow;
    private ActivityMainAccountSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_account_setting);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_account_setting);
        binding.setLifecycleOwner(this);
        generalMethods = new GeneralMethods(this);
        storeLanguageData = new StoreLanguageData(this);
        generalMethods.setDirection(binding.mainLin);
        generalMethods.changeLanguage();
        arArrow = getResources().getDrawable(R.drawable.ar_arrow_ic);
        enArrow = getResources().getDrawable(R.drawable.en_arrow_ic);


        if (storeLanguageData.getAppLanguage().equals("ar")) {
            binding.editProfileInfoLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getResources().getDrawable(R.drawable.person_ic), null);
            binding.editWorkLocationLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getResources().getDrawable(R.drawable.location_black_ic), null);
            binding.editOfficeTimeLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getResources().getDrawable(R.drawable.time_small_ic), null);
            binding.editBookingPriceLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getResources().getDrawable(R.drawable.price_ic), null);
            binding.editPasswordLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getResources().getDrawable(R.drawable.lock_ic), null);
            binding.disableAccountLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getResources().getDrawable(R.drawable.disable_ic), null);

        } else {
            binding.editProfileInfoLin.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.person_ic), null, enArrow, null);
            binding.editWorkLocationLin.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.location_black_ic), null, enArrow, null);
            binding.editOfficeTimeLin.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.time_small_ic), null, enArrow, null);
            binding.editBookingPriceLin.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.price_ic), null, enArrow, null);
            binding.editPasswordLin.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.lock_ic), null, enArrow, null);
            binding.disableAccountLin.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.disable_ic), null, enArrow, null);

        }
        binding.editProfileInfoLin.setOnClickListener(this::onClick);
        binding.editWorkLocationLin.setOnClickListener(this::onClick);
        binding.editOfficeTimeLin.setOnClickListener(this::onClick);
        binding.editBookingPriceLin.setOnClickListener(this::onClick);
        binding.editPasswordLin.setOnClickListener(this::onClick);
        binding.disableAccountLin.setOnClickListener(this::onClick);
        binding.backBtn.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {

        if (binding.editProfileInfoLin.equals(v)) {
            startActivity(new Intent(this, EditProfileInformation.class));
        }
        if (binding.editWorkLocationLin.equals(v)) {
            startActivity(new Intent(this, EditOfficeLocation.class));

        }
        if (binding.editOfficeTimeLin.equals(v)) {
            startActivity(new Intent(this, EditOfficeWorkingHours.class));

        }
        if (binding.editBookingPriceLin.equals(v)) {
            startActivity(new Intent(this, EditBookingPrice.class));

        }
        if (binding.editPasswordLin.equals(v)) {
            startActivity(new Intent(this, EditPassword.class));

        }
        if (binding.disableAccountLin.equals(v)) {
            startActivity(new Intent(this, EditAccountStatus.class));

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
