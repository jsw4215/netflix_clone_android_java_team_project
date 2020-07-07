package com.daniel.app.netfilx_clone.src.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.main.utils.MainLoadingActivity;
import com.daniel.app.netfilx_clone.src.profile.interfaces.ProfileActivityView;
import com.daniel.app.netfilx_clone.src.profile.models.result;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;

import java.util.ArrayList;
import java.util.List;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class ProfileActivity extends BaseActivity implements ProfileActivityView {

    private static final String TAG = "ProfileActivity";

    ImageView mIvProfile1;
    ImageView mIvProfile2;
    ImageView mIvProfile3;
    ImageView mIvProfile4;
    ImageView mIvEditProfile1;
    ImageView mIvEditProfile2;
    ImageView mIvEditProfile3;
    ImageView mIvEditProfile4;

    ImageView mTvProfileChange;
    ImageView mTvProfileComplete;
    List<result> mResult = new ArrayList<>();
    int mAddAvailable;
    String mTag_available = "available";
    String mTag_exist = "exist";
    String mImgUrl;
    Context mContext = ProfileActivity.this;


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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(0);
        }

        String temp = sSharedPreferences.getString(X_ACCESS_TOKEN,null);

        Log.d(TAG, "onCreate: token" + mResult.size());

        tryGetProfiles();

        mIvProfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIvProfile1.getTag().equals("available")){
                    Intent intent = new Intent(ProfileActivity.this, ProfileAddActivity.class);
                    intent.putExtra("profileId",mResult.get(0).getProfileId());
                    startActivity(intent);
                }else if(mIvProfile1.getTag().equals("exist")){
                    Intent intent = new Intent(ProfileActivity.this, MainLoadingActivity.class);
                    intent.putExtra("calling_activity","profile_activity");
                    intent.putExtra("profileId",mResult.get(0).getProfileId());
                    startActivity(intent);
                    finish();
                }
            }
        });

        mIvProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + mResult.size());
                if(mIvProfile2.getTag().equals("available")){
                    Log.d(TAG, "onClick: [if avilable] inside " + mResult.size());
                    Intent intent = new Intent(ProfileActivity.this, ProfileAddActivity.class);
                    intent.putExtra("profileId",mResult.get(1).getProfileId());
                    startActivity(intent);
                }else if(mIvProfile2.getTag().equals("exist")){
                    Intent intent = new Intent(ProfileActivity.this, MainLoadingActivity.class);
                    intent.putExtra("calling_activity","profile_activity");
                    intent.putExtra("profileId",mResult.get(1).getProfileId());
                    startActivity(intent);
                    finish();
                }

            }
        });

        mIvProfile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIvProfile3.getTag().equals("available")){
                    Intent intent = new Intent(ProfileActivity.this, ProfileAddActivity.class);
                    intent.putExtra("profileId",mResult.get(2).getProfileId());
                    startActivity(intent);
                }else if(mIvProfile3.getTag().equals("exist")){
                    Intent intent = new Intent(ProfileActivity.this, MainLoadingActivity.class);
                    intent.putExtra("calling_activity","profile_activity");
                    intent.putExtra("profileId",mResult.get(2).getProfileId());
                    startActivity(intent);
                    finish();
                }

            }
        });

        mIvProfile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIvProfile4.getTag().equals("available")){
                    Intent intent = new Intent(ProfileActivity.this, ProfileAddActivity.class);
                    intent.putExtra("profileId",mResult.get(3).getProfileId());
                    startActivity(intent);
                }else if(mIvProfile4.getTag().equals("exist")){
                    Intent intent = new Intent(ProfileActivity.this, MainLoadingActivity.class);
                    intent.putExtra("calling_activity","profile_activity");
                    intent.putExtra("profileId",mResult.get(3).getProfileId());
                    startActivity(intent);
                    finish();
                }
            }
        });

        mTvProfileChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvProfileChange.setVisibility(View.GONE);
                mTvProfileComplete.setVisibility(View.VISIBLE);

                if(mIvProfile1.getTag().equals("exist")){mIvEditProfile1.setVisibility(View.VISIBLE);
                    mIvProfile1.setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.MULTIPLY);}
                if(mIvProfile2.getTag().equals("exist")){mIvEditProfile2.setVisibility(View.VISIBLE);
                    mIvProfile2.setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.MULTIPLY);}
                if(mIvProfile3.getTag().equals("exist")){mIvEditProfile3.setVisibility(View.VISIBLE);
                    mIvProfile3.setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.MULTIPLY);}
                if(mIvProfile4.getTag().equals("exist")){mIvEditProfile4.setVisibility(View.VISIBLE);
                    mIvProfile4.setColorFilter(Color.parseColor("#2B2B2B"), PorterDuff.Mode.MULTIPLY);}

                mIvEditProfile1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, ProfileChangeActivity.class);
                        Log.d(TAG, "onClick: "+ mResult.get(0).getProfileId() +"아이디" +mResult.get(0).getProfileImgUrl() );
                        intent.putExtra("imgUrl",mResult.get(0).getProfileImgUrl());
                        intent.putExtra("profileId",mResult.get(0).getProfileId());
                        startActivity(intent);
                    }
                });

                mIvEditProfile2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, ProfileChangeActivity.class);
                        intent.putExtra("imgUrl",mResult.get(1).getProfileImgUrl());
                        Log.d(TAG, "onClick: 프로파일 " + mResult.get(1).getProfileId());
                        intent.putExtra("profileId",mResult.get(1).getProfileId());
                        startActivity(intent);
                    }
                });

                mIvEditProfile3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, ProfileChangeActivity.class);
                        intent.putExtra("imgUrl",mResult.get(2).getProfileImgUrl());
                        intent.putExtra("profileId",mResult.get(2).getProfileId());
                        startActivity(intent);
                    }
                });

                mIvEditProfile4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProfileActivity.this, ProfileChangeActivity.class);
                        intent.putExtra("imgUrl",mResult.get(3).getProfileImgUrl());
                        intent.putExtra("profileId",mResult.get(3).getProfileId());
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
                mIvProfile4.clearColorFilter();


                mTvProfileChange.setVisibility(View.VISIBLE);

            }
        });

    }



    private void setImgUrl(String imgUrl){

        mImgUrl = imgUrl;

    }

    private void tryGetProfiles() {
        Log.d(TAG, "tryGetProfiles: started.");
        showProgressDialog();

        final ProfileService profileService = new ProfileService(this);
        profileService.getProfiles();

    }

    public void setProfiles(List<result> result, int addAvailable) {

        Log.d(TAG, "setProfiles: " + addAvailable + result.get(0).getProfileImgUrl());

        mIvProfile1 = findViewById(R.id.profile_iv_profile1);
        mIvProfile2 = findViewById(R.id.profile_iv_profile2);
        mIvProfile3 = findViewById(R.id.profile_iv_profile3);
        mIvProfile4 = findViewById(R.id.profile_iv_profile4);

        int num = result.size();
        Drawable plusProfile = getResources().getDrawable(R.drawable.plus_profile);

        //for문 구현하는 법을 고려했으나 layout 접근 방법이 모호함
        //프로필 4개일경우
        if(addAvailable==0) {
            new DownloadImageTask(mIvProfile1).execute(result.get(0).getProfileImgUrl());
            Log.d(TAG, "setProfiles: 0");
            mIvProfile1.setTag(mTag_exist);
            new DownloadImageTask(mIvProfile2).execute(result.get(1).getProfileImgUrl());
            Log.d(TAG, "setProfiles: 1");
            mIvProfile2.setTag(mTag_exist);
            new DownloadImageTask(mIvProfile3).execute(result.get(2).getProfileImgUrl());
            Log.d(TAG, "setProfiles: 2");
            mIvProfile3.setTag(mTag_exist);
            new DownloadImageTask(mIvProfile4).execute(result.get(3).getProfileImgUrl());
            Log.d(TAG, "setProfiles: 3");
            mIvProfile4.setTag(mTag_exist);
        }
        //프로필 추가가능일 경우
        else{
            if(num==3){
                new DownloadImageTask(mIvProfile1).execute(result.get(0).getProfileImgUrl());
                new DownloadImageTask(mIvProfile2).execute(result.get(1).getProfileImgUrl());
                new DownloadImageTask(mIvProfile3).execute(result.get(2).getProfileImgUrl());
                mIvProfile4.setImageDrawable(plusProfile);

                mIvProfile1.setTag(mTag_exist);
                mIvProfile2.setTag(mTag_exist);
                mIvProfile3.setTag(mTag_exist);
                mIvProfile4.setTag(mTag_available);

            }else if(num==2){
                new DownloadImageTask(mIvProfile1).execute(result.get(0).getProfileImgUrl());
                new DownloadImageTask(mIvProfile2).execute(result.get(1).getProfileImgUrl());
                mIvProfile3.setImageDrawable(plusProfile);
                mIvProfile4.setImageDrawable(plusProfile);

                mIvProfile1.setTag(mTag_exist);
                mIvProfile2.setTag(mTag_exist);
                mIvProfile3.setTag(mTag_available);
                mIvProfile4.setTag(mTag_available);

            }else if(num==1){

                new DownloadImageTask(mIvProfile1).execute(result.get(0).getProfileImgUrl());
                mIvProfile2.setImageDrawable(plusProfile);
                mIvProfile3.setImageDrawable(plusProfile);
                mIvProfile4.setImageDrawable(plusProfile);

                mIvProfile1.setTag(mTag_exist);
                mIvProfile2.setTag(mTag_available);
                mIvProfile3.setTag(mTag_available);
                mIvProfile4.setTag(mTag_available);
                Log.d(TAG, "setProfiles: " + mIvProfile2.getTag());
            }else if(num==0){
                mIvProfile1.setImageDrawable(plusProfile);
                mIvProfile2.setImageDrawable(plusProfile);
                mIvProfile3.setImageDrawable(plusProfile);
                mIvProfile4.setImageDrawable(plusProfile);

                mIvProfile1.setTag(mTag_available);
                mIvProfile2.setTag(mTag_available);
                mIvProfile3.setTag(mTag_available);
                mIvProfile4.setTag(mTag_available);

            }
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void validateSuccess(List<result> result, int addAvailable) {
        hideProgressDialog();
        //사진아이디, 유알엘 담기, 추가유효 담기

        this.mResult = result;
        this.mAddAvailable = addAvailable;

        Log.d(TAG, "validateSuccess: size of result" + mResult.size());
        setProfiles(mResult, mAddAvailable);

    }

    @Override
    public void profileAddSuccess(boolean isSuccess) {
        Log.d(TAG, "profileAddSuccess: ");
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        Log.d(TAG, "validateFailure: ");
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
