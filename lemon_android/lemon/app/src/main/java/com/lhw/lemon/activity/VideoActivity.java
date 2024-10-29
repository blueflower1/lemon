package com.lhw.lemon.activity;


import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.lhw.lemon.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("videoUrl");
        String videoName = intent.getStringExtra("videoName");
        String videoEp = intent.getStringExtra("videoEp");

        String title = "";

        if (videoEp == null) {
            videoEp = "";
        } else if ("0".equals(videoEp)) {
            title = videoName;
        } else {
            title = videoName + " 第" + videoEp + "集";
        }

        JzvdStd videoPlayer = findViewById(R.id.videoPlayer);

        videoPlayer.setUp(videoUrl,title);
    }
}
