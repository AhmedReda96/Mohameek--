package mfl.com.ui.start.signUp;

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
import mfl.com.databinding.ActivitySignUpScreenBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.home.HomeActivity;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {
    private ActivitySignUpScreenBinding binding;
    private StoreLanguageData storeLanguageData;
    private GeneralMethods generalMethods;
    private Uri imageUri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        storeLanguageData = new StoreLanguageData(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_screen);
        binding.setLifecycleOwner(this);

        generalMethods.setDirection(binding.mainLin);
        checkLanguage();

        binding.signUpBtn.setOnClickListener(this::onClick);
        binding.cardImg.setOnClickListener(this::onClick);
        binding.addCardBtn.setOnClickListener(this::onClick);


    }

    private void checkLanguage() {
        if (storeLanguageData.getAppLanguage().equals("en")) {
            binding.joinUsImg.setImageResource(R.drawable.en_subscribe_ic);
        }
        if (storeLanguageData.getAppLanguage().equals("ar")) {
            binding.joinUsImg.setImageResource(R.drawable.ar_subscribe_ic);
        }
    }

    @Override
    public void onClick(View v) {
        if (binding.signUpBtn.equals(v)) {
            startActivity(new Intent(this, HomeActivity.class));

        }
        if (binding.cardImg.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(SignUpScreen.this);

        }
        if (binding.addCardBtn.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(SignUpScreen.this);

        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        try {

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(imageReturnedIntent);

                if (resultCode == RESULT_OK) {
                    binding.cardImg.setPadding(0, 0, 0, 0);
                    imageUri = result.getUri();
                    Glide.with(getApplicationContext()).load(imageUri).into(binding.cardImg);
                    binding.cardImg.setScaleType(ImageView.ScaleType.FIT_XY);

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
