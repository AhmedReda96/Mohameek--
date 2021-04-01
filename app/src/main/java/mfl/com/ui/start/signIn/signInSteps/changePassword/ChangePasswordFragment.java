package mfl.com.ui.start.signIn.signInSteps.changePassword;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

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
           // viewModel.checkData(binding.Password.getText().toString().trim(), binding.confirmPassword.getText().toString().trim());
            ((SignInStepsHome) getActivity()).selectIndex(1);

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
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidPassword), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid confirmPassword1":
                        binding.error.setText(getResources().getString(R.string.invalidConfirmPassword));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidConfirmPassword), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid confirmPassword2":
                        binding.error.setText(getResources().getString(R.string.confirmPasswordNotMatched));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.confirmPasswordNotMatched), Snackbar.LENGTH_LONG).show();

                        break;
                    case "noInternetConnection":
                        binding.error.setText(getResources().getString(R.string.noInternetConnection));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.noInternetConnection), Snackbar.LENGTH_LONG).show();

                        break;
                    case "resetError":
                        binding.error.setText("");
                        break;
                    case "ServerError":
                        binding.error.setText(getResources().getString(R.string.serverError));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.serverError), Snackbar.LENGTH_LONG).show();

                        break;

                }
            }
        });


    }
}

