package mfl.com.ui.home.fragment.setting.accountSetting.editProfileInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import mfl.com.R;
import mfl.com.databinding.ActivityEditProfileInformationBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.home.mainHome.HomeActivity;
import mfl.com.ui.start.signUp.SignUpScreen;

public class EditProfileInformation extends AppCompatActivity implements View.OnClickListener {
    private GeneralMethods generalMethods;
    private ActivityEditProfileInformationBinding binding;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile_information);
        binding.setLifecycleOwner(this);
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);


        binding.backBtn.setOnClickListener(this::onClick);
        binding.chooseImgBtn.setOnClickListener(this::onClick);
        binding.lawyerImg.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (binding.backBtn.equals(v)) {
            onBackPressed();
        }
        if (binding.lawyerImg.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(EditProfileInformation.this);

        }
        if (binding.chooseImgBtn.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(EditProfileInformation.this);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        try {

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(imageReturnedIntent);

                if (resultCode == RESULT_OK) {
                    binding.lawyerImg.setPadding(0, 0, 0, 0);
                    imageUri = result.getUri();
                    Glide.with(getApplicationContext()).load(imageUri).into(binding.lawyerImg);
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
