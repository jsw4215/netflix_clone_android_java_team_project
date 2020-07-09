package com.daniel.app.netfilx_clone.src.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.comingsoon.ComingSoonActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.ZzimActivity;
import com.daniel.app.netfilx_clone.src.main.utils.BottomNavigationViewHelper;
import com.daniel.app.netfilx_clone.src.main.utils.LogoutDialogActivity;
import com.daniel.app.netfilx_clone.src.profile.ProfileService;
import com.daniel.app.netfilx_clone.src.profile.interfaces.ProfileActivityView;
import com.daniel.app.netfilx_clone.src.profile.models.result;
import com.daniel.app.netfilx_clone.src.signin.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class MoreActivity extends BaseActivity implements ProfileActivityView {

    private static final String TAG = "MoreActivity";

    private static final int ACTIVITY_NUM = 4;
    Context mContext = MoreActivity.this;
    BottomNavigationView mBottomNavigationView;
    TextView mLogout;
    LinearLayout mLlZzim;
    int mProfileId;
    ImageView mIvProfile1;
    TextView mTvProfile1;
    ImageView mIvProfile2;
    TextView mTvProfile2;
    ImageView mIvProfile3;
    TextView mTvProfile3;
    ImageView mIvProfile4;
    TextView mTvProfile4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        mBottomNavigationView = findViewById(R.id.nav_view);
        mLogout = findViewById(R.id.more_tv_logout);
        mLlZzim = findViewById(R.id.more_ll_zzim);
        mIvProfile1 = findViewById(R.id.profile_iv_profile1);
        mTvProfile1 = findViewById(R.id.profile_tv_profile1);
        mIvProfile2 = findViewById(R.id.profile_iv_profile2);
        mTvProfile2 = findViewById(R.id.profile_tv_profile2);
        mIvProfile3 = findViewById(R.id.profile_iv_profile3);
        mTvProfile3 = findViewById(R.id.profile_tv_profile3);

        mProfileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));

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

        setupBottomNavigationView();

        tryGetProfiles();

        mLlZzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, ZzimActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: logout");
                Intent intent = new Intent(MoreActivity.this, LogoutDialogActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);

            }
        });

    }

    private void tryGetProfiles() {
        Log.d(TAG, "tryGetProfiles: started.");
        showProgressDialog();

        final ProfileService profileService = new ProfileService(this);
        profileService.getProfiles();

    }

    private void setupBottomNavigationView(){
        Log.d(TAG,"setupBottomnavView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(mBottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(mContext, this, mBottomNavigationView);
        Menu menu = mBottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    @Override
    public void validateSuccess(List<result> result, int addAvailable) {
        Log.d(TAG, "validateSuccess: ");

        hideProgressDialog();

        setProfile(result);

    }

    private void setProfile(List<result> result) {
        Log.d(TAG, "setProfile: " + result.get(0).getProfileId());

        if(result.size()==3||result.size()>3){

        mTvProfile1.setText(String.valueOf(result.get(0).getProfileId()));
        mTvProfile2.setText(String.valueOf(result.get(1).getProfileId()));
        mTvProfile3.setText(String.valueOf(result.get(2).getProfileId()));
        Glide.with(mContext).load(result.get(0).getProfileImgUrl()).into(mIvProfile1);
        Glide.with(mContext).load(result.get(1).getProfileImgUrl()).into(mIvProfile2);
        Glide.with(mContext).load(result.get(2).getProfileImgUrl()).into(mIvProfile3);
        }else if(result.size()==2){
            mTvProfile1.setText(String.valueOf(result.get(0).getProfileId()));
            mTvProfile2.setText(String.valueOf(result.get(1).getProfileId()));
            Glide.with(mContext).load(result.get(0).getProfileImgUrl()).into(mIvProfile1);
            Glide.with(mContext).load(result.get(1).getProfileImgUrl()).into(mIvProfile2);
        }else if(result.size()==1){
            mTvProfile1.setText(String.valueOf(result.get(0).getProfileId()));
            Glide.with(mContext).load(result.get(0).getProfileImgUrl()).into(mIvProfile1);
        }

    }

    @Override
    public void profileAddSuccess(boolean isSuccess) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
