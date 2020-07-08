package com.daniel.app.netfilx_clone.src.main.toptools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.main.toptools.utils.RecyclerViewAdapter;

import java.util.ArrayList;

public class GenreActivity extends Activity {

    private static final String TAG = "GenreActivity";

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mRecyclerViewAdapter;

    ArrayList<String> mGenreList = new ArrayList<>();

    ImageView mClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        Log.d(TAG, "onCreate: started.");

        mRecyclerView = findViewById(R.id.main_rv_genre);
        mClose = findViewById(R.id.genre_btn_close);

        //추가, 수정, 삭제 시에 해당 recyclerview의 크기 높이 변경 방지
        mRecyclerView.setHasFixedSize(true);
        //데이터 초기화
        mGenreList.clear();
        Intent intent = getIntent();


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

        String fromWhere = intent.getStringExtra("fromWhere");

        Log.d(TAG, "onCreate: intent" + fromWhere);

        if(("TVProgram").equals(intent.getStringExtra("fromWhere"))||("Movie").equals(intent.getStringExtra("fromWhere"))||
                ("Zzim").equals(intent.getStringExtra("fromWhere"))){
            Log.d(TAG, "onCreate: TVorMovie" + fromWhere);
            addTVProgramOrMovie();
        }else if(("TVProgramGenre").equals(intent.getStringExtra("fromWhere"))){
            Log.d(TAG, "onCreate: TVProgramGenre" + fromWhere);
            addTVProgramGenre();
        }else if(("MovieGenre").equals(intent.getStringExtra("fromWhere"))){
            Log.d(TAG, "onCreate: MovieGenre" + fromWhere);
            addMovieGenre();
        }

        for(int i =0;i<mGenreList.size();i++) {
            Log.d(TAG, "onCreate: finish" + mGenreList.get(i));
        }

        //리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //어댑터 연결
        mRecyclerViewAdapter = new RecyclerViewAdapter(mGenreList, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void addTVProgramOrMovie(){

        mGenreList.add("전체");
        mGenreList.add("TV 프로그램");
        mGenreList.add("영화");
        mGenreList.add("내가 찜한 콘텐츠");

    }

    private void addTVProgramGenre(){

        mGenreList.add("전체 장르");
        mGenreList.add("저장 가능");
        mGenreList.add("한국 드라마");
        mGenreList.add("미국 드라마");
        mGenreList.add("영국 드라마");
        mGenreList.add("아시아 드라마");
        mGenreList.add("버라이어티/예능");
        mGenreList.add("맥션");
        mGenreList.add("드라마");
        mGenreList.add("코미디");
        mGenreList.add("스릴러");
        mGenreList.add("호러");
        mGenreList.add("SF");
        mGenreList.add("판타지");
        mGenreList.add("키즈");
        mGenreList.add("청춘/하이틴");
        mGenreList.add("애니");
        mGenreList.add("다큐시리즈");
        mGenreList.add("역사");
        mGenreList.add("자연과학");
        mGenreList.add("음성 지원");

    }

    private void addMovieGenre(){

        mGenreList.add("전체 장르");
        mGenreList.add("저장 가능");
        mGenreList.add("한국");
        mGenreList.add("미국 영화");
        mGenreList.add("외국 작품");
        mGenreList.add("영화제 수상작");
        mGenreList.add("인디");
        mGenreList.add("어린이/가족");
        mGenreList.add("액션");
        mGenreList.add("코미디");
        mGenreList.add("SF");
        mGenreList.add("판타지");
        mGenreList.add("스릴러");
        mGenreList.add("호러");
        mGenreList.add("범죄");
        mGenreList.add("로맨스");
        mGenreList.add("드라마");
        mGenreList.add("다큐멘터리");
        mGenreList.add("역사");
        mGenreList.add("자연과학");
        mGenreList.add("일본 애니메이션");
        mGenreList.add("음악/뮤지컬");
        mGenreList.add("고전");
        mGenreList.add("음성 지원");
    }

}
