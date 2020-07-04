package com.daniel.app.netfilx_clone.src.single;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daniel.app.netfilx_clone.R;
import com.daniel.app.netfilx_clone.src.signin.SignInActivity;

public class SingleActivity extends AppCompatActivity {

    LinearLayout mLinearLayout;
    ImageView mLike;
    Point p = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        mLinearLayout = findViewById(R.id.single_total_view);

        mLike = findViewById(R.id.single_like_icon);

        mLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Open popup window
                if (p != null)
                    showPopup(SingleActivity.this, p);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        if(action==MotionEvent.ACTION_DOWN) {
//            Intent intent = new Intent(SingleActivity.this, LikeActivity.class);
//            startActivity(intent);
//        }
        return false;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];
        TextView button = (TextView) findViewById(R.id.single_like_words);

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        button.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions

        p.x = location[0];
        p.y = location[1];
    }

    public static void dimBehind(PopupWindow popupWindow) {
        View container = popupWindow.getContentView().getRootView();
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.8f;
        wm.updateViewLayout(container, p);
    }

    //............
    private void showPopup(final Activity context, Point p) {


        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.transparent);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.activity_like, viewGroup);
        layout.findViewById(R.id.like_like).setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_slide_to_left));
        layout.findViewById(R.id.like_hate).setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_slide_to_right));

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWindowLayoutMode(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = -250;
        int OFFSET_Y = -350;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

        dimBehind(popup);

        // Getting a reference to Close button, and close the popup when clicked.
        ImageView close = (ImageView) layout.findViewById(R.id.like_x_mark);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }

}
