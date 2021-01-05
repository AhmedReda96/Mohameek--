package mfl.com.ui.home.fragment.news.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.squareup.picasso.Picasso;

import mfl.com.R;
import mfl.com.databinding.ActivityNewsDetailsScreenBinding;
import mfl.com.db.news.NewsEntity;
import mfl.com.session.GeneralMethods;

public class NewsDetailsScreen extends AppCompatActivity implements View.OnClickListener {
    private ActivityNewsDetailsScreenBinding binding;
    private GeneralMethods generalMethods;
    private Intent intent;
    private String title, image, description, createdBy, date;
    private int id;
    private NewsDetailsVM viewModel;
    private boolean favoriteFlag = false;

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
        viewModel = ViewModelProviders.of(this).get(NewsDetailsVM.class);


        binding.backBtn.setOnClickListener(this::onClick);
        binding.favoriteBtn.setOnClickListener(this::onClick);

        viewModel.initVM(this);
        getIntentData();


    }

    private void getIntentData() {
        intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("newsId"));
        description = intent.getStringExtra("newsDescription");
        createdBy = intent.getStringExtra("createdBy");
        date = intent.getStringExtra("newsDate");
        image = intent.getStringExtra("newsImg");
        title = intent.getStringExtra("newsTitle");

        listenerOnLiveData();
        viewModel.checkId(id);
        setData();
    }

    private void setData() {
        binding.newsDate.setText(date);
        binding.newsSource.setText(createdBy);
        binding.newsTitle.setText(title);
        Picasso.get().load(image).into(binding.newsImg);
        binding.newsDescription.setText(description);

    }


    @Override
    public void onClick(View v) {
        if (binding.favoriteBtn.equals(v)) {
            if (favoriteFlag) {
                binding.favoriteBtn.setBackgroundResource(R.drawable.ic_empty_favorite);
                viewModel.deleteNewsFromRoom(id);
                favoriteFlag = false;

            } else {
                binding.favoriteBtn.setBackgroundResource(R.drawable.ic_full_favorite);
                viewModel.insertNewsToRoom(new NewsEntity(id, title, image, description, createdBy, date));
                favoriteFlag = true;


            }
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

    private void listenerOnLiveData() {
        viewModel.resultLD.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                switch (result) {
                    case "existedId":
                        favoriteFlag = true;
                        binding.favoriteBtn.setBackgroundResource(R.drawable.ic_full_favorite);
                        break;

                    case "notExistedId":
                        favoriteFlag = false;
                        binding.favoriteBtn.setBackgroundResource(R.drawable.ic_empty_favorite);
                        break;

                }
            }
        });
    }

}
