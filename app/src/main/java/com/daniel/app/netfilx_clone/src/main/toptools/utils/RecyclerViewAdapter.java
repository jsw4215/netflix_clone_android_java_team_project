package com.daniel.app.netfilx_clone.src.main.toptools.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.main.MovieActivity;
import com.daniel.app.netfilx_clone.src.main.single.SingleActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.GenreActivity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    Context mContext;
    private ArrayList<String> mTvList;
    int Position;
    public static String fromwhere;
    int mProfileId;

    public RecyclerViewAdapter(ArrayList<String> items, Context context) {
        mTvList = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // ListView의 getView 부분을 담당하는 메소드
        ((ViewHolder) holder).onBind(mTvList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTvList.size(); // 데이터 개수 리턴
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            // 화면에 표시될 View 로부터 위젯에 대한 참조 획득
            mTitle = itemView.findViewById(R.id.genre_tv_item);

            //클릭리스너 부분 -> public static 해명
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent intent = ((GenreActivity) mContext).getIntent();
                        mProfileId = intent.getIntExtra("profileId",-1);
                        Position = getAdapterPosition();
                        Log.d(TAG, "onClick: " + mTvList.get(Position));
                        String Genre = mTvList.get(Position);
                        //여기에 해당 장르 조건을 걸어 인텐트로 화면을 넘기면 될 것으로 보임
                        Intent intent2 = new Intent(mContext, MovieActivity.class);
                        intent2.putExtra("profileId",mProfileId);
                        mContext.startActivity(intent2);
                        ((GenreActivity) mContext).finish();

                }
            });
        }

        public void onBind(String item) {
            mTitle.setText(item);
        }
    }

}
