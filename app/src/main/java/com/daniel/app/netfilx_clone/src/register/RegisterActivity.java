package com.daniel.app.netfilx_clone.src.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.signin.SignInActivity;

public class RegisterActivity extends AppCompatActivity {

    Button mRegBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mRegBtnNext = findViewById(R.id.reg_btn_next);

        DialogActivity dialogActivity;

        //투명화면
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

        TextView TvLogin = findViewById(R.id.adv_tv_login);
        TvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });


        mRegBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MembershipActivity.class);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_right_out);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this, DialogActivity.class);
        startActivity(intent);
    }

    public void customOnClick(View view) {
    }
}
