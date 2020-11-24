package mfl.com.ui.start.signIn.signInSteps.changePassword;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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


        binding.nextBtn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {

        if (binding.nextBtn.equals(v)) {
            ((SignInStepsHome) getActivity()).selectIndex(1);

        }
    }

}

