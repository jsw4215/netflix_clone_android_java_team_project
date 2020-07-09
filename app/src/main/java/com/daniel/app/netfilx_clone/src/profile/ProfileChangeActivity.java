package com.daniel.app.netfilx_clone.src.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.profile.interfaces.ProfileActivityView;
import com.daniel.app.netfilx_clone.src.profile.models.ProfileBody;
import com.daniel.app.netfilx_clone.src.profile.models.result;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.ProfileSelectionActivity;
import com.daniel.app.netfilx_clone.src.profile.utils.DeleteDialogActivity;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;

import java.util.List;

public class ProfileChangeActivity extends BaseActivity implements ProfileActivityView {

    private static final String TAG = "ProfileChangeActivity";

    String mImgUrl;
    int mProfileId;
    int mImgId;

    ImageView mProfileChangeCancel;
    TextView mProfileChangeSave;
    ImageView mProfileChangeIvProfile;
    TextView mProfileChangeTvProfile;
    ProfileActivity profileActivity;
    EditText mProfileChangeET;
    EditText mEtProfileName;

    LinearLayout mLinearLayout;
    Context mContext = ProfileChangeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_change);

        mProfileChangeCancel = findViewById(R.id.profile_change_tv_cancel);
        mProfileChangeSave = findViewById(R.id.profile_change_tv_save);
        mProfileChangeIvProfile = findViewById(R.id.profile_change_iv_profile);
        mProfileChangeTvProfile = findViewById(R.id.profile_change_tv_profile);
        mLinearLayout = findViewById(R.id.profile_change_ll_delete);
        mProfileChangeET = findViewById(R.id.profile_change_et_profile_name);
        mEtProfileName = findViewById(R.id.profile_change_et_profile_name);

        //인텐트 받아오기 이미지, ID

        Intent intent = getIntent();
        mImgUrl = intent.getStringExtra("imgUrl");
        mProfileId = intent.getIntExtra("profileId", -1);
        mImgId = intent.getIntExtra("imgId",-1);

        Glide.with(mContext).load(mImgUrl).into(mProfileChangeIvProfile);
        mEtProfileName.setText(String.valueOf(mProfileId));
        Log.d(TAG, "onCreate: profileId" + mProfileId);

        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //삭제

                //다이얼로그 띄우기
                Intent intent = new Intent(ProfileChangeActivity.this, DeleteDialogActivity.class);
                intent.putExtra("profileId", mProfileId);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

        mProfileChangeTvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileChangeActivity.this, ProfileSelectionActivity.class);
                intent.putExtra("profileId", mProfileId);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        mProfileChangeIvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileChangeActivity.this, ProfileSelectionActivity.class);
                intent.putExtra("profileId", mProfileId);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        mProfileChangeCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        mProfileChangeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String etName = mProfileChangeET.getText().toString();
                ProfileBody profileBody = new ProfileBody();
                profileBody.setProfileName(etName);
                profileBody.setProfileImgId(mImgId);
                Log.d(TAG, "onClick: " + etName + "and " + mProfileId);
                tryModifyProfiles(profileBody, mProfileId);
            }
        });

    }

    public void tryModifyProfiles(ProfileBody profileBody, int profileId) {
        Log.d(TAG, "tryModifyProfiles: started.");
        showProgressDialog();

        final ProfileService profileService = new ProfileService(this);

        profileService.modifyProfile(profileBody, profileId);

    }

    @Override
    public void validateSuccess(List<result> result, int addAvailable) {
        hideProgressDialog();
        Log.d(TAG, "validateSuccess: ");
    }

    @Override
    public void profileAddSuccess(boolean isSuccess) {
        hideProgressDialog();
        Log.d(TAG, "profileAddSuccess: ");
        Intent intent = new Intent(ProfileChangeActivity.this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        Log.d(TAG, "validateFailure: ");
    }
}
