package com.daniel.app.netfilx_clone.src.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.daniel.app.netfilx_clone.R;

public class Poster extends LinearLayout {

    private void init(Context context){
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.poster,this,true);
    }

    public Poster(Context context) {
        super(context);

        init(context);
    }

    public Poster(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public Poster(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

}
