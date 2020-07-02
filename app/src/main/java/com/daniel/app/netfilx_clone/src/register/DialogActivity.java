package com.daniel.app.netfilx_clone.src.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.advertisement.AdvertisementActivity;

public class DialogActivity extends Activity {

    LinearLayout mAlertStopRegister;
    TextView mAlertCancel;
    TextView mAlertConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_stop_register);
        mAlertStopRegister = findViewById(R.id.alert_stop_reg);
        mAlertCancel = findViewById(R.id.alert_cancel);
        mAlertConfirm = findViewById(R.id.alert_confirm);

        mAlertCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAlertConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DialogActivity.this, AdvertisementActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
