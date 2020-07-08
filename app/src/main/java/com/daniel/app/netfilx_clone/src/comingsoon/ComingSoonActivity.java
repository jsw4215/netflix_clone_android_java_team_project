package com.daniel.app.netfilx_clone.src.comingsoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.comingsoon.models.MovieDetail;
import com.daniel.app.netfilx_clone.src.comingsoon.utils.ComingRecyclerViewAdapter;
import com.daniel.app.netfilx_clone.src.main.toptools.models.Contents;
import com.daniel.app.netfilx_clone.src.main.utils.BottomNavigationViewHelper;
import com.daniel.app.netfilx_clone.src.search.SearchActivity;
import com.daniel.app.netfilx_clone.src.search.utils.SearchRecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class ComingSoonActivity extends AppCompatActivity {

    private static final String TAG = "ComingSoonActivity";
    private static final int ACTIVITY_NUM = 2;
    Context mContext = ComingSoonActivity.this;

    RecyclerView mRecyclerView;
    ComingRecyclerViewAdapter mRecyclerViewAdapter;
    BottomNavigationView mBottomNavigationView;

    ArrayList<MovieDetail> mContentsList = new ArrayList<>();

    int mProfileId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);

        Log.d(TAG, "onCreate: started.");

        mRecyclerView = findViewById(R.id.coming_rv);
        mBottomNavigationView = findViewById(R.id.nav_view);
        mProfileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));

        mContentsList.clear();

        String tempTitle = "아무거나";

        MovieDetail temp = new MovieDetail();

        temp.setTitle(tempTitle);

        for(int i=0;i<10;i++) {
            mContentsList.add(temp);
        }

        setupBottomNavigationView();

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

        //추가, 수정, 삭제 시에 해당 recyclerview의 크기 높이 변경 방지
        mRecyclerView.setHasFixedSize(true);

//리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //어댑터 연결
        mRecyclerViewAdapter = new ComingRecyclerViewAdapter(mContentsList, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }

    private void setupBottomNavigationView(){
        Log.d(TAG,"setupBottomnavView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(mBottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(mContext, this, mBottomNavigationView);
        Menu menu = mBottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }




}