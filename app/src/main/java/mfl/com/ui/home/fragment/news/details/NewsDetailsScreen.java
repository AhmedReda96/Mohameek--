package mfl.com.ui.home.fragment.news.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import mfl.com.R;
import mfl.com.databinding.ActivityNewsDetailsScreenBinding;
import mfl.com.session.GeneralMethods;

public class NewsDetailsScreen extends AppCompatActivity implements View.OnClickListener {
    private ActivityNewsDetailsScreenBinding binding;
    private GeneralMethods generalMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details_screen);
        init();
    }

    private void init() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details_screen);
        binding.setLifecycleOwner(this);
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);


        binding.backBtn.setOnClickListener(this::onClick);
        binding.favoriteBtn.setOnClickListener(this::onClick);

    }


    @Override
    public void onClick(View v) {
        if (binding.favoriteBtn.equals(v)) {
            binding.favoriteBtn.setBackgroundResource(R.drawable.ic_full_favorite);
        }

        if (binding.backBtn.equals(v)) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
