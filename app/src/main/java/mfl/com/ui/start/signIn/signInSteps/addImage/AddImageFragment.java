package mfl.com.ui.start.signIn.signInSteps.addImage;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import mfl.com.R;
import mfl.com.databinding.FragmentAddImageBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;


public class AddImageFragment extends Fragment implements View.OnClickListener {
    private FragmentAddImageBinding binding;
    private GeneralMethods generalMethods;
    private List<String> list=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddImageBinding.inflate(inflater, container, false);
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
        binding.nextBtn.setOnClickListener(this::onClick);

        generalMethods.setDirection(binding.mainLin);
        list.add("adsd");
        list.add("adsd");
        list.add("adsd");
        list.add("adsd");

        binding.spinner.setItems(list);
        binding.spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });


        binding.bio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 150) {
                    binding.maxInfo.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                } else {
                    binding.maxInfo.setTextColor(getResources().getColor(android.R.color.darker_gray));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    @Override
    public void onClick(View v) {
        if (binding.nextBtn.equals(v)) {
            ((SignInStepsHome) getActivity()).selectIndex(2);

        }
    }
}