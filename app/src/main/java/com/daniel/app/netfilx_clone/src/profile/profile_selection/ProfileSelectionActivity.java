package com.daniel.app.netfilx_clone.src.profile.profile_selection;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.BaseActivity;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.interfaces.ProfileSelectionActivityView;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.models.results;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.models.selection_detail;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.utils.ProfileSelectionRecyclerViewAdapter;

import java.util.List;

public class ProfileSelectionActivity extends BaseActivity implements ProfileSelectionActivityView {

    private static final String TAG = "ProfileSelectionActivit";

    ImageView mIvBackArrow;
    results mResults;
    RecyclerView mRecyclerView;
    ProfileSelectionRecyclerViewAdapter mProfileSelectionRecyclerViewAdapter;
    int mProfileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);
        Log.d(TAG, "onCreate: ");

        mIvBackArrow = findViewById(R.id.profile_selection_back_arrow);
        mRecyclerView = findViewById(R.id.profile_rv_selection);

        Intent intent = getIntent();
        mProfileId = intent.getIntExtra("profileId",-1);

        mIvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //클릭한 해당 이미지 가져오기
        tryGetProfileImg();

    }

    private void tryGetProfileImg() {
        Log.d(TAG, "tryGetProfileImg: started.");
        showProgressDialog();

        final ProfileSelectionService profileSelectionService = new ProfileSelectionService(this);
        profileSelectionService.getProfileImg();

    }

    private void setProfileImgList(results results){
        Log.d(TAG, "setProfileImgList: started.");

        //String listName = results.getProfileName();

        List<selection_detail> photoList = results.getDetails();

        //추가, 수정, 삭제 시에 해당 recyclerview의 크기 높이 변경 방지
        mRecyclerView.setHasFixedSize(true);

        //리싸이클러뷰 레이아웃매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //어댑터 연결
        mProfileSelectionRecyclerViewAdapter = new ProfileSelectionRecyclerViewAdapter(photoList, this);
        mRecyclerView.setAdapter(mProfileSelectionRecyclerViewAdapter);

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
    public void validateSuccess(results result) {
        Log.d(TAG, "validateSuccess: "+ result.getProfileName());
        hideProgressDialog();
        this.mResults = result;
        setProfileImgList(mResults);

    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
