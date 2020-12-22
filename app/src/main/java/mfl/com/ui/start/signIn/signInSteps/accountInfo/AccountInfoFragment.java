package mfl.com.ui.start.signIn.signInSteps.accountInfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import mfl.com.databinding.FragmentAccountInfoBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

import static android.app.Activity.RESULT_OK;


public class AccountInfoFragment extends Fragment implements View.OnClickListener {
    private FragmentAccountInfoBinding binding;
    private GeneralMethods generalMethods;
    private Uri imageUri = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountInfoBinding.inflate(inflater, container, false);
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
        binding.lawyerImg.setOnClickListener(this::onClick);


        binding.lawyerImg.setOnClickListener(this::onClick);
        generalMethods.setDirection(binding.mainLin);
        binding.bio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 300) {
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

        if (binding.lawyerImg.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(getActivity());

        }


    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        try {

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(imageReturnedIntent);

                if (resultCode == RESULT_OK) {
                    binding.lawyerImg.setPadding(0, 0, 0, 0);
                    imageUri = result.getUri();
                    Glide.with(getActivity()).load(imageUri).into(binding.lawyerImg);
                    binding.lawyerImg.setScaleType(ImageView.ScaleType.FIT_XY);

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                    Exception error = result.getError();
                    error.printStackTrace();

                }
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }

}