package com.daniel.app.netfilx_clone.src.comingsoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.comingsoon.models.MovieDetail;
import com.daniel.app.netfilx_clone.src.comingsoon.utils.ComingRecyclerViewAdapter;
import com.daniel.app.netfilx_clone.src.main.toptools.models.Contents;
import com.daniel.app.netfilx_clone.src.search.utils.SearchRecyclerViewAdapter;

import java.util.ArrayList;

public class ComingSoonActivity extends AppCompatActivity {

    private static final String TAG = "ComingSoonActivity";

    RecyclerView mRecyclerView;
    ComingRecyclerViewAdapter mRecyclerViewAdapter;

    ArrayList<MovieDetail> mContentsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);

        Log.d(TAG, "onCreate: started.");

        mRecyclerView = findViewById(R.id.coming_rv);

        mContentsList.clear();

        String tempTitle = "아무거나";

        MovieDetail temp = new MovieDetail();

        temp.setTitle(tempTitle);

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
        mRecyclerViewAdapter = new ComingRecyclerViewAdapter(mContentsList, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }
}