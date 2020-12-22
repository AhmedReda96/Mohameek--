package mfl.com.ui.home.fragment.offer.offerDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivityOfferDetailsScreenBinding;
import mfl.com.session.GeneralMethods;

public class OfferDetailsScreen extends AppCompatActivity implements View.OnClickListener {

    private ActivityOfferDetailsScreenBinding binding;
    private GeneralMethods generalMethods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_offer_details_screen);
        binding.setLifecycleOwner(this);

        init();
    }

    private void init() {
        generalMethods = new GeneralMethods(this);
        generalMethods.setDirection(binding.mainLin);
        generalMethods.changeLanguage();

        binding.deleteOfferBtn.setOnClickListener(this::onClick);
        binding.backBtn.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View v) {
        if (binding.backBtn.equals(v)){

            onBackPressed();

        }  if (binding.deleteOfferBtn.equals(v)){



        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    finish();
    }
}
