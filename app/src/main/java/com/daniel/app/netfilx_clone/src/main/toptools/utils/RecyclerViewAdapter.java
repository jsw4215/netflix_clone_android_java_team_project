package com.daniel.app.netfilx_clone.src.main.toptools.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.single.SingleActivity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    private ArrayList<String> mTvList;
    int Position;
    public static String fromwhere;

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

                        Position = getAdapterPosition();
                        //String when = ((FoodActicity) mContext).setwhen();
                        //String id_fromSearch = ((FoodActicity) mContext).setid();
                        //((FoodActicity) mContext).setlist(when, mTvList.get(Position).getTitle(), mTvList.get(Position).getDetail());
                        //ArrayList<FoodItem> list = new ArrayList<>();
                        //list = ((FoodActicity)mContext).getlist();
                        Intent intent = new Intent(mContext, SingleActivity.class);
//                        intent.putExtra(mContext.getString(R.string.row_id), mTvList.get(Position).getUid());
//                        intent.putExtra("food_name", mTvList.get(Position).getTitle());
//                        intent.putExtra("food_kcal", mTvList.get(Position).getDetail());
//                        intent.putExtra("list",list);
//                        intent.putExtra("when", when);
//                        intent.putExtra("id", id_fromSearch);
                        mContext.startActivity(intent);

                }
            });
        }

        public void onBind(String item) {
            mTitle.setText(item);
        }
    }

}
