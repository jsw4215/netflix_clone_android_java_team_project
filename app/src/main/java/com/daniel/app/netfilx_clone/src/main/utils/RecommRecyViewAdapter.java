package com.daniel.app.netfilx_clone.src.main.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.main.MovieActivity;
import com.daniel.app.netfilx_clone.src.main.models.RecommendResult;
import com.daniel.app.netfilx_clone.src.main.single.SingleActivity;
import com.daniel.app.netfilx_clone.src.main.toptools.models.ZzimResult;

import java.util.List;

public class RecommRecyViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    private List<RecommendResult> mList;
    int Position;

    public RecommRecyViewAdapter(List<RecommendResult> items, Context context) {
        mList = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // ListView의 getView 부분을 담당하는 메소드
        ((RecommRecyViewAdapter.ViewHolder) holder).onBind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size(); // 데이터 개수 리턴
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            // 화면에 표시될 View 로부터 위젯에 대한 참조 획득
            mPoster = itemView.findViewById(R.id.iv_poster);

            //클릭리스너 부분 -> public static 해명
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Position = getAdapterPosition();
                    int ContentsId = mList.get(Position).getContentsId();
                    int ProfileId = ((MovieActivity) mContext).getIntent().getIntExtra("profileId",2);
                    //String when = ((FoodActicity) mContext).setwhen();
                    //String id_fromSearch = ((FoodActicity) mContext).setid();
                    //((FoodActicity) mContext).setlist(when, mTvList.get(Position).getTitle(), mTvList.get(Position).getDetail());
                    //ArrayList<FoodItem> list = new ArrayList<>();
                    //list = ((FoodActicity)mContext).getlist();
                    Intent intent = new Intent(mContext, SingleActivity.class);
                    intent.putExtra("contentsId",ContentsId);
                    intent.putExtra("profileId",ProfileId);
                    mContext.startActivity(intent);

                }
            });
        }

        public void onBind(RecommendResult item) {
            //mPoster.setImageResource();

            Glide.with(mContext)
                    .load(item.getThumbnailImgUrl())
                    .centerCrop()
                    .into(mPoster);

            //mPoster.setImageResource(item.getImgURL());
        }
    }

}
