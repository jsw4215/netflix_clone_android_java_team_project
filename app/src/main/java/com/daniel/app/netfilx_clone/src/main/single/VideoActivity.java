package com.daniel.app.netfilx_clone.src.main.single;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.daniel.app.netfilx_clone.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoActivity extends AppCompatActivity {

    Context mContext = VideoActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();
        String videoUri = intent.getStringExtra("videoUri");

        PlayerView playerView;
        playerView = findViewById(R.id.video_pv);

        SimpleExoPlayer player = new SimpleExoPlayer.Builder(mContext).build();

        playerView.setPlayer(player);

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                Util.getUserAgent(mContext, "yourApplicationName"));
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse(videoUri));
        // Prepare the player with the source.
        player.prepare(videoSource);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

}