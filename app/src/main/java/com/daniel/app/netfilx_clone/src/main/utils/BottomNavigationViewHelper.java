package com.daniel.app.netfilx_clone.src.main.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.comingsoon.ComingSoonActivity;
import com.daniel.app.netfilx_clone.src.main.MainActivity;
import com.daniel.app.netfilx_clone.src.main.SavedContentsActivity;
import com.daniel.app.netfilx_clone.src.search.SearchActivity;
import com.daniel.app.netfilx_clone.src.main.MoreActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationView bottomNavigationView){
        Log.d(TAG,"setupBottomNavigationView: setting up");
        //bottomNavigationView.shift(false);
        //bottomNavigationView.enableItemShiftingMode(false);
        //bottomNavigationView.shift(false);
        //bottomNavigationView.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.navigation_home:
                        Intent intent1 = new Intent(context, MainActivity.class);
                        context.startActivity(intent1);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.navigation_search:
                        Intent intent2 = new Intent(context, SearchActivity.class);
                        context.startActivity(intent2);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.navigation_coming_soon:
                        Intent intent3 = new Intent(context, ComingSoonActivity.class);
                        context.startActivity(intent3);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.navigation_saved_contents:
                        Intent intent4 = new Intent(context, SavedContentsActivity.class );
                        context.startActivity(intent4);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case R.id.navigation_else:
                        Intent intent5 = new Intent(context, MoreActivity.class);
                        context.startActivity(intent5);
                        callingActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                }
                return false;
            }
        });

    }
}
