package com.daniel.app.netfilx_clone.src.main.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.profile.ProfileActivity;
import com.daniel.app.netfilx_clone.src.profile.ProfileService;
import com.daniel.app.netfilx_clone.src.profile.interfaces.ProfileActivityView;
import com.daniel.app.netfilx_clone.src.profile.models.result;

import java.util.List;

public class DeleteDialogActivity extends Activity implements ProfileActivityView {

    private static final String TAG = "DeleteDialogActivity";

    LinearLayout mAlertStopRegister;
    TextView mAlertCancel;
    TextView mAlertConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_profile_delete);
        mAlertCancel = findViewById(R.id.profile_dialog_cancel);
        mAlertConfirm = findViewById(R.id.profile_dialog_delete_confirm);

        Intent intent = getIntent();
        final int imgId = intent.getIntExtra("profileId",-1);

        mAlertCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAlertConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tryDeleteProfiles(imgId);

            }
        });

    }

    public void tryDeleteProfiles(int id) {
        Log.d(TAG, "tryDeleteProfiles: started.");

        final ProfileService profileService = new ProfileService(this);
        profileService.deleteProfile(id);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action==MotionEvent.ACTION_DOWN) {
            finish();
        }
        return false;
    }

    @Override
    public void validateSuccess(List<result> result, int addAvailable) {
        Log.d(TAG, "validateSuccess: ");
    }

    @Override
    public void profileAddSuccess(boolean isSuccess) {
        Log.d(TAG, "profileAddSuccess: ");
        Intent intent = new Intent(DeleteDialogActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateFailure(String message) {
        Log.d(TAG, "validateFailure: ");
    }
}
