package com.daniel.app.netfilx_clone.src.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.main.toptools.models.Contents;
import com.daniel.app.netfilx_clone.src.main.toptools.utils.RecyclerViewAdapter;
import com.daniel.app.netfilx_clone.src.search.utils.SearchRecyclerViewAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";

    RecyclerView mRecyclerView;
    SearchRecyclerViewAdapter mRecyclerViewAdapter;

    ArrayList<Contents> mContentsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d(TAG, "onCreate: started.");

        mRecyclerView = findViewById(R.id.search_rv_contents);

        mContentsList.clear();

        int imgId = Resources.getSystem().getIdentifier("poster_sample", "drawable", "android");
        String tempTitle = "아무거나";

        Contents temp = new Contents();

        temp.setTitle(tempTitle);
        temp.setImgURL(imgId);

        for(int i=0;i<10;i++) {
            mContentsList.add(temp);
        }

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
}
