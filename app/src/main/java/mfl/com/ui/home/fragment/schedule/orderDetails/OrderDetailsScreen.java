package mfl.com.ui.home.fragment.schedule.orderDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import mfl.com.R;
import mfl.com.databinding.ActivityOrderDetailsScreenBinding;
import mfl.com.session.GeneralMethods;

public class OrderDetailsScreen extends AppCompatActivity {
    private GeneralMethods generalMethods;
    private ActivityOrderDetailsScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_details_screen);
        init();

    }

    private void init() {
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
    }
}
