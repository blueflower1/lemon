package com.lhw.lemon.activity;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.lhw.lemon.R;
import com.lhw.lemon.adapter.VideoListAdapter;
import com.lhw.lemon.config.Config;
import com.lhw.lemon.tools.network.MyRequest;
import com.lhw.lemon.utils.CommonUtils;


import java.util.List;

public class VideoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String videoListString = MyRequest.post(Config.VIDEO_LIST_URL, "query=" + query);
                List<String> videoList = JSONArray.parseArray(videoListString, String.class);
                if (videoList.isEmpty()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CommonUtils.topToast(VideoListActivity.this,Config.SOURCE_MISS);
                        }
                    });

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        VideoListAdapter adapter = new VideoListAdapter(videoList,VideoListActivity.this);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(VideoListActivity.this));
                    }
                });


            }
        }).start();


    }
}
