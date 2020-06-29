package com.daniel.app.netfilx_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    //Thats for duration
    static int duration=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar spinner = new android.widget.ProgressBar(SplashActivity.this,null,
                android.R.attr.progressBarStyle);

        spinner.getIndeterminateDrawable().setColorFilter(0xFFFF0000, android.graphics.PorterDuff.Mode.MULTIPLY);

        splashscreenstart();

    }
    public void splashscreenstart(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity
                        .this, SignInActivity.class));
                finish();
            }
        },duration);

    }

}