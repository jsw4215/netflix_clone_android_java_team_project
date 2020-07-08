package com.daniel.app.netfilx_clone.src.main.toptools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.main.MainService;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResult;
import com.daniel.app.netfilx_clone.src.main.utils.BottomNavigationViewHelper;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class ZzimActivity extends BaseActivity implements MainActivityView {

    private static final String TAG = "ZzimActivity";
    private static final int ACTIVITY_NUM = 0;

    int mProfileId;

    ImageView mZzimPoster1;
    ImageView mZzimPoster2;
    ImageView mZzimPoster3;
    ImageView mZzimPoster4;
    ImageView mZzimPoster5;
    ImageView mZzimPoster6;
    ImageView mZzimPoster7;
    ImageView mZzimPoster8;
    ImageView mZzimPoster9;
    ImageView mTopIcon;

    TextView mTvTopZzim;

    BottomNavigationView mBottomNavigationView;
    Context mContext = ZzimActivity.this;


    ZzimResponse mZzimResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzim);
        mBottomNavigationView = findViewById(R.id.nav_view);
        mTopIcon = findViewById(R.id.main_tool_top_icon);
        mTvTopZzim = findViewById(R.id.main_tv_top_zzim);

        Intent intent = getIntent();
        mProfileId = intent.getIntExtra("profileId",2);

        mProfileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));

        tryGetZzim(mProfileId);

        setupBottomNavigationView();

        mTopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZzimActivity.this, MainActivity.class);
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTvTopZzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZzimActivity.this, GenreActivity.class);
                intent.putExtra("fromWhere","Zzim");
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

    }

    private void setupBottomNavigationView(){
        Log.d(TAG,"setupBottomnavView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(mBottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(mContext, this, mBottomNavigationView);
        Menu menu = mBottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    private void setImage(ZzimResponse zzimResponse){

        mZzimPoster1 = findViewById(R.id.zzim_poster1);
        mZzimPoster2 = findViewById(R.id.zzim_poster2);
        mZzimPoster3 = findViewById(R.id.zzim_poster3);
        mZzimPoster4 = findViewById(R.id.zzim_poster4);
        mZzimPoster5 = findViewById(R.id.zzim_poster5);
        mZzimPoster6 = findViewById(R.id.zzim_poster6);
        mZzimPoster7 = findViewById(R.id.zzim_poster7);
        mZzimPoster8 = findViewById(R.id.zzim_poster8);
        mZzimPoster9 = findViewById(R.id.zzim_poster9);

        new DownloadImageTask(mZzimPoster1).execute(zzimResponse.getResult().get(0).getThumbnailImgUrl());
        new DownloadImageTask(mZzimPoster2).execute(zzimResponse.getResult().get(1).getThumbnailImgUrl());
        new DownloadImageTask(mZzimPoster3).execute(zzimResponse.getResult().get(2).getThumbnailImgUrl());
        new DownloadImageTask(mZzimPoster4).execute(zzimResponse.getResult().get(3).getThumbnailImgUrl());
        new DownloadImageTask(mZzimPoster5).execute(zzimResponse.getResult().get(4).getThumbnailImgUrl());
        new DownloadImageTask(mZzimPoster6).execute(zzimResponse.getResult().get(5).getThumbnailImgUrl());
        new DownloadImageTask(mZzimPoster7).execute(zzimResponse.getResult().get(6).getThumbnailImgUrl());
        new DownloadImageTask(mZzimPoster8).execute(zzimResponse.getResult().get(7).getThumbnailImgUrl());

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

    }

    private void tryGetZzim(int mProfileId) {
            showProgressDialog();

            final MainService mainService = new MainService(this);
            mainService.getZzim(mProfileId);

    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void zzzimSuccess(ZzimResponse zzimResponse) {
        Log.d(TAG, "zzzimSuccess: ");
        hideProgressDialog();

        this.mZzimResponse = zzimResponse;

        setImage(zzimResponse);

    }

    @Override
    public void top10Success(Top10Response top10Response) {

    }

    @Override
    public void recommendSuccess(RecommendResponse recommendResponse) {

    }

    @Override
    public void netflixOriginalSuccess(NetflixOriginalResponse netflixOriginalResponse) {

    }

    @Override
    public void validateFailure(String message) {

    }
}