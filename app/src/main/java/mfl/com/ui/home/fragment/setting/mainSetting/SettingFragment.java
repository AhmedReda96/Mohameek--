package mfl.com.ui.home.fragment.setting.mainSetting;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mfl.com.R;
import mfl.com.databinding.FragmentSettingBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.home.fragment.setting.accountSetting.mainAccountSetting.MainAccountSetting;
import mfl.com.ui.home.fragment.setting.changeLanguage.ChangeLanguageScreen;
import mfl.com.ui.start.signIn.mainSignIn.SignInScreen;


public class SettingFragment extends Fragment implements View.OnClickListener {

    private FragmentSettingBinding binding;
    private GeneralMethods generalMethods;
    private StoreLanguageData storeLanguageData;
    private Drawable arArrow, enArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
        generalMethods = new GeneralMethods(getActivity());
        storeLanguageData = new StoreLanguageData(getActivity());
        generalMethods.setDirection(binding.mainLin);
        generalMethods.changeLanguage();
        arArrow = getContext().getResources().getDrawable(R.drawable.ar_arrow_ic);
        enArrow = getContext().getResources().getDrawable(R.drawable.en_arrow_ic);


        if (storeLanguageData.getAppLanguage().equals("ar")) {
            binding.helpLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getContext().getResources().getDrawable(R.drawable.help_ic), null);
            binding.accountSettingLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getContext().getResources().getDrawable(R.drawable.account_ic), null);
            binding.logoutLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getContext().getResources().getDrawable(R.drawable.logout_ic), null);
            binding.languageLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getContext().getResources().getDrawable(R.drawable.language_ic), null);
            binding.payLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getContext().getResources().getDrawable(R.drawable.card_ic), null);
            binding.rateLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getContext().getResources().getDrawable(R.drawable.rate_ic), null);
            binding.shareLin.setCompoundDrawablesWithIntrinsicBounds(arArrow, null, getContext().getResources().getDrawable(R.drawable.share_ic), null);

        } else {
            binding.helpLin.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.help_ic), null, enArrow, null);
            binding.accountSettingLin.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.account_ic), null, enArrow, null);
            binding.languageLin.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.language_ic), null, enArrow, null);
            binding.logoutLin.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.logout_ic), null, enArrow, null);
            binding.payLin.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.card_ic), null, enArrow, null);
            binding.rateLin.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.rate_ic), null, enArrow, null);
            binding.shareLin.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.share_ic), null, enArrow, null);

        }

        binding.accountSettingLin.setOnClickListener(this::onClick);
        binding.logoutLin.setOnClickListener(this::onClick);
        binding.languageLin.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        if (binding.languageLin.equals(v)) {
            startActivity(new Intent(getActivity(), ChangeLanguageScreen.class));
        }
        if (binding.accountSettingLin.equals(v)) {
            startActivity(new Intent(getActivity(), MainAccountSetting.class));
        }
        if (binding.logoutLin.equals(v)) {
            startActivity(new Intent(getActivity(), SignInScreen.class));
        }
    }
}
