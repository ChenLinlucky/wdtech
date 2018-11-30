package com.bw.movie.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bw.movie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.video)
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String videolist = intent.getStringExtra("videolist");
        int position = intent.getIntExtra("position",0);
        Uri parse = Uri.parse(videolist);
        video.setMediaController(new MediaController(this));
        video.setVideoURI(parse);
        video.start();

    }
}