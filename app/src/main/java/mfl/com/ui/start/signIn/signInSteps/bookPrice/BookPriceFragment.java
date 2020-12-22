package mfl.com.ui.start.signIn.signInSteps.bookPrice;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suke.widget.SwitchButton;

import mfl.com.R;
import mfl.com.databinding.FragmentBookPriceBinding;
import mfl.com.databinding.FragmentPaymentBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.home.mainHome.HomeActivity;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class BookPriceFragment extends Fragment implements View.OnClickListener {
    private FragmentBookPriceBinding binding;
    private GeneralMethods generalMethods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBookPriceBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        listenOnSwitchers();
    }

    private void init() {
        generalMethods = new GeneralMethods(getActivity());
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);

        binding.nextBtn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (binding.nextBtn.equals(v)) {
            ((SignInStepsHome) getActivity()).selectIndex(5);
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
