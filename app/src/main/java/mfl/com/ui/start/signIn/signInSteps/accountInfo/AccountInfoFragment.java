package mfl.com.ui.start.signIn.signInSteps.accountInfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mfl.com.R;
import mfl.com.databinding.FragmentAccountInfoBinding;
import mfl.com.session.GeneralMethods;


import static android.app.Activity.RESULT_OK;

public class AccountInfoFragment extends Fragment implements View.OnClickListener {
    private FragmentAccountInfoBinding binding;
    private GeneralMethods generalMethods;
    private Uri imageUri = null;
    private String imgExtension;
    private Bitmap bitmap = null;
    private AccountInfoVM viewModel;
    private int gender = 1;
    private String date = null;
    private List<Integer> specialises = new ArrayList<>();
    private int currentYear;
    private int choiceYear;

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
        generalMethods.setDirection(binding.mainLin);
        viewModel = new ViewModelProvider(this).get(AccountInfoVM.class);
        viewModel.initVM(getActivity());


        binding.nextBtn.setOnClickListener(this::onClick);
        binding.lawyerImg.setOnClickListener(this::onClick);
        binding.lawyerImg.setOnClickListener(this::onClick);

        viewModel.getProfileData();
        listeners();
        listenerOnLiveData();


    }

    private void listenerOnLiveData() {

        viewModel.getFNResultLD.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.firstName.setText(s);
            }
        });

        viewModel.getLNResultLD.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.lastName.setText(s);

            }
        });
        viewModel.getPhoneResultLD.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.phone.setText(s);

            }
        });

        viewModel.resultLD.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String result) {
                binding.error.setVisibility(View.VISIBLE);
                switch (result) {
                    case "invalid card Image":
                        binding.error.setText(getResources().getString(R.string.invalidImage));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidImage), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid FirstName":
                        binding.error.setText(getResources().getString(R.string.invalidFN));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidFN), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid LastName":
                        binding.error.setText(getResources().getString(R.string.invalidLN));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidLN), Snackbar.LENGTH_LONG).show();

                        break;

                    case "invalid Phone":
                        binding.error.setText(getResources().getString(R.string.invalidPhone));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidPhone), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid date":
                        binding.error.setText(getResources().getString(R.string.invalidDate));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidDate), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid year":
                        binding.error.setText(getResources().getString(R.string.invalidYear));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidYear), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid specialises":
                        binding.error.setText(getResources().getString(R.string.invalidSpecialises));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidSpecialises), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid bio":
                        binding.error.setText(getResources().getString(R.string.invalidBio));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidBio), Snackbar.LENGTH_LONG).show();

                        break;
                    case "resetError":
                        binding.error.setText("");
                        break;

                    case "noInternetConnection":
                        binding.error.setText(getResources().getString(R.string.noInternetConnection));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.noInternetConnection), Snackbar.LENGTH_LONG).show();

                        break;
                    case "ServerError":
                        binding.error.setText(getResources().getString(R.string.serverError));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.serverError), Snackbar.LENGTH_LONG).show();

                        break;

                }
            }
        });


    }

    private void listeners() {
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


        binding.maleRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.femaleRB.setChecked(false);
                    gender = 1;
                }
            }
        });

        binding.femaleRB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.maleRB.setChecked(false);
                gender = 2;
            }
        });

        date = String.valueOf(binding.datePicker.getDayOfMonth() + "/" + binding.datePicker.getMonth() + 1 + "/" + binding.datePicker.getYear());


        binding.aamCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(1);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(1));
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            }
        });
        binding.aqaratCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(2);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(2));
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            }
        });
        binding.merasCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(3);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(3));
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            }
        });
        binding.genyatCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(4);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(4));
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            }
        });
        binding.zawagWTalaqCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(5);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(5));
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            }
        });
        binding.hadanaCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(6);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(6));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.estsharatQanonyaCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(7);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(7));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.sharekatCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(8);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(8));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.defaaCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(9);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(9));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.sharaayCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(10);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(10));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.qadayaAllamayaCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(11);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(11));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.qadayaBenookCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(12);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(12));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.hegraCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(13);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(13));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.darayebCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(14);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(14));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });
        binding.dawlyCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                specialises.add(15);
                Log.d("TAG", "Mohameek onCheckedChanged: " + specialises.size());
            } else {
                specialises.remove(Integer.valueOf(15));
                Log.d("TAG", "Mohameek  onCheckedChanged: " + specialises.size());
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (binding.nextBtn.equals(v)) {
            currentYear = Calendar.getInstance().get(Calendar.YEAR);
            choiceYear = binding.datePicker.getYear();
            viewModel.checkData(bitmap, imgExtension, binding.firstName.getText().toString().trim(), binding.lastName.getText().toString().trim(), gender, date, currentYear, choiceYear, binding.phone.getText().toString().trim(), specialises, binding.bio.getText().toString().trim());
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
                    String path = imageUri.getLastPathSegment();
                    imgExtension = path.substring(path.lastIndexOf(".") + 1); // Without dot jpg, png
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);

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