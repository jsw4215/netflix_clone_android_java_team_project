package com.daniel.app.netfilx_clone.src.main.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.advertisement.AdvertisementActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.splash.SplashActivity;

public class MainLoadingActivity extends AppCompatActivity {

    ImageView mLoadingAnim;

    static int duration = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loading);

        mLoadingAnim = findViewById(R.id.loading_image);

        Glide.with(this).load(R.raw.main_loading).into(mLoadingAnim);

        splashscreenstart();

    }


    public void splashscreenstart(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainLoadingActivity
                        .this, MainActivity.class));
                finish();
            }
        },duration);

    }


}