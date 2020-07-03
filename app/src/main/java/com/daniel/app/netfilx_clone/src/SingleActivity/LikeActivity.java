package com.daniel.app.netfilx_clone.src.SingleActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.daniel.app.netfilx_clone.R;

public class LikeActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);



        relativeLayout = findViewById(R.id.transparent);

        relativeLayout.setVisibility(View.GONE);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action==MotionEvent.ACTION_DOWN) {
            relativeLayout.setVisibility(View.VISIBLE);
        }
        return false;
    }


}
