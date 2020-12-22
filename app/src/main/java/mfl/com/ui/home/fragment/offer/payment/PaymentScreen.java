package mfl.com.ui.home.fragment.offer.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import mfl.com.R;
import mfl.com.databinding.ActivityPaymentScreenBinding;
import mfl.com.session.GeneralMethods;

public class PaymentScreen extends AppCompatActivity {
    private ActivityPaymentScreenBinding binding;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_screen);
        binding.setLifecycleOwner(this);

        init();
    }


    private void init() {
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);


    }
}
