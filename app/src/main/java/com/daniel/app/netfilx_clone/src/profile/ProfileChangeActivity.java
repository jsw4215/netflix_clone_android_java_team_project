package com.daniel.app.netfilx_clone.src.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;

public class ProfileChangeActivity extends AppCompatActivity {

    TextView mProfileChangeCancel;
    TextView mProfileChangeSave;
    ImageView mProfileChangeIvProfile;
    TextView mProfileChangeTvProfile;
    ProfileActivity profileActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change);

        mProfileChangeCancel = findViewById(R.id.profile_change_tv_cancel);
        mProfileChangeSave = findViewById(R.id.profile_change_tv_save);
        mProfileChangeIvProfile = findViewById(R.id.profile_change_iv_profile);
        mProfileChangeTvProfile = findViewById(R.id.profile_change_tv_profile);

        mProfileChangeTvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileChangeActivity.this, ProfileSelectionActivity.class);
                startActivity(intent);
            }
        });

        mProfileChangeIvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileChangeActivity.this, ProfileSelectionActivity.class);
                startActivity(intent);
            }
        });

        mProfileChangeCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mProfileChangeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //값 넘기기

               finish();
            }
        });

    }
}
