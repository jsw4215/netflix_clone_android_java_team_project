package com.daniel.app.netfilx_clone.src.main.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.advertisement.AdvertisementActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.main.MovieActivity;
import com.daniel.app.netfilx_clone.src.splash.SplashActivity;

public class MainLoadingActivity extends AppCompatActivity {

    private static final String TAG = "MainLoadingActivity";

    ImageView mLoadingAnim;

    String mFrom;
    int mProfileId;

    static int duration = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_loading);

        Intent intent = getIntent();
        mFrom = intent.getStringExtra("calling_activity");
        mProfileId = intent.getIntExtra("profileId",-1);

        mLoadingAnim = findViewById(R.id.loading_image);

        Glide.with(this).load(R.raw.main_loading).into(mLoadingAnim);

        splashscreenstart();

    }


    public void splashscreenstart(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(mFrom.equals("profile_activity")||mFrom.equals("movie_activity")) {

                    Intent intent = new Intent(MainLoadingActivity.this, MainActivity.class);
                    intent.putExtra("profileId",mProfileId);
                    startActivity(intent);

                }else if(mFrom.equals("main_activity")){

                    Intent intent = new Intent(MainLoadingActivity.this, MovieActivity.class);
                    Log.d(TAG, "click: intent to movie");
                    intent.putExtra("profileId",mProfileId);
                    startActivity(intent);

                }


                finish();
            }
        },duration);

    }


}