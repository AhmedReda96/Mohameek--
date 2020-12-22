package mfl.com.ui.home.fragment.offer.addOffer;

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
import mfl.com.databinding.ActivityAddOfferScreenBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.home.fragment.offer.payment.PaymentScreen;
import mfl.com.ui.home.fragment.setting.accountSetting.editProfileInfo.EditProfileInformation;

public class AddOfferScreen extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddOfferScreenBinding binding;
    private GeneralMethods generalMethods;
    private Uri imageUri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_offer_screen);
        binding.setLifecycleOwner(this);
        init();
    }


    private void init() {
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);


        binding.chooseImgBtn.setOnClickListener(this::onClick);
        binding.offerImg.setOnClickListener(this::onClick);
        binding.nextBtn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (binding.nextBtn.equals(v)) {
            startActivity(new Intent(this, PaymentScreen.class));
        }
        if (binding.chooseImgBtn.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(AddOfferScreen.this);
        }
        if (binding.offerImg.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(AddOfferScreen.this);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        try {

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(imageReturnedIntent);

                if (resultCode == RESULT_OK) {
                    binding.offerImg.setPadding(0, 0, 0, 0);
                    imageUri = result.getUri();
                    Glide.with(getApplicationContext()).load(imageUri).into(binding.offerImg);
                    binding.offerImg.setScaleType(ImageView.ScaleType.FIT_XY);

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
