package mfl.com.ui.start.signIn.signInSteps.stepsHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mfl.com.R;
import mfl.com.databinding.ActivitySignInStepsHomeBinding;
import mfl.com.helper.SignInSectionPagerAdapter;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.start.signIn.mainSignIn.SignInScreen;

public class SignInStepsHome extends AppCompatActivity implements View.OnClickListener {
    private ActivitySignInStepsHomeBinding binding;
    private GeneralMethods generalMethods;
    private Intent intent;
    private static final String TAG = SignInStepsHome.class.getSimpleName();
    private ProgressDialog progressDialog;
    private SweetAlertDialog sweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_steps_home);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in_steps_home);
        binding.setLifecycleOwner(this);

        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
      //  progressDialog.show();

        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitle(this.getResources().getString(R.string.doYouWantToExit));
        sweetAlertDialog.setContentText(this.getResources().getString(R.string.doYouWantToExit_purpose1));
        sweetAlertDialog.setConfirmText(this.getResources().getString(R.string.stay));
        sweetAlertDialog.setCancelText(this.getResources().getString(R.string.exitNow));
        sweetAlertDialog.setCancelable(false);


        binding.viewPager.setAdapter(new SignInSectionPagerAdapter(this));

        binding.viewPager.setUserInputEnabled(false);
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                binding.stepView.go(position, true);
            }
        });

        binding.backBtn.setOnClickListener(this::onClick);

    //   getStepScreen();


    }

    private void showDialog() {
        sweetAlertDialog.show();


        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finishAffinity();


            }
        });

        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                     @Override
                                                     public void onClick(SweetAlertDialog sDialog) {
                                                         sweetAlertDialog.dismiss();
                                                     }
                                                 }
        );

    }

    private void getStepScreen() {
        intent = getIntent();
        Log.d(TAG, "Mohameek getStepScreen: false: " + intent.getStringExtra("step"));
        selectIndex(Integer.parseInt(intent.getStringExtra("step")));
    }

    public void selectIndex(int newIndex) {
        binding.viewPager.setCurrentItem(newIndex);
        progressDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        if (binding.backBtn.equals(v)) {
            showDialog();
        }
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    sweetAlertDialog.dismiss();
    }
}
