package com.daniel.app.netfilx_clone.src.search.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.main.single.SingleActivity;
import com.daniel.app.netfilx_clone.src.search.SearchActivity;
import com.daniel.app.netfilx_clone.src.search.models.SearchResult;

import java.util.List;

public class SearchWordRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "SearchWordRecyclerViewA";
    Context mContext;
    private List<SearchResult> mList;
    int Position;

    public SearchWordRecyclerViewAdapter(List<SearchResult> items, Context context) {
        mList = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_word_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // ListView의 getView 부분을 담당하는 메소드
        ((SearchWordRecyclerViewAdapter.ViewHolder) holder).onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size(); // 데이터 개수 리턴
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mPoster;
        public ImageView mPlay;



        public ViewHolder(View itemView) {
            super(itemView);
            // 화면에 표시될 View 로부터 위젯에 대한 참조 획득
            mTitle = itemView.findViewById(R.id.search_tv_title);
            mPoster = itemView.findViewById(R.id.search_iv_item);
            mPlay = itemView.findViewById(R.id.search_iv_play);


            //클릭리스너 부분 -> public static 해명
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((SearchActivity)mContext).tryGetSearchContents();
                    Position = getAdapterPosition();
                    Intent intent = new Intent(mContext, SingleActivity.class);
                    intent.putExtra("contentsId",mList.get(Position).getId());
                    Log.d(TAG, "onClick: contentsId " + mList.get(Position).getId());
                    mContext.startActivity(intent);

                }
            });
        }


        public void onBind(SearchResult item) {

            Log.d(TAG, "onBind: " + item.getVideoUrl());
            mTitle.setText(item.getTitle());
            Glide.with(mContext).load(item.getThumbnailImgUrl()).into(mPoster);
        }
    }


}
