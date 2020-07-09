package com.daniel.app.netfilx_clone.src.profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.profile.interfaces.ProfileActivityView;
import com.daniel.app.netfilx_clone.src.profile.models.result;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.ProfileSelectionActivity;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;

import java.util.List;

public class ProfileAddActivity extends BaseActivity implements ProfileActivityView {

    private static final String TAG = "ProfileAddActivity";

    ImageView mIvBackArrow;
    ImageView mIvProfile;
    ImageView mIvEdit;
    TextView mTvComplete;
    EditText mEtName;
    String mImgUrl;
    String mName;
    int mImgId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_add);

        mIvBackArrow = findViewById(R.id.profile_add_back_arrow);
        mIvProfile = findViewById(R.id.profile_add_iv_profile);
        mIvEdit = findViewById(R.id.profile_add_edit);
        mEtName = findViewById(R.id.profile_add_et_profile_name);
        mTvComplete = findViewById(R.id.profile_add_complete);
        mTvComplete.setVisibility(View.GONE);

        mEtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mTvComplete.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvComplete.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Intent intent = getIntent();
        if(intent!=null) {
            String imgUrl = intent.getStringExtra("imgUrl");
            mImgId = intent.getIntExtra("imgId",-1);
            Log.d(TAG, "onCreate: imgid" + mImgId);
            setImg(imgUrl);
        }
        Log.d(TAG, "onCreate: imgid, out of [if clause]" + mImgId);
        mTvComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: imgid" + mImgId);
                tryPostProfile(mEtName.getText().toString(),mImgId);
            }
        });

        mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAddActivity.this, ProfileSelectionActivity.class);
                intent.putExtra("calling_activity","profile_add_activity");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        mIvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAddActivity.this, ProfileSelectionActivity.class);
                intent.putExtra("calling_activity","profile_add_activity");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });


        mIvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAddActivity.this, ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

    }

    private void tryPostProfile(String name, int imgId){
        Log.d(TAG, "tryPostProfile: started." + name + imgId);
        showProgressDialog();

        final ProfileService profileService = new ProfileService(this);
        profileService.postProfile(name,imgId);

    }

    public void setImg(String imgUrl){

        if(imgUrl!=null) {

            new DownloadImageTask(mIvProfile).execute(imgUrl);

            mImgUrl = imgUrl;
        }
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
        Intent intent = new Intent(ProfileAddActivity.this, ProfileActivity.class);
        intent.putExtra("imgUrl",mImgUrl);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        Log.d(TAG, "validateFailure: ");
    }
}