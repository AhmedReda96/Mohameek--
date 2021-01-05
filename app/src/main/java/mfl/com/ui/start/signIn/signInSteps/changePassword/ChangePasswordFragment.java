package mfl.com.ui.start.signIn.signInSteps.changePassword;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mfl.com.R;
import mfl.com.databinding.FragmentChangePasswordBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;


public class ChangePasswordFragment extends Fragment implements View.OnClickListener {
    private FragmentChangePasswordBinding binding;
    private GeneralMethods generalMethods;
    private ChangePasswordVM viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
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
        viewModel = ViewModelProviders.of(getActivity()).get(ChangePasswordVM.class);

        binding.nextBtn.setOnClickListener(this::onClick);
        viewModel.initVM(getActivity());
        listenerOnLiveData();
    }

    @Override
    public void onClick(View v) {

        if (binding.nextBtn.equals(v)) {
            viewModel.checkData(binding.Password.getText().toString().trim(), binding.confirmPassword.getText().toString().trim());
        }
    }


    private void listenerOnLiveData() {
        viewModel.resultLD.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String result) {
                binding.error.setVisibility(View.VISIBLE);
                switch (result) {

                    case "invalid password":
                        binding.error.setText(getResources().getString(R.string.invalidPassword));
                        break;
                    case "invalid confirmPassword1":
                        binding.error.setText(getResources().getString(R.string.invalidConfirmPassword));
                        break;
                    case "invalid confirmPassword2":
                        binding.error.setText(getResources().getString(R.string.confirmPasswordNotMatched));
                        break;
                    case "noInternetConnection":
                        binding.error.setText(getResources().getString(R.string.noInternetConnection));
                        break;
                    case "resetError":
                        binding.error.setText("");
                        break;
                    case "ServerError":
                        binding.error.setText(getResources().getString(R.string.serverError));
                        break;

                }
            }
        });


    }
}

