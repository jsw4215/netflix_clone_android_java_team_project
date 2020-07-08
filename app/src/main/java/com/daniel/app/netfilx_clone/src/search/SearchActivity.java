package com.daniel.app.netfilx_clone.src.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.models.Contents;
import com.daniel.app.netfilx_clone.src.main.toptools.utils.RecyclerViewAdapter;
import com.daniel.app.netfilx_clone.src.main.utils.BottomNavigationViewHelper;
import com.daniel.app.netfilx_clone.src.search.utils.SearchRecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private static final int ACTIVITY_NUM = 1;
    Context mContext = SearchActivity.this;

    RecyclerView mRecyclerView;
    SearchRecyclerViewAdapter mRecyclerViewAdapter;
    BottomNavigationView mBottomNavigationView;


    ArrayList<Contents> mContentsList = new ArrayList<>();

    int mProfileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d(TAG, "onCreate: started.");

        mBottomNavigationView = findViewById(R.id.nav_view);
        mRecyclerView = findViewById(R.id.search_rv_contents);

        mProfileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));

        mContentsList.clear();

        int imgId = Resources.getSystem().getIdentifier("poster_sample", "drawable", "android");
        String tempTitle = "아무거나";

        Contents temp = new Contents();

        temp.setTitle(tempTitle);
        temp.setImgURL(imgId);

        for(int i=0;i<10;i++) {
            mContentsList.add(temp);
        }

        setupBottomNavigationView();

//추가, 수정, 삭제 시에 해당 recyclerview의 크기 높이 변경 방지
        mRecyclerView.setHasFixedSize(true);

//리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //어댑터 연결
        mRecyclerViewAdapter = new SearchRecyclerViewAdapter(mContentsList, this);
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
