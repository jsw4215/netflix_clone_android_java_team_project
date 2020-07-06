package com.daniel.app.netfilx_clone.src.signin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.advertisement.AdvertisementActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.main.single.SingleActivity;
import com.daniel.app.netfilx_clone.src.profile.ProfileActivity;
import com.daniel.app.netfilx_clone.src.signin.interfaces.SignInActivityView;
import com.google.android.material.textfield.TextInputEditText;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class SignInActivity extends BaseActivity implements SignInActivityView {

    private static final String TAG = "SignInActivity";

    TextInputEditText mEmail;
    TextInputEditText mPassword;
    Button mSignInBtn;
    ConstraintLayout mAlertEmail;
    ConstraintLayout mAlertPw;
    TextView mAlertEmailReg;
    TextView mAlertEmailRetry;
    TextView mAlertPwReset;
    TextView mAlertPwRetry;
    ImageView mSignInTopBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmail = findViewById(R.id.sign_in_et_email);
        mPassword = findViewById(R.id.sign_in_et_pw);
        mSignInBtn = findViewById(R.id.sign_in_button);
        mAlertEmail = findViewById(R.id.alert_email);
        mAlertPw = findViewById(R.id.alert_pw);
        mAlertEmailReg = findViewById(R.id.alert_email_register);
        mAlertEmailRetry = findViewById(R.id.alert_email_retry);
        mAlertPwReset = findViewById(R.id.alert_pw_reset);
        mAlertPwRetry = findViewById(R.id.alert_pw_retry);
        mSignInTopBackArrow = findViewById(R.id.sign_in_top_back_arrow);

        mAlertEmail.setVisibility(View.GONE);
        mAlertPw.setVisibility(View.GONE);

        mAlertEmailReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, AdvertisementActivity.class);

                startActivity(intent);
            }
        });

        mAlertEmailRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertEmail.setVisibility(View.GONE);
            }
        });

        mAlertPwReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/kr/LoginHelp/"));
                startActivity(intent);
            }
        });

                mAlertPwRetry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAlertPw.setVisibility(View.GONE);
                    }
                });

        mSignInTopBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClickSignIn: started. ");

                String email =mEmail.getText().toString();
                String password = mPassword.getText().toString();

                tryLogin(email, password);

            }
        });

    }

    private void tryLogin(String email, String pw) {
        showProgressDialog();

        Log.d(TAG, "tryLogin: started.");


        final SignInService signInService = new SignInService(this);
        signInService.postSignInInfo(email, pw);

    }

    private void tryGetTest() {
        showProgressDialog();

        Log.d(TAG, "tryGetTest: started.");

        String email = "dlwltjd7778@naver.com";
        String pw = "1111";



        final SignInService signInService = new SignInService(this);
        signInService.postSignInInfo(email, pw);

    }

    @Override
    public void validateSuccess(boolean isSuccess, int code, String jwt) {
        hideProgressDialog();
        Log.d(TAG, "validateSuccess:Message ");
        if(isSuccess){
            Log.d(TAG, "validateSuccess: Login Success");

            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN, jwt);
            editor.apply();

            //Intent intent = new Intent(SignInActivity.this, SingleActivity.class);
            Intent intent = new Intent(SignInActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }else{
            makeDialog(code);
            Log.d(TAG, "validateSuccess: Login failure");
        }
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        Log.d(TAG, "validateFailure: failure");
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_hello_world:
                tryGetTest();
                break;
            default:
                break;
        }
    }

    private void makeDialog(int code) {

        Log.d(TAG, "makeDialog: " + code);

        if(code == 200){
            mAlertEmail.setVisibility(View.VISIBLE);

        }else if(code == 210){
            mAlertPw.setVisibility(View.VISIBLE);

        }


    }



}
