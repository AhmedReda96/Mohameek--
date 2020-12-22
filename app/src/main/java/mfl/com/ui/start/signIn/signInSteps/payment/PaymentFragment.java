package mfl.com.ui.start.signIn.signInSteps.payment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mfl.com.databinding.FragmentPaymentBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.home.mainHome.HomeActivity;


public class PaymentFragment extends Fragment implements View.OnClickListener {
    private FragmentPaymentBinding binding;
    private GeneralMethods generalMethods;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
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
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);

        binding.nextBtn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (binding.nextBtn.equals(v)){
            startActivity(new Intent(getActivity(), HomeActivity.class));
            getActivity().finish();

        }

    }
}
