package com.example.plantingyourway;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Planting Your Way");

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (SharedPref.readBoolenValue(Constants.REMEMBER_ME, false)) {
                    Log.e("doSomethings", "treu");
                    startActivity(new Intent(SplashActivity.this, ShoppingActivity.class));
                    finish();
                } else {
                    Log.e("doSomethings", "false");
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();

                }

//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        };
        Timer timer = new Timer();
        //Adding timer for 4 seconds
        timer.schedule(timerTask,1000);
    }
}

