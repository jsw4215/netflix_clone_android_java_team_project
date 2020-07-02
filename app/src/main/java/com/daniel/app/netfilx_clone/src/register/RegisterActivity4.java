package com.daniel.app.netfilx_clone.src.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;

public class RegisterActivity4 extends AppCompatActivity {

    Button mRegBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register4);
        mRegBtnNext = findViewById(R.id.reg_btn_next);

        Intent intent = getIntent();

        final String token= intent.getStringExtra("token");

        mRegBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity4.this, RegisterActivity5.class);
                intent.putExtra("token", token);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(RegisterActivity4.this, DialogActivity.class);
        startActivity(intent);
    }
}
