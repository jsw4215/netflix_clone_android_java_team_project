package com.daniel.app.netfilx_clone.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.interfaces.MainActivityView;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResponse;
import com.daniel.app.netfilx_clone.src.main.models.NetflixOriginalResult;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResponse;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResult;
import com.daniel.app.netfilx_clone.src.main.models.Top10Response;
import com.daniel.app.netfilx_clone.src.main.models.Top10Result;
import com.daniel.app.netfilx_clone.src.main.single.SingleActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResponse;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResult;
import com.daniel.app.netfilx_clone.src.main.utils.NetflixOriginRecyViewAdapter;
import com.daniel.app.netfilx_clone.src.main.utils.RecommRecyViewAdapter;
import com.daniel.app.netfilx_clone.src.main.utils.ZzimRecyViewAdapter;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;

import java.util.List;

public class MovieActivity extends BaseActivity implements MainActivityView {
    private static final String TAG = "MovieActivity";

    int mProfileId;

    Poster mPoster;
    RecyclerView mZzimRecyclerView;
    RecyclerView mRecommRecyclerView;
    RecyclerView mNetflixOriginRecyView;
    List<ZzimResult> mZzimResult;
    List<Top10Result> mTop10Result;
    ZzimRecyViewAdapter mZzimRecyViewAdapter;
    RecommRecyViewAdapter mRecommRecyViewAdapter;
    NetflixOriginRecyViewAdapter mNetflixOriginRecyViewAdapter;
    ImageView mTop1;
    ImageView mTop2;
    ImageView mTop3;
    ImageView mTop4;
    ImageView mTop5;
    ImageView mTop6;
    ImageView mTop7;
    ImageView mTop8;
    ImageView mTop9;
    ImageView mTop10;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = getIntent();
        mProfileId = intent.getIntExtra("profileId",-1);

        mPoster = new Poster(getApplicationContext());
        //final View ZzimList = findViewById(R.id.movie_inc_zzim_list);
        //EditText editText = (EditText)emailView.findViewById(R.id.contactEmailEdit);



        //mZzimList.clear();

        tryGetZzim(mProfileId);
        tryGetTop10(mProfileId);
        tryGetRecommend(mProfileId);
        tryGetNetflixOriginal(mProfileId);




    }

    private void setNetflixOriginToAdapter(List<NetflixOriginalResult> netflixOriginalResults) {

        mNetflixOriginRecyView = findViewById(R.id.movie_rv_netflix_origin);

        mNetflixOriginRecyView.setHasFixedSize(true);

        //리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mNetflixOriginRecyView.setLayoutManager(layoutManager);
        //어댑터 연결
        mNetflixOriginRecyViewAdapter = new NetflixOriginRecyViewAdapter(netflixOriginalResults, this);
        mNetflixOriginRecyView.setAdapter(mNetflixOriginRecyViewAdapter);

    }



    //다른 부분과 데이터가 같아 한번에 가능하나, 데이터가 다를 경우를 가정해 각 리스트별 어댑터과 클래스를 만들어 따로 연결함
    private void setRecommendToAdapter(List<RecommendResult> recommendResult) {

        mRecommRecyclerView = findViewById(R.id.movie_rv_recommend);

        mRecommRecyclerView.setHasFixedSize(true);

        //리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecommRecyclerView.setLayoutManager(layoutManager);
        //어댑터 연결
        mRecommRecyViewAdapter = new RecommRecyViewAdapter(recommendResult, this);
        mRecommRecyclerView.setAdapter(mRecommRecyViewAdapter);

    }

    private void setZzimDataToAdapter(List<ZzimResult> ZzimResult) {

        mZzimRecyclerView = findViewById(R.id.movie_rv_zzim);
        //추가, 수정, 삭제 시에 해당 recyclerview의 크기 높이 변경 방지
        mZzimRecyclerView.setHasFixedSize(true);

        //리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mZzimRecyclerView.setLayoutManager(layoutManager);
        //어댑터 연결
        mZzimRecyViewAdapter = new ZzimRecyViewAdapter(ZzimResult, this);
        mZzimRecyclerView.setAdapter(mZzimRecyViewAdapter);

    }



    private void setTop10Data(final List<Top10Result> mTop10Result) {

        //랭킹부분은 1~10 숫자 부분 해결이 난해해 일일이 추가

        mTop1 = findViewById(R.id.top10_1);
        mTop2 = findViewById(R.id.top10_2);
        mTop3 = findViewById(R.id.top10_3);
        mTop4 = findViewById(R.id.top10_4);
        mTop5 = findViewById(R.id.top10_5);
        mTop6 = findViewById(R.id.top10_6);
        mTop7 = findViewById(R.id.top10_7);
        mTop8 = findViewById(R.id.top10_8);
        mTop9 = findViewById(R.id.top10_9);
        mTop10 = findViewById(R.id.top10_10);


        new DownloadImageTask(mTop1).execute(mTop10Result.get(0).getThumbnailImgUrl());
        new DownloadImageTask(mTop2).execute(mTop10Result.get(1).getThumbnailImgUrl());
        new DownloadImageTask(mTop3).execute(mTop10Result.get(2).getThumbnailImgUrl());
        new DownloadImageTask(mTop4).execute(mTop10Result.get(3).getThumbnailImgUrl());
        new DownloadImageTask(mTop5).execute(mTop10Result.get(4).getThumbnailImgUrl());
        new DownloadImageTask(mTop6).execute(mTop10Result.get(5).getThumbnailImgUrl());
        new DownloadImageTask(mTop7).execute(mTop10Result.get(6).getThumbnailImgUrl());
        new DownloadImageTask(mTop8).execute(mTop10Result.get(7).getThumbnailImgUrl());
        new DownloadImageTask(mTop9).execute(mTop10Result.get(8).getThumbnailImgUrl());
        new DownloadImageTask(mTop10).execute(mTop10Result.get(9).getThumbnailImgUrl());

        mTop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(0).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(1).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(2).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(3).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTop5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(4).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });
        mTop6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(5).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTop7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(6).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTop8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(7).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTop9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(8).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });

        mTop10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, SingleActivity.class);
                intent.putExtra("contentsId",mTop10Result.get(9).getContentsId());
                intent.putExtra("profileId",mProfileId);
                startActivity(intent);
            }
        });



    }


    private void tryGetZzim(int profileId) {
        Log.d(TAG, "tryGetZzim: ");
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getZzim(profileId);

    }

    private void tryGetTop10(int profileId){
        Log.d(TAG, "tryGetTop10: " + profileId);
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTop10(profileId);

    }

    private void tryGetRecommend(int profileId){
        Log.d(TAG, "tryGetRecommend: ");
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getRecommend(profileId);

    }

    private void tryGetNetflixOriginal(int profileId){
        Log.d(TAG, "tryGetNetflixOriginal: ");
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getNetflixOriginal(profileId);

    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void zzzimSuccess(ZzimResponse zzimResponse) {
        hideProgressDialog();
        Log.d(TAG, "zzzimSuccess: " + zzimResponse.getResult().get(0).getThumbnailImgUrl());

        mZzimResult = zzimResponse.getResult();

        setZzimDataToAdapter(mZzimResult);
    }


    @Override
    public void top10Success(Top10Response top10Response) {
        hideProgressDialog();
        Log.d(TAG, "top10Success: " + top10Response.getMessage());

        mTop10Result = top10Response.getResult();

        setTop10Data(mTop10Result);
    }


    @Override
    public void recommendSuccess(RecommendResponse recommendResponse) {
        hideProgressDialog();
        Log.d(TAG, "recommendSuccess: " + recommendResponse.getRecommendContents().get(0).getThumbnailImgUrl());

        setRecommendToAdapter(recommendResponse.getRecommendContents());
    }


    @Override
    public void netflixOriginalSuccess(NetflixOriginalResponse netflixOriginalResponse) {
        hideProgressDialog();
        Log.d(TAG, "netflixOriginalSuccess: ");

        setNetflixOriginToAdapter(netflixOriginalResponse.getResult());
    }

    @Override
    public void validateFailure(String message) {

    }
}