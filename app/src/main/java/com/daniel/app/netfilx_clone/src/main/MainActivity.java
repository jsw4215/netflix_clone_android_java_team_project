package com.daniel.app.netfilx_clone.src.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.single.SingleActivity;
import com.daniel.app.netfilx_clone.src.main.single.VideoActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.GenreActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.ZzimActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.main.utils.BottomNavigationViewHelper;
import com.daniel.app.netfilx_clone.src.main.utils.MainLoadingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView {

    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;

    int mProfileId;

    Button mPlay;
    TextView mTvTopMovie;
    TextView mTvTopTv;
    TextView mTvTopZzim;
    Toolbar mTopContainer;
    ViewFlipper mViewFlipper;
    LinearLayout mLinearLayout;
    LinearLayout mLlHeart;
    ImageView mIvHeart;
    BottomNavigationView mBottomNavigationView;
    Context mContext = MainActivity.this;
    String mTag_Check = "check";
    String mTag_Plus = "plus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_main);
        mBottomNavigationView = findViewById(R.id.nav_view);
        mTvTopMovie = findViewById(R.id.main_tv_top_movie);
        mTvTopTv = findViewById(R.id.main_tv_top_program);
        mTvTopZzim = findViewById(R.id.main_tv_top_zzim);
        mTopContainer = findViewById(R.id.main_top_container);
        mViewFlipper = findViewById(R.id.main_vf_top);
        mLinearLayout = findViewById(R.id.main_lin_top);
        mPlay = findViewById(R.id.main_btn_play);
        mLlHeart = findViewById(R.id.main_ll_heart);
        mIvHeart = findViewById(R.id.main_iv_heart);

        mProfileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));

        int[] topToolId ={R.id.main_tv_top_program, R.id.main_tv_top_movie, R.id.main_tv_top_zzim};
        View[] views = {mViewFlipper.getChildAt(0).findViewById(R.id.main_tv_top_program),
                mViewFlipper.getChildAt(0).findViewById(R.id.main_tv_top_zzim),
                mViewFlipper.getChildAt(0).findViewById(R.id.main_tv_top_movie),
                mViewFlipper.getChildAt(1).findViewById(R.id.main_tv_top_movie),
                mViewFlipper.getChildAt(1).findViewById(R.id.main_tv_top_zzim),
                mViewFlipper.getChildAt(2).findViewById(R.id.main_tv_top_program),
                mViewFlipper.getChildAt(2).findViewById(R.id.main_tv_top_zzim)};

        //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setupBottomNavigationView();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(0);
        }

        mTvTopTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewFlipper.setDisplayedChild(2);
                        //setupProgramTopToolBar();
                    }
                },100);

            }
        });

        mViewFlipper.getChildAt(2).findViewById(R.id.main_tool_top_icon_TV).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewFlipper.setDisplayedChild(0);
                        //setupProgramTopToolBar();
                    }
                },100);

            }
        });


        mViewFlipper.getChildAt(2).findViewById(R.id.main_tv_top_genre_TV).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                intent.putExtra("fromWhere","TVProgramGenre");
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);

            }
        });

        mTvTopMovie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mTvTopTv.setVisibility(View.INVISIBLE);
                mTvTopZzim.setVisibility(View.INVISIBLE);
                //mTvTopMovie = findViewById(R.id.main_tv_top_movie);
                mTvTopMovie.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_top_to_right));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewFlipper.setDisplayedChild(1);

                        Intent intent = new Intent(MainActivity.this, MainLoadingActivity.class);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        intent.putExtra("calling_activity","main_activity");
                        intent.putExtra("profileId",mProfileId);
                        startActivity(intent);

                        //setupMovieTopToolBar();
                        //클릭리스너 달기
                    }
               },300);

            }
        });

        mTvTopZzim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mTvTopTv.setVisibility(View.INVISIBLE);
                mTvTopMovie.setVisibility(View.INVISIBLE);
                mTvTopZzim.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_top_further_right));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(MainActivity.this, ZzimActivity.class);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        intent.putExtra("calling_activity","main_activity");
                        intent.putExtra("profileId",mProfileId);
                        startActivity(intent);

                        //setupMovieTopToolBar();
                        //클릭리스너 달기
                    }
                },500);

            }
        });

        mLlHeart.setTag(mTag_Check);

        mLlHeart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLlHeart.getTag().equals(mTag_Check)){
                    mIvHeart.setImageResource(R.drawable.ic_add_white);
                    mLlHeart.setTag(mTag_Plus);
                }else{
                    mIvHeart.setImageResource(R.drawable.ic_white_check);
                    mLlHeart.setTag(mTag_Check);
                }

            }
        });

        final String dummy_url = "https://firebasestorage.googleapis.com/v0/b/netflix-51e85.appspot.com/o/videos%2FInception%20%EC%98%81%ED%99%94%20%EC%98%88%EA%B3%A0%ED%8E%B8.mp4?alt=media&token=0ad61c37-8118-4aff-8f49-a0935d5065ed";

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                intent.putExtra("videoUri",dummy_url);
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


    private void setupMovieTopToolBar(){

        if(mViewFlipper.getChildAt(2)!=null) {

            Log.d(TAG, "setupMovieTopToolBar: movie clicklistener setted.");

            mViewFlipper.getChildAt(2).findViewById(R.id.main_tv_top_movie2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                    intent.putExtra("fromWhere", "Movie");
                    startActivity(intent);
                }
            });

            mViewFlipper.getChildAt(2).findViewById(R.id.main_tv_top_genre2).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                    intent.putExtra("fromWhere", "MovieGenre");
                    startActivity(intent);
                }
            });

        }

    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void zzzimSuccess(ZzimResponse zzimResponse) {
        Log.d(TAG, "zzzimSuccess: ");
    }

    @Override
    public void top10Success(Top10Response top10Response) {
        Log.d(TAG, "top10Success: ");
    }

    @Override
    public void recommendSuccess(RecommendResponse recommendResponse) {
        Log.d(TAG, "recommendSuccess: ");
    }

    @Override
    public void netflixOriginalSuccess(NetflixOriginalResponse netflixOriginalResponse) {

    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }



}
