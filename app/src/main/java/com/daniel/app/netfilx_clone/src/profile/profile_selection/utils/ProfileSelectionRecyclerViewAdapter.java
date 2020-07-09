package com.daniel.app.netfilx_clone.src.profile.profile_selection.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.profile.ProfileAddActivity;
import com.daniel.app.netfilx_clone.src.profile.ProfileChangeActivity;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.ProfileSelectionActivity;
import com.daniel.app.netfilx_clone.src.profile.profile_selection.models.selection_detail;
import com.daniel.app.netfilx_clone.src.profile.utils.DownloadImageTask;

import java.util.List;

public class ProfileSelectionRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ProfileSelectionRecycle";

    Context mContext;
    private List<selection_detail> mList;
    int Position;

    public ProfileSelectionRecyclerViewAdapter(List<selection_detail> items, Context context) {
        mList = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_selection_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // ListView의 getView 부분을 담당하는 메소드
        ((ProfileSelectionRecyclerViewAdapter.ViewHolder) holder).onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size(); // 데이터 개수 리턴
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mProfileImg;

        public ViewHolder(View itemView) {
            super(itemView);
            // 화면에 표시될 View 로부터 위젯에 대한 참조 획득
            mProfileImg = itemView.findViewById(R.id.profile_selection_rv_single);

            //클릭리스너 부분 -> public static 해명
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Position = getAdapterPosition();
                    Log.d(TAG, "onClick: 이미지아이디" + ((ProfileSelectionActivity) mContext).getIntent().getStringExtra("calling_activity"));
                    if(((ProfileSelectionActivity) mContext).getIntent().hasExtra("calling_activity")){
                        Log.d(TAG, "onClick: " + ((ProfileSelectionActivity) mContext).getIntent().getStringExtra("calling_activity"));
                    Intent intent = new Intent(mContext, ProfileAddActivity.class);
                    intent.putExtra("imgUrl",mList.get(Position).getProfileImgUrl());
                    intent.putExtra("imgId",mList.get(Position).getProfileImgId());
                    Log.d(TAG, "onClick: " + mList.get(Position).getProfileImgId());
                    mContext.startActivity(intent);
                    }else{
                        int profileId = ((ProfileSelectionActivity) mContext).getIntent().getIntExtra("profileId",-1);
                        Intent intent = new Intent(mContext, ProfileChangeActivity.class);
                        intent.putExtra("imgUrl",mList.get(Position).getProfileImgUrl());
                        intent.putExtra("imgId",mList.get(Position).getProfileImgId());
                        intent.putExtra("profileId",profileId);
                        Log.d(TAG, "onClick: " + mList.get(Position).getProfileImgId());
                        mContext.startActivity(intent);
                    }

                }
            });
        }

        public void onBind(selection_detail item) {
            new DownloadImageTask(mProfileImg).execute(item.getProfileImgUrl());

        }
    }

}
