package com.daniel.app.netfilx_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MembershipActivity extends AppCompatActivity {

    Button mRegBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        mRegBtnNext = findViewById(R.id.reg_btn_next);

        mRegBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MembershipActivity.this, RegisterActivity2.class);
                startActivity(intent);
            }
        });

    }
}
