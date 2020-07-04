package com.daniel.app.netfilx_clone.src.comingsoon.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.comingsoon.models.MovieDetail;
import com.daniel.app.netfilx_clone.src.single.SingleActivity;

import java.util.ArrayList;

public class ComingRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    private ArrayList<MovieDetail> mList;
    int Position;

    public ComingRecyclerViewAdapter(ArrayList<MovieDetail> items, Context context) {
        mList = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coming_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // ListView의 getView 부분을 담당하는 메소드
        ((ComingRecyclerViewAdapter.ViewHolder) holder).onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size(); // 데이터 개수 리턴
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mTitleImage;
        public VideoView mVideo;
        public TextView mContents;
        public TextView mGenre;

        public ViewHolder(View itemView) {
            super(itemView);
            // 화면에 표시될 View 로부터 위젯에 대한 참조 획득
            mTitle = itemView.findViewById(R.id.coming_tv_title);
            mTitleImage = itemView.findViewById(R.id.coming_iv_title_image);
            mVideo = itemView.findViewById(R.id.coming_vv);
            mContents = itemView.findViewById(R.id.coming_tv_contents);
            mGenre = itemView.findViewById(R.id.coming_tv_genre);

            //클릭리스너 부분 -> public static 해명
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Position = getAdapterPosition();
                    //String when = ((FoodActicity) mContext).setwhen();
                    //String id_fromSearch = ((FoodActicity) mContext).setid();
                    //((FoodActicity) mContext).setlist(when, mTvList.get(Position).getTitle(), mTvList.get(Position).getDetail());
                    //ArrayList<FoodItem> list = new ArrayList<>();
                    //list = ((FoodActicity)mContext).getlist();
                    Intent intent = new Intent(mContext, SingleActivity.class);
                    mContext.startActivity(intent);

                }
            });
        }

        public void onBind(MovieDetail item) {
            mTitle.setText(item.getTitle());
            mTitleImage.setImageResource(item.getTitleImage());
            mVideo.setVideoURI(item.getVideoView());
            mContents.setText(item.getContents());
            mGenre.setText(item.getGenre());
        }
    }

}
