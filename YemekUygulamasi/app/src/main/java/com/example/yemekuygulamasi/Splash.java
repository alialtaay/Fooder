package com.example.yemekuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.content.Intent;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash3);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 3000);
    }
}