package mfl.com.ui.start.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

import mfl.com.R;
import mfl.com.databinding.ActivitySignUpScreenBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.home.mainHome.HomeActivity;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SignUpScreen.class.getSimpleName();
    private ActivitySignUpScreenBinding binding;
    private GeneralMethods generalMethods;
    private Uri imageUri = null;
    private Bitmap bitmap = null;
    private SignUpScreenVM viewModel;
    private StoreLanguageData storeLanguageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_screen);
        binding.setLifecycleOwner(this);

        init();
    }

    private void init() {
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        storeLanguageData = new StoreLanguageData(this);

        viewModel = new ViewModelProvider(this).get(SignUpScreenVM.class);
        viewModel.initVM(this);



        binding.backBtn.setOnClickListener(this::onClick);
        binding.cardImg.setOnClickListener(this::onClick);
        binding.addCardBtn.setOnClickListener(this::onClick);
        binding.signUpBtn.setOnClickListener(this::onClick);
        changeIntroImage();

        listenerOnLiveData();
    }

    private void changeIntroImage() {
        if (storeLanguageData.getAppLanguage().equals("en")) {
            binding.joinUsImg.setImageResource(R.drawable.en_subscribe_ic);
        }
        if (storeLanguageData.getAppLanguage().equals("ar")) {
            binding.joinUsImg.setImageResource(R.drawable.ar_subscribe_ic);
        }

    }

    private void listenerOnLiveData() {
        viewModel.resultLD.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                binding.error.setVisibility(View.VISIBLE);
                switch (result) {
                    case "invalid FirstName":
                        binding.error.setText(getResources().getString(R.string.invalidFN));
                        break;
                    case "invalid LastName":
                        binding.error.setText(getResources().getString(R.string.invalidLN));
                        break;
                    case "invalid Email":
                        binding.error.setText(getResources().getString(R.string.invalidEmail));
                        break;
                    case "invalid Phone":
                        binding.error.setText(getResources().getString(R.string.invalidPhone));
                        break;
                    case "invalid Id":
                        binding.error.setText(getResources().getString(R.string.invalidId));
                        break;
                    case "invalid card Image":
                        binding.error.setText(getResources().getString(R.string.invalidCardImage));
                        break;

                }
            }
        });


    }


    @Override
    public void onClick(View v) {

        if (binding.backBtn.equals(v)) {
            onBackPressed();
        }
        if (binding.cardImg.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(SignUpScreen.this);

        }
        if (binding.addCardBtn.equals(v)) {
            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(SignUpScreen.this);

        }
        if (binding.signUpBtn.equals(v)) {
            viewModel.checkData(binding.firstName.getText().toString().trim(),
                    binding.lastName.getText().toString().trim(),
                    binding.email.getText().toString().trim(),
                    binding.phone.getText().toString().trim(),
                    binding.id.getText().toString().trim(),
                    bitmap);
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

                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);



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
