package com.daniel.app.netfilx_clone.src.main.single;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.single.interfaces.SingleActivityView;
import com.daniel.app.netfilx_clone.src.main.single.models.EvaluateResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.HeartResponse;
import com.daniel.app.netfilx_clone.src.main.single.models.HeartResult;
import com.daniel.app.netfilx_clone.src.main.single.models.SingleResponse;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SingleActivity extends BaseActivity implements SingleActivityView {

    private static final String TAG = "SingleActivity";

    PopupWindow popup = new PopupWindow();

    ImageView mBackgound;
    LinearLayout mLinearLayout;
    LinearLayout mLlZzim;
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
        mContentsId = intent.getIntExtra("contentsId",1);
        mProfileId = intent.getIntExtra("profileId",2);

        tryGetSingleInfo(mProfileId, mContentsId);



        //like 평가 공유x

        mLinearLayout = findViewById(R.id.single_total_view);

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

        //백그라운드 블러
        //url 이미지를 bitmap으로 가져온다.

        //Bitmap bitmap = fromUrltoBitmap(mSingleResponse.getContentsResult().getContentsInfo().getThumbnailImgUrl());

        //fastBlur 함수 사용
        //bitmap = Fastblur(bitmap, 1.7, 25);

        //mBackgound.setImageBitmap(bitmap);

        new DownloadImageTask(mPoster).execute(mSingleResponse.getContentsResult().getContentsInfo().getThumbnailImgUrl());
        //mPercent.setText(mSingleResponse.getContentsResult().getContentsInfo().getGenres());
        String year = String.valueOf(mSingleResponse.getContentsResult().getContentsInfo().getYear());
        mYear.setText(year);
        mAge.setText(mSingleResponse.getContentsResult().getContentsInfo().getAge());
        mRuntime.setText(mSingleResponse.getContentsResult().getContentsInfo().getRuntime());
        mContents.setText(mSingleResponse.getContentsResult().getContentsInfo().getDetails());
        mAppearance.setText(mSingleResponse.getContentsResult().getContentsInfo().getActors());
        mDirector.setText(mSingleResponse.getContentsResult().getContentsInfo().getDirectors());

        //2개의 API에서 Like status 보존
        mLikeStatus = mSingleResponse.getContentsResult().getEvaluationStatus();
        //Like
        setLikeIcon(mSingleResponse.getContentsResult().getEvaluationStatus());

        Log.d(TAG, "layoutInit: similarPoster" + mSingleResponse.getSimilarContents().get(0).getThumbnailImgUrl());
        //similar contents 띄우기
        new DownloadImageTask(mSimilarPoster1).execute(mSingleResponse.getSimilarContents().get(0).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster2).execute(mSingleResponse.getSimilarContents().get(1).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster3).execute(mSingleResponse.getSimilarContents().get(2).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster4).execute(mSingleResponse.getSimilarContents().get(3).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster5).execute(mSingleResponse.getSimilarContents().get(4).getThumbnailImgUrl());
        new DownloadImageTask(mSimilarPoster6).execute(mSingleResponse.getSimilarContents().get(5).getThumbnailImgUrl());

        //상단 상태바 투명, 흰 아이콘
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

        if(mSingleResponse.getContentsResult().getEvaluationStatus().equals("N")){
        mIvZzim.setImageResource(R.drawable.ic_add_white);
        }else if(mSingleResponse.getContentsResult().getEvaluationStatus().equals("Y")){
            mIvZzim.setImageResource(R.drawable.ic_white_check);
        }

        //Zzim 클릭리스너
        mLlZzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //try post zzim
                tryPostHeart();
                //if + -> 체크로 바꾸고 체크 -> + 로 바꾸기

            }
        });

        //Like 클릭리스너
        mLikeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mLikeStatus==null){
                    if (p != null) showPopup(SingleActivity.this, p);
                }else if(mLikeStatus.equals("G")){
                    tryPostEvaluate("N");
                }else if(mLikeStatus.equals("B")){
                    tryPostEvaluate("N");
                }else if(mLikeStatus.equals("N")){
                    if (p != null) showPopup(SingleActivity.this, p);
                }

            }
        });

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

    private Bitmap fromUrltoBitmap(String imgUrl){

        Bitmap myBitmap = null;

        URL myImageURL = null;
        try {
            myImageURL = new URL(imgUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection connection = (HttpURLConnection)myImageURL .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myBitmap;
    }

    private void setHeartIcon(String heartStatus){
        Log.d(TAG, "setHeartIcon: " + heartStatus);
        if(heartStatus.equals("activated")){
            mIvZzim.setImageResource(R.drawable.ic_white_check);
        }else if(heartStatus.equals("deleted")){
            mIvZzim.setImageResource(R.drawable.ic_add_white);
        }


    }

    private void setLikeIcon(String choice) {

        if(choice==null){
        mLikeIcon.setImageResource(R.drawable.like_only);
        mLikeWord.setText("평가");}
        else if(choice.equals("G")){
            mLikeIcon.setImageResource(R.drawable.liked);
            mLikeWord.setText("평가됨");
        }else if(choice.equals("B")){
            mLikeIcon.setImageResource(R.drawable.hated_);
            mLikeWord.setText("평가됨");
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
        popup = new PopupWindow(context);
        popup.setContentView(layout);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = -250;
        int OFFSET_Y = -350;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

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

    public Bitmap Fastblur(Bitmap sentBitmap, double scale, int radius) {

        int width = (int) Math.round(sentBitmap.getWidth() * scale);
        int height = (int) Math.round(sentBitmap.getHeight() * scale);
        sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = ( 0xff000000 & pix[yi] ) | ( dv[rsum] << 16 ) | ( dv[gsum] << 8 ) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
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

        //팝업 지우기
        popup.dismiss();
    }

    @Override
    public void heartSuccess(HeartResponse heartResponse) {
        hideProgressDialog();

        Log.d(TAG, "heartSuccess: " + heartResponse.getHeartResult().getHeartStatus() +heartResponse.getMessage());

        this.mHeartResponse = heartResponse;

        setHeartIcon(heartResponse.getHeartResult().getHeartStatus());
    }


    @Override
    public void validateFailure(String message) {
        Log.d(TAG, "validateFailure: ");
        hideProgressDialog();

    }

}
