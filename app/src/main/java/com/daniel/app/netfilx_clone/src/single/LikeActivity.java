package com.daniel.app.netfilx_clone.src.single;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daniel.app.netfilx_clone.R;

public class LikeActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    ImageView mLikes;
    ImageView mHates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        mLikes = findViewById(R.id.like_like);
        mHates = findViewById(R.id.like_hate);

        mLinearLayout = findViewById(R.id.transparent);

        //애니메이션 페이드인 작업


//        Animation fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
//        fadeIn.setDuration(3000);
//
//        AnimationSet animation = new AnimationSet(false); // change to false
//        animation.addAnimation(fadeIn);
//        mLikes.setAnimation(animation);
//        mHates.setAnimation(animation);
//        animation.start();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action==MotionEvent.ACTION_DOWN) {
            mLinearLayout.setVisibility(View.VISIBLE);
        }
        return false;
    }


}
