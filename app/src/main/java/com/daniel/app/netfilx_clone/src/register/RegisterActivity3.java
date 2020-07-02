package com.daniel.app.netfilx_clone.src.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.register.interfaces.RegisterActivityView;
import com.daniel.app.netfilx_clone.src.signin.SignInActivity;
import com.google.android.material.textfield.TextInputEditText;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class RegisterActivity3 extends BaseActivity implements RegisterActivityView {

    private static final String TAG = "RegisterActivity3";

    Button mRegBtnNext;

    TextInputEditText mEmailIn;
    TextInputEditText mPasswordIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        mRegBtnNext = findViewById(R.id.reg_btn_next);
        mEmailIn = findViewById(R.id.reg_et_email_inside);
        mPasswordIn = findViewById(R.id.reg_et_pw_inside);

        TextView TvLogin = findViewById(R.id.adv_tv_login);
        TvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity3.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        mRegBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailIn.getText().toString();
                String password = mPasswordIn.getText().toString();

                tryRegister(email,password);

            }
        });

    }

    private void tryRegister(String email, String pw) {
        showProgressDialog();

        Log.d(TAG, "tryRegister: started." +email + pw);

        final RegisterService registerService = new RegisterService(this);
        registerService.postRegisterInfo(email, pw);
    }

    @Override
    public void validateSuccess(String token) {
        hideProgressDialog();
        Log.d(TAG, "validateSuccess: ");

        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(X_ACCESS_TOKEN, token);
        editor.apply();

        Intent intent = new Intent(RegisterActivity3.this, RegisterActivity4.class);
        intent.putExtra("token",token);
        startActivity(intent);
        finish();
        //성공시 어떻게 할래
        //mTvHelloWorld.setText(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d(TAG, "validateFailure: ");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(RegisterActivity3.this, DialogActivity.class);
        startActivity(intent);
    }

}
