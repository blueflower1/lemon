package com.lhw.lemon.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.lhw.lemon.R;
import com.lhw.lemon.activity.DetailActivity;
import com.lhw.lemon.config.Config;
import com.lhw.lemon.holder.VideoListHolder;
import com.lhw.lemon.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListHolder> {

    List<String> videoList;
    Context context;

    public VideoListAdapter(List<String> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListHolder holder, int position) {
        String video = videoList.get(position);
        JSONObject videoObject = JSON.parseObject(video);
        String name = videoObject.getString("name");
        Integer id = videoObject.getInteger("id");
        String photoUrl = videoObject.getString("photoUrl");
        Integer createTimeUnix = videoObject.getInteger("createTime");
        Integer videoType = videoObject.getInteger("videoType");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = dateFormat.format(createTimeUnix * 1000L);

        Glide.with(holder.imageView).load(photoUrl).into(holder.imageView);
        holder.textView.setText(String.format(Config.VIDEO_LIST_VIEW_TEXT_FORMAT,name, CommonUtils.videoTypeConverter(videoType),createTime));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
