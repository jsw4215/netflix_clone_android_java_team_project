package com.daniel.app.netfilx_clone.src.register;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.register.interfaces.RegisterActivityView;
import com.daniel.app.netfilx_clone.src.register.models.RegisterBody;
import com.daniel.app.netfilx_clone.src.register.models.RegisterPayBody;
import com.daniel.app.netfilx_clone.src.signin.SignInActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class RegisterActivity5 extends BaseActivity implements RegisterActivityView {

    private static final String TAG = "RegisterActivity5";

    Button mRegBtnNext;
    TextInputEditText mCardNum;
    TextInputEditText mCardValidMonthAndYear;
    TextInputEditText mName;
    Spinner mBirthDate;
    Spinner mBirthMonth;
    TextInputEditText mBirthYear;
    String mSelectedBD;
    String mSelectedBM;
    CheckBox chkAll;
    CheckBox chk1;
    CheckBox chk2;
    CheckBox chk3;
    CheckBox chk4;
    TextView mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register5);

        mRegBtnNext = findViewById(R.id.reg_btn_next);
        mCardNum = findViewById(R.id.reg_et_pay_card_num);
        mCardValidMonthAndYear = findViewById(R.id.reg_et_pay_mon_year);
        mName = findViewById(R.id.reg_et_pay_name);
        mBirthYear = findViewById(R.id.reg_et_pay_birth_year);
        mBirthDate = findViewById(R.id.reg_card_birth_date);
        mBirthMonth = findViewById(R.id.reg_card_birth_month);
        chkAll = findViewById(R.id.chk_sign_up_agreement_all);
        chk1 = findViewById(R.id.chk_sign_up_agreement_1);
        chk2 = findViewById(R.id.chk_sign_up_agreement_2);
        chk3 = findViewById(R.id.chk_sign_up_agreement_3);
        chk4 = findViewById(R.id.chk_sign_up_agreement_4);
        mLogout = findViewById(R.id.adv_tv_login);

        Intent intent = getIntent();

        final String token = intent.getStringExtra("token");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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

        mBirthDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedBD = (String) mBirthDate.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mBirthMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedBM = (String) mBirthMonth.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Log.d(TAG, "check BD and BM: " + mSelectedBD + mSelectedBM);


        mRegBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterPayBody registerPayBody = new RegisterPayBody();

                registerPayBody.setCardNum(mCardNum.getText().toString());
                registerPayBody.setName(mName.getText().toString());
                registerPayBody.setbYear(Integer.parseInt(mBirthYear.getText().toString()));

                //카드 유효기간 년,월 구분해서 넣기
                String validMonthYear = mCardValidMonthAndYear.getText().toString();
                String[] monthAndYear = new String[2];
                monthAndYear = validMonthYear.split("/");

                registerPayBody.setExpMonth(Integer.parseInt(monthAndYear[0]));
                registerPayBody.setExpYear(Integer.parseInt(monthAndYear[1])+2000);

                //스피너로 받은 생월일 넣기
                String month =mSelectedBM.substring(0,1);
                registerPayBody.setbMonth(Integer.parseInt(month));
                registerPayBody.setbDay(Integer.parseInt(mSelectedBD));


                //지불 카드 정보 등록
                tryRegisterPay(token, registerPayBody);

            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity5.this, SignInActivity.class);

                startActivity(intent);

            }
        });

    }

    private void tryRegisterPay(String token, RegisterPayBody registerPayBody) {
        showProgressDialog();

        HashMap<String, Object> params = new HashMap<>();
        params.put("cardNum", registerPayBody.getCardNum());
        params.put("expYear", registerPayBody.getExpYear());
        params.put("expMonth", registerPayBody.getExpMonth());
        params.put("name", registerPayBody.getName());
        params.put("bDay",registerPayBody.getbDay());
        params.put("bMonth", registerPayBody.getbMonth());
        params.put("bYear", registerPayBody.getbYear());

        final RegisterPayService registerPayService = new RegisterPayService(this, params);
        registerPayService.postRegisterPayInfo();
    }

    private void tryGetTest() {
        showProgressDialog();

        String email = "aaa@aaa.com";
        String pw = "11111";

        final RegisterService registerService = new RegisterService(this);
        registerService.postRegisterInfo(email, pw);
    }

    @Override
    public void validateSuccess(String Message) {
        hideProgressDialog();
        Log.d(TAG, "validateSuccess: ");

        Intent intent = new Intent(RegisterActivity5.this, MainActivity.class);
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
        Intent intent = new Intent(RegisterActivity5.this, DialogActivity.class);
        startActivity(intent);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
            case R.id.chk_sign_up_agreement_all:
                if (chkAll.isChecked()){
                    chk1.setChecked(true);
                    chk2.setChecked(true);
                    chk3.setChecked(true);
                    chk4.setChecked(true);
                }else if(!chkAll.isChecked()){
                    chk1.setChecked(false);
                    chk2.setChecked(false);
                    chk3.setChecked(false);
                    chk4.setChecked(false);
                }
                break;
            case R.id.chk_sign_up_agreement_1:
                if (chk1.isChecked() && chk2.isChecked() && chk3.isChecked() && chk4.isChecked()){
                    chkAll.setChecked(true);
                }else if (chkAll.isChecked() && !chk1.isChecked()){
                    chkAll.setChecked(false);
                }
                break;
            case R.id.chk_sign_up_agreement_2:
                if (chk1.isChecked() && chk2.isChecked() && chk3.isChecked() && chk4.isChecked()){
                    chkAll.setChecked(true);
                }else if (chkAll.isChecked() && !chk2.isChecked()){
                    chkAll.setChecked(false);
                }
                break;
            case R.id.chk_sign_up_agreement_3:
                if (chk1.isChecked() && chk2.isChecked() && chk3.isChecked() && chk4.isChecked()){
                    chkAll.setChecked(true);
                }else if (chkAll.isChecked() && !chk3.isChecked()){
                    chkAll.setChecked(false);
                }
                break;
            case R.id.chk_sign_up_agreement_4:
                if (chk1.isChecked() && chk2.isChecked() && chk3.isChecked() && chk4.isChecked()){
                    chkAll.setChecked(true);
                }else if (chkAll.isChecked() && !chk4.isChecked()){
                    chkAll.setChecked(false);
                }
                break;
            default:
                break;
        }
    }
}
