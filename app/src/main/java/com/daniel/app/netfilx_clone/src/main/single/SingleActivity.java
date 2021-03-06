package com.daniel.app.netfilx_clone.src.main.single;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.main.MovieActivity;
import com.daniel.app.netfilx_clone.src.main.single.interfaces.SingleActivityView;
import com.daniel.app.netfilx_clone.src.main.single.models.EvaluateResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.HeartResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.HeartResult;
import com.daniel.app.netfilx_clone.src.main.single.models.SingleResponse;
import com.daniel.app.netfilx_clone.src.main.single.utils.BlurTransformation;
import com.daniel.app.netfilx_clone.src.main.utils.MainLoadingActivity;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class SingleActivity extends BaseActivity implements SingleActivityView {

    private static final String TAG = "SingleActivity";

    PopupWindow popup = new PopupWindow();

    ImageView mBackgound;
    LinearLayout mLinearLayout;
    LinearLayout mLlZzim;
    LinearLayout mSaved;
    ImageView mIvZzim;
    ImageView mBackArrow;
    ImageView mPoster;
    ImageView mSimilarPoster1;
    ImageView mSimilarPoster2;
    ImageView mSimilarPoster3;
    ImageView mSimilarPoster4;
    ImageView mSimilarPoster5;
    ImageView mSimilarPoster6;
    ImageView mLikeIcon;

    SingleResponse mSingleResponse;
    EvaluateResponse mEvaluateResponse;
    HeartResponse mHeartResponse;

    TextView mLikeWord;
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

    String mLikeStatus;

    int mContentsId;
    int mProfileId;

    Point p = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        Log.d(TAG, "onCreate: started.");

        Intent intent = getIntent();
        mContentsId = intent.getIntExtra("contentsId", 1);
        mProfileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));

        tryGetSingleInfo(mProfileId, mContentsId);

        //like ?????? ??????x

        mLinearLayout = findViewById(R.id.single_total_view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(0);
        }

        //mLike = findViewById(R.id.single_like_icon);

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
        mLikeIcon = findViewById(R.id.single_like_icon);
        mLikeWord = findViewById(R.id.single_like_words);
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
        mLlZzim = findViewById(R.id.single_ll_zzim);
        mIvZzim = findViewById(R.id.single_iv_zzim);
        mBackgound = findViewById(R.id.single_dim_background);
        mSaved = findViewById(R.id.single_saved);

        //mBackgound.setImageBitmap(bitmap);
        Log.d(TAG, "layoutInit: " + mSingleResponse.getContentsResult().getContentsInfo().getThumbnailImgUrl());
        new DownloadImageTask(mPoster).execute(mSingleResponse.getContentsResult().getContentsInfo().getThumbnailImgUrl());
        String year = String.valueOf(mSingleResponse.getContentsResult().getContentsInfo().getYear());
        mYear.setText(year);
        mAge.setText(mSingleResponse.getContentsResult().getContentsInfo().getAge());
        mRuntime.setText(mSingleResponse.getContentsResult().getContentsInfo().getRuntime());
        mContents.setText(mSingleResponse.getContentsResult().getContentsInfo().getDetails());
        mAppearance.setText(mSingleResponse.getContentsResult().getContentsInfo().getActors());
        mDirector.setText(mSingleResponse.getContentsResult().getContentsInfo().getDirectors());

        //2?????? API?????? Like status ??????
        mLikeStatus = mSingleResponse.getContentsResult().getEvaluationStatus();
        //Like
        setLikeIcon(mSingleResponse.getContentsResult().getEvaluationStatus());

        Log.d(TAG, "layoutInit: similarPoster" + mSingleResponse.getSimilarContents().get(0).getThumbnailImgUrl());
        //similar contents ?????????


        new DownloadImageTask(mSimilarPoster1).execute(mSingleResponse.getSimilarContents().get(0).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster2).execute(mSingleResponse.getSimilarContents().get(1).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster3).execute(mSingleResponse.getSimilarContents().get(2).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster4).execute(mSingleResponse.getSimilarContents().get(3).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster5).execute(mSingleResponse.getSimilarContents().get(4).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster6).execute(mSingleResponse.getSimilarContents().get(5).getThumbnailImgUrl());


        RequestOptions options = new RequestOptions();

        //??????????????? ??????
        //url ???????????? bitmap?????? ????????????.
        options.override(MATCH_PARENT, MATCH_PARENT);
        options.fitCenter();
        options.centerCrop();
        options.transform(new BlurTransformation(this, 100));

        Glide.with(this).setDefaultRequestOptions(options).asBitmap()
                .load(mSingleResponse.getContentsResult().getContentsInfo().getThumbnailImgUrl()).into(mBackgound);

        //?????? ????????? ??????, ??? ?????????
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(0);
        }

        if (mSingleResponse.getContentsResult().getEvaluationStatus().equals("N")) {
            mIvZzim.setImageResource(R.drawable.ic_add_white);
        } else if (mSingleResponse.getContentsResult().getEvaluationStatus().equals("Y")) {
            mIvZzim.setImageResource(R.drawable.ic_white_check);
        }

        if(mSingleResponse.getContentsResult().getHeartStatus().equals("Y")){
        mIvZzim.setImageResource(R.drawable.ic_white_check);
        }else if(mSingleResponse.getContentsResult().getHeartStatus().equals("N")){
            mIvZzim.setImageResource(R.drawable.ic_add_white);
        }

        //Zzim ???????????????
        mLlZzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //try post zzim
                tryPostHeart();
                //if + -> ????????? ????????? ?????? -> + ??? ?????????

            }
        });

        //Like ???????????????
        mLikeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLikeStatus == null) {
                    if (p != null) showPopup(SingleActivity.this, p);
                } else if (mLikeStatus.equals("G")) {
                    tryPostEvaluate("N");
                } else if (mLikeStatus.equals("B")) {
                    tryPostEvaluate("N");
                } else if (mLikeStatus.equals("N")) {
                    if (p != null) showPopup(SingleActivity.this, p);
                }

            }
        });

        mSimilarPoster1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId, mSingleResponse.getSimilarContents().get(0).getContentsId());
            }
        });

        mSimilarPoster2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId, mSingleResponse.getSimilarContents().get(1).getContentsId());
            }
        });

        mSimilarPoster3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId, mSingleResponse.getSimilarContents().get(2).getContentsId());
            }
        });

        mSimilarPoster4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId, mSingleResponse.getSimilarContents().get(3).getContentsId());
            }
        });

        mSimilarPoster5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId, mSingleResponse.getSimilarContents().get(4).getContentsId());
            }
        });

        mSimilarPoster6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetSingleInfo(mProfileId, mSingleResponse.getSimilarContents().get(5).getContentsId());
            }
        });


        //????????? Url?????? ????????? ??? ????????? ??????
        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SingleActivity.this, VideoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("videoUri", mSingleResponse.getContentsResult().getContentsInfo().getVideoUrl());
                startActivity(intent);

            }
        });

        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleActivity.this, MovieActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


    }

    private void setHeartIcon(String heartStatus) {
        Log.d(TAG, "setHeartIcon: " + heartStatus);
        if (heartStatus.equals("activated")) {
            mIvZzim.setImageResource(R.drawable.ic_white_check);
        } else if (heartStatus.equals("deleted")) {
            mIvZzim.setImageResource(R.drawable.ic_add_white);
        }


    }

    private void setLikeIcon(String choice) {

        if (choice == null) {
            mLikeIcon.setImageResource(R.drawable.like_only);
            mLikeWord.setText("??????");
        } else if (choice.equals("G")) {
            mLikeIcon.setImageResource(R.drawable.liked);
            mLikeWord.setText("?????????");
        } else if (choice.equals("B")) {
            mLikeIcon.setImageResource(R.drawable.hated_);
            mLikeWord.setText("?????????");
        }

    }

    private void tryPostHeart() {
        showProgressDialog();

        final SingleService singleService = new SingleService(this);
        singleService.postHeart(mProfileId, mContentsId);


    }

    private void tryPostEvaluate(String choice) {
        showProgressDialog();

        final SingleService singleService = new SingleService(this);
        singleService.postEvaluate(mProfileId, mContentsId, choice);


    }

    private void tryGetSingleInfo(int profileId, int contentsId) {
        showProgressDialog();

        final SingleService singleService = new SingleService(this);
        singleService.getSingleInfo(profileId, contentsId);
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showPopup(final Activity context, Point p) {

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.transparent);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.activity_like, viewGroup);
        layout.findViewById(R.id.like_like).setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_slide_to_left));
        layout.findViewById(R.id.like_hate).setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_slide_to_right));

        // Creating the PopupWindow
        popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = -210;
        int OFFSET_Y = -320;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAsDropDown(mLikeIcon, OFFSET_X, OFFSET_Y);

        dimBehind(popup);

        popup.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);


        // Getting a reference to Close button, and close the popup when clicked.
        ImageView close = layout.findViewById(R.id.like_x_mark);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        ImageView like = layout.findViewById(R.id.like_like);
        ImageView hate = layout.findViewById(R.id.like_hate);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choice = "G";
                tryPostEvaluate(choice);
            }
        });

        hate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choice = "B";
                tryPostEvaluate(choice);
            }
        });

    }

    @Override
    public void validateSuccess(SingleResponse singleResponse) {
        hideProgressDialog();
        this.mSingleResponse = singleResponse;
        layoutInit();

    }

    @Override
    public void evaluateSuccess(EvaluateResponse evaluateResponse) {
        Log.d(TAG, "evaluateSuccess: ");
        hideProgressDialog();

        this.mEvaluateResponse = evaluateResponse;

        mLikeStatus = evaluateResponse.getResult().getChoice();

        setLikeIcon(mLikeStatus);

        //?????? ?????????
        popup.dismiss();
    }

    @Override
    public void heartSuccess(HeartResponse heartResponse) {
        hideProgressDialog();

        Log.d(TAG, "heartSuccess: " + heartResponse.getHeartResult().getHeartStatus() + heartResponse.getMessage());

        this.mHeartResponse = heartResponse;

        setHeartIcon(heartResponse.getHeartResult().getHeartStatus());
    }


    @Override
    public void validateFailure(String message) {
        Log.d(TAG, "validateFailure: ");
        hideProgressDialog();

    }

}
