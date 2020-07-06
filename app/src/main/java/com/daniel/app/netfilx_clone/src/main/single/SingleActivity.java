package com.daniel.app.netfilx_clone.src.main.single;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.single.interfaces.SingleActivityView;
import com.daniel.app.netfilx_clone.src.main.single.models.SingleResponse;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;

public class SingleActivity extends BaseActivity implements SingleActivityView {

    private static final String TAG = "SingleActivity";

    LinearLayout mLinearLayout;
    ImageView mLike;
    ImageView mBackArrow;
    ImageView mPoster;
    ImageView mSimilarPoster1;
    ImageView mSimilarPoster2;
    ImageView mSimilarPoster3;
    ImageView mSimilarPoster4;
    ImageView mSimilarPoster5;
    ImageView mSimilarPoster6;

    SingleResponse mSingleResponse;

    TextView mPercent;
    TextView mYear;
    TextView mAge;
    TextView mRuntime;
    TextView mPlayBtn;
    TextView mPlayText;
    TextView mSaveBtn;
    TextView mSaveText;
    TextView mContents;
    TextView mAppearance;
    TextView mDirector;

    int mContentsId;
    int mProfileId;

    Point p = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        Log.d(TAG, "onCreate: started.");

        Intent intent = getIntent();
        mContentsId = intent.getIntExtra("contentsId",1);
        mProfileId = intent.getIntExtra("profileId",2);

        tryGetSingleInfo(mProfileId, mContentsId);



        //like 평가 공유x

        mLinearLayout = findViewById(R.id.single_total_view);

        mLike = findViewById(R.id.single_like_icon);

        mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Open popup window
                if (p != null)
                    showPopup(SingleActivity.this, p);
            }
        });

    }

    private void layoutInit() {

        Log.d(TAG, "layoutInit: started." + mSingleResponse.getContentsResult().getContentsInfo().getThumbnailImgUrl());

        mBackArrow = findViewById(R.id.single_iv_arrow_back);
        mPoster = findViewById(R.id.single_iv_poster);
        mPercent = findViewById(R.id.sinlge_tv_percent);
        mYear = findViewById(R.id.single_tv_year);
        mAge = findViewById(R.id.single_tv_old);
        mRuntime = findViewById(R.id.single_tv_runtime);
        mPlayBtn = findViewById(R.id.single_btn_play);
        mPlayText = findViewById(R.id.single_tv_play);
        mSaveBtn = findViewById(R.id.single_btn_save);
        mSaveText = findViewById(R.id.single_tv_save);
        mContents = findViewById(R.id.single_tv_context);
        mAppearance = findViewById(R.id.single_tv_appearance);
        mDirector = findViewById(R.id.single_tv_director);
        mSimilarPoster1 = findViewById(R.id.single_similar_poster1);
        mSimilarPoster2 = findViewById(R.id.single_similar_poster2);
        mSimilarPoster3 = findViewById(R.id.single_similar_poster3);
        mSimilarPoster4 = findViewById(R.id.single_similar_poster4);
        mSimilarPoster5 = findViewById(R.id.single_similar_poster5);
        mSimilarPoster6 = findViewById(R.id.single_similar_poster6);

        new DownloadImageTask(mPoster).execute(mSingleResponse.getContentsResult().getContentsInfo().getThumbnailImgUrl());
        mPercent.setText(mSingleResponse.getContentsResult().getEvaluationStatus());
        String year = String.valueOf(mSingleResponse.getContentsResult().getContentsInfo().getYear());
        mYear.setText(year);
        mAge.setText(mSingleResponse.getContentsResult().getContentsInfo().getAge());
        mRuntime.setText(mSingleResponse.getContentsResult().getContentsInfo().getRuntime());
        mContents.setText(mSingleResponse.getContentsResult().getContentsInfo().getDetails());
        mAppearance.setText(mSingleResponse.getContentsResult().getContentsInfo().getActors());
        mDirector.setText(mSingleResponse.getContentsResult().getContentsInfo().getDirectors());

        Log.d(TAG, "layoutInit: similarPoster" + mSingleResponse.getSimilarContents().get(0).getThumbnailImgUrl());
        //similar contents 띄우기
        new DownloadImageTask(mSimilarPoster1).execute(mSingleResponse.getSimilarContents().get(0).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster2).execute(mSingleResponse.getSimilarContents().get(1).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster3).execute(mSingleResponse.getSimilarContents().get(2).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster4).execute(mSingleResponse.getSimilarContents().get(3).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster5).execute(mSingleResponse.getSimilarContents().get(4).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster6).execute(mSingleResponse.getSimilarContents().get(5).getThumbnailImgUrl());

        mSimilarPoster1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId,mSingleResponse.getSimilarContents().get(0).getContentsId());
            }
        });

        mSimilarPoster2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId,mSingleResponse.getSimilarContents().get(1).getContentsId());
            }
        });

        mSimilarPoster3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId,mSingleResponse.getSimilarContents().get(2).getContentsId());
            }
        });

        mSimilarPoster4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId,mSingleResponse.getSimilarContents().get(3).getContentsId());
            }
        });

        mSimilarPoster5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId,mSingleResponse.getSimilarContents().get(4).getContentsId());
            }
        });

        mSimilarPoster6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId,mSingleResponse.getSimilarContents().get(5).getContentsId());
            }
        });


        //비디오 Url통해 새로운 창 띄워서 재생
        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SingleActivity.this, VideoActivity.class);
                intent.putExtra("videoUri",mSingleResponse.getContentsResult().getContentsInfo().getVideoUrl());
                startActivity(intent);

            }
        });


    }

    private void tryGetSingleInfo(int profileId,int contentsId) {
        showProgressDialog();

        final SingleService singleService = new SingleService(this);
        singleService.getSingleInfo(profileId,contentsId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        if(action==MotionEvent.ACTION_DOWN) {
//            Intent intent = new Intent(SingleActivity.this, LikeActivity.class);
//            startActivity(intent);
//        }
        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        TextView button = (TextView) findViewById(R.id.single_like_words);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        button.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions

        p.x = location[0];
        p.y = location[1];
    }

    public static void dimBehind(PopupWindow popupWindow) {
        View container = popupWindow.getContentView().getRootView();
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.8f;
        wm.updateViewLayout(container, p);
    }

    //............
    private void showPopup(final Activity context, Point p) {


        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.transparent);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.activity_like, viewGroup);
        layout.findViewById(R.id.like_like).setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_slide_to_left));
        layout.findViewById(R.id.like_hate).setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_slide_to_right));

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 0;
        int OFFSET_Y = 0;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        dimBehind(popup);

        // Getting a reference to Close button, and close the popup when clicked.
        ImageView close = (ImageView) layout.findViewById(R.id.like_x_mark);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

    @Override
    public void validateSuccess(SingleResponse singleResponse) {
        Log.d(TAG, "validateSuccess: " + singleResponse.getContentsResult().getContentsInfo().getThumbnailImgUrl());
        Log.d(TAG, "validateSuccess: " + singleResponse.getSimilarContents().get(0).getThumbnailImgUrl());
        hideProgressDialog();
        this.mSingleResponse = singleResponse;
        layoutInit();

    }

    @Override
    public void validateFailure(String message) {
        Log.d(TAG, "validateFailure: ");
        hideProgressDialog();

    }

}
