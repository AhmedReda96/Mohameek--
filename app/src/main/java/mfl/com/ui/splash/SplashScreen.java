package mfl.com.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import mfl.com.R;
import mfl.com.ui.home.fragment.news.details.NewsDetailsScreen;
import mfl.com.ui.home.mainHome.HomeActivity;
import mfl.com.ui.start.signIn.mainSignIn.SignInScreen;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }

    private void init() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                finish();
            }
        }, 3000);


    }
}
