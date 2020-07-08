package com.daniel.app.netfilx_clone.src.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.comingsoon.ComingSoonActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.ZzimActivity;
import com.daniel.app.netfilx_clone.src.main.utils.BottomNavigationViewHelper;
import com.daniel.app.netfilx_clone.src.main.utils.LogoutDialogActivity;
import com.daniel.app.netfilx_clone.src.signin.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.daniel.app.netfilx_clone.src.ApplicationClass.sSharedPreferences;

public class MoreActivity extends AppCompatActivity {

    private static final String TAG = "MoreActivity";

    private static final int ACTIVITY_NUM = 4;
    Context mContext = MoreActivity.this;
    BottomNavigationView mBottomNavigationView;
    TextView mLogout;
    LinearLayout mLlZzim;
    int mProfileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        mBottomNavigationView = findViewById(R.id.nav_view);
        mLogout = findViewById(R.id.more_tv_logout);
        mLlZzim = findViewById(R.id.more_ll_zzim);

        mProfileId = Integer.parseInt(sSharedPreferences.getString("profileId", String.valueOf(-1)));

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(0);
        }

        setupBottomNavigationView();

        mLlZzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreActivity.this, ZzimActivity.class);
                startActivity(intent);
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MoreActivity.this, LogoutDialogActivity.class);
                startActivity(intent);

            }
        });

    }

    private void setupBottomNavigationView(){
        Log.d(TAG,"setupBottomnavView: setting up BottomNavigationView");
        BottomNavigationViewHelper.setupBottomNavigationView(mBottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(mContext, this, mBottomNavigationView);
        Menu menu = mBottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}
