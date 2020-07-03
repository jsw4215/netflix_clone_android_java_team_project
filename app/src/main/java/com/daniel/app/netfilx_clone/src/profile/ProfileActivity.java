package com.daniel.app.netfilx_clone.src.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.main.MainActivity;

public class ProfileActivity extends AppCompatActivity {

    ImageView mIvProfile1;
    ImageView mIvProfile2;
    ImageView mIvProfile3;
    ImageView mIvProfile4;
    ImageView mIvEditProfile1;
    ImageView mIvEditProfile2;
    ImageView mIvEditProfile3;
    ImageView mIvEditProfile4;
    ColorFilter oldColor;
    TextView mTvProfileChange;
    TextView mTvProfileComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// view inflating
        setContentView(R.layout.activity_profile);
        mIvProfile1 = findViewById(R.id.profile_iv_profile1);
        mIvProfile2 = findViewById(R.id.profile_iv_profile2);
        mIvProfile3 = findViewById(R.id.profile_iv_profile3);
        mIvProfile4 = findViewById(R.id.profile_iv_profile4);

        mTvProfileChange = findViewById(R.id.profile_tv_change);
        mTvProfileComplete = findViewById(R.id.profile_tv_complete);

        mIvEditProfile1 = findViewById(R.id.profile_iv_edit_profile1);
        mIvEditProfile2 = findViewById(R.id.profile_iv_edit_profile2);
        mIvEditProfile3 = findViewById(R.id.profile_iv_edit_profile3);
        mIvEditProfile4 = findViewById(R.id.profile_iv_edit_profile4);

        mTvProfileComplete.setVisibility(View.GONE);
        mIvEditProfile1.setVisibility(View.GONE);
        mIvEditProfile2.setVisibility(View.GONE);
        mIvEditProfile3.setVisibility(View.GONE);
        mIvEditProfile4.setVisibility(View.GONE);

        oldColor =  mIvProfile1.getColorFilter();

        mIvProfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mIvProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mIvProfile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mIvProfile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mTvProfileChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvProfileChange.setVisibility(View.GONE);
                mTvProfileComplete.setVisibility(View.VISIBLE);
                mIvEditProfile1.setVisibility(View.VISIBLE);
                mIvEditProfile2.setVisibility(View.VISIBLE);
                mIvEditProfile3.setVisibility(View.VISIBLE);

                mIvProfile1.setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.MULTIPLY);
                mIvProfile2.setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.MULTIPLY);
                mIvProfile3.setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.MULTIPLY);

                mIvEditProfile1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, ProfileChangeActivity.class);
                        startActivity(intent);
                    }
                });

                mIvEditProfile2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, ProfileChangeActivity.class);
                        startActivity(intent);
                    }
                });

                mIvEditProfile3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, ProfileChangeActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });

        mTvProfileComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTvProfileComplete.setVisibility(View.GONE);
                mIvEditProfile1.setVisibility(View.GONE);
                mIvEditProfile2.setVisibility(View.GONE);
                mIvEditProfile3.setVisibility(View.GONE);
                mIvEditProfile4.setVisibility(View.GONE);

                mIvProfile1.clearColorFilter();
                mIvProfile2.clearColorFilter();
                mIvProfile3.clearColorFilter();

                mTvProfileChange.setVisibility(View.VISIBLE);

            }
        });


    }



}
