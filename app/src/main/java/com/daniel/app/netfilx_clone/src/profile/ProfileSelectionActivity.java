package com.daniel.app.netfilx_clone.src.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.daniel.app.netfilx_clone.R;

public class ProfileSelectionActivity extends AppCompatActivity {

    ImageView mIvBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        mIvBackArrow = findViewById(R.id.profile_selection_back_arrow);

        mIvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //클릭한 해당 이미지 가져오기

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action==MotionEvent.ACTION_DOWN) {
            finish();
        }
        return false;
    }

}
