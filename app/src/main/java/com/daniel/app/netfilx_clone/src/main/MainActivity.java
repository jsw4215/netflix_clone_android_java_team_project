package com.daniel.app.netfilx_clone.src.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.advertisement.AdvertisementActivity;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.toptools.GenreActivity;
import com.daniel.app.netfilx_clone.src.splash.SplashActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements MainActivityView {

    private static final String TAG = "MainActivity";

    TextView mTvTopMovie;
    TextView mTvTopTv;
    TextView mTvTopGenre;
    Toolbar mTopContainer;
    ViewFlipper mViewFlipper;
    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTvTopMovie = findViewById(R.id.main_tv_top_movie);
        mTvTopTv = findViewById(R.id.main_tv_top_program);
        mTvTopGenre = findViewById(R.id.main_tv_top_genre);
        mTopContainer = findViewById(R.id.main_top_container);
        mViewFlipper = findViewById(R.id.main_vf_top);
        mLinearLayout = findViewById(R.id.main_lin_top);

        int[] topToolId ={R.id.main_tv_top_program, R.id.main_tv_top_movie, R.id.main_tv_top_genre};
        View[] views = {mViewFlipper.getChildAt(0).findViewById(R.id.main_tv_top_program),
                mViewFlipper.getChildAt(0).findViewById(R.id.main_tv_top_genre),
                mViewFlipper.getChildAt(0).findViewById(R.id.main_tv_top_movie),
                mViewFlipper.getChildAt(1).findViewById(R.id.main_tv_top_movie),
                mViewFlipper.getChildAt(1).findViewById(R.id.main_tv_top_genre),
                mViewFlipper.getChildAt(2).findViewById(R.id.main_tv_top_program),
                mViewFlipper.getChildAt(2).findViewById(R.id.main_tv_top_genre)};

        //
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

        mTvTopGenre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //내가찜한 콘텐츠 레이아웃
            }
        });

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

        mTvTopMovie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mTvTopTv.setVisibility(View.INVISIBLE);
                //mTvTopMovie = findViewById(R.id.main_tv_top_movie);
                mTvTopMovie.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_top_to_right));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mViewFlipper.setDisplayedChild(1);
                        //setupMovieTopToolBar();
                        //클릭리스너 달기기
                    }
               },300);

            }
        });




    }

    private void setupProgramTopToolBar(){

        if(mViewFlipper.getChildAt(1)!=null) {
            Log.d(TAG, "setupProgramTopToolBar: clicklistener is setted.");

            mViewFlipper.getChildAt(1).findViewById(R.id.main_tv_top_program1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                    intent.putExtra("fromWhere", "TVProgram");
                    startActivity(intent);
                }
            });

            mViewFlipper.getChildAt(1).findViewById(R.id.main_tv_top_genre1).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                    intent.putExtra("fromWhere", "TVProgramGenre");
                    startActivity(intent);
                }
            });
        }

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



    private void tryGetTest() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_hello_world:
                tryGetTest();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
