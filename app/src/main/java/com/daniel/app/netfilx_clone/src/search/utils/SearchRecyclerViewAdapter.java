package com.daniel.app.netfilx_clone.src.search.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.main.toptools.models.Contents;
import com.daniel.app.netfilx_clone.src.main.single.SingleActivity;

import java.util.ArrayList;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    private ArrayList<Contents> mList;
    int Position;

    public SearchRecyclerViewAdapter(ArrayList<Contents> items, Context context) {
        mList = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // ListView의 getView 부분을 담당하는 메소드
        ((SearchRecyclerViewAdapter.ViewHolder) holder).onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size(); // 데이터 개수 리턴
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public ImageView mPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            // 화면에 표시될 View 로부터 위젯에 대한 참조 획득
            mTitle = itemView.findViewById(R.id.search_tv_title);
            mPoster = itemView.findViewById(R.id.search_iv_item);

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

        public void onBind(Contents item) {
            mTitle.setText(item.getTitle());
            mPoster.setImageResource(item.getImgURL());
        }
    }

}
