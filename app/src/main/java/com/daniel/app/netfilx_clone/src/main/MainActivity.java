package com.daniel.app.netfilx_clone.src.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.advertisement.AdvertisementActivity;
import com.daniel.app.netfilx_clone.src.main.fragment.ComingSoonFragment;
import com.daniel.app.netfilx_clone.src.main.fragment.MainFragment;
import com.daniel.app.netfilx_clone.src.main.fragment.MoreFragment;
import com.daniel.app.netfilx_clone.src.main.fragment.SavedContentsFragment;
import com.daniel.app.netfilx_clone.src.main.fragment.SearchFragment;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.toptools.GenreActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.main.utils.MainLoadingActivity;
import com.daniel.app.netfilx_clone.src.splash.SplashActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements MainActivityView {

    private static final String TAG = "MainActivity";

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private MainFragment mainFragment = new MainFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ComingSoonFragment comingSoonFragement = new ComingSoonFragment();
    private SavedContentsFragment savedContentsFragment = new SavedContentsFragment();
    private MoreFragment moreFragment = new MoreFragment();

    int mProfileId;

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

        Intent intent = getIntent();
        mProfileId = intent.getIntExtra("profileId",-1);

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

        //fragment

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, mainFragment).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home: {
                        transaction.replace(R.id.frame_layout, mainFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_search: {
                        transaction.replace(R.id.frame_layout, searchFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_coming_soon: {
                        transaction.replace(R.id.frame_layout, comingSoonFragement).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_saved_contents: {
                        transaction.replace(R.id.frame_layout, savedContentsFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_else: {
                        transaction.replace(R.id.frame_layout, moreFragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });




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

                        Intent intent = new Intent(MainActivity.this, MainLoadingActivity.class);
                        intent.putExtra("calling_activity","main_activity");
                        intent.putExtra("profileId",mProfileId);
                        startActivity(intent);

                        //setupMovieTopToolBar();
                        //클릭리스너 달기
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
