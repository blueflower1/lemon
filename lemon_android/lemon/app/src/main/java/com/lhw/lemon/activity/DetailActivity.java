package com.lhw.lemon.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.lhw.lemon.R;
import com.lhw.lemon.config.Config;
import com.lhw.lemon.tools.network.MyRequest;
import com.lhw.lemon.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImageView1 = findViewById(R.id.detail_imageView_1);
        TextView detailTextView1 = findViewById(R.id.detail_textView_1);
        LinearLayout detailLinear1 = findViewById(R.id.detail_linear_1);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);


        new Thread(new Runnable() {
            @Override
            public void run() {
                String movieJson = MyRequest.post(Config.DETAIL_URL, "id=" + id);
                JSONObject movieJsonObject = JSON.parseObject(movieJson);
                String photoUrl = movieJsonObject.getString("photoUrl");
                String name = movieJsonObject.getString("name");
                Integer createTimeUnix = movieJsonObject.getInteger("createTime");
                Integer updateTimeUnix = movieJsonObject.getInteger("updateTime");
                Integer videoType = movieJsonObject.getInteger("videoType");
                String videoUrls = movieJsonObject.getString("videoUrls");


                String videoTypeStr = CommonUtils.videoTypeConverter(videoType);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createTime = dateFormat.format(createTimeUnix * 1000L);
                String updateTime = dateFormat.format(updateTimeUnix * 1000L);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Glide.with(getApplicationContext())
                                .load(photoUrl)
                                .into(detailImageView1);

                        String text = String.format(Config.DETAIL_VIEW_TEXT_FORMAT, name, videoTypeStr, createTime, updateTime);
                        detailTextView1.setText(text);


                        ArrayList<Button> buttonList = new ArrayList<>();

                        if (videoType == 1) {
                            Button button = new Button(DetailActivity.this);
                            button.setText(Config.MOVIE_PLAY_BUTTON_TEXT);
                            String buttonProperties;
                            buttonProperties = videoUrls + "|" + name;
                            button.setTag(buttonProperties);
                            button.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            ));
                            detailLinear1.addView(button);
                            buttonList.add(button);
                        } else {
                            List<String> videoUrlList = JSONArray.parseArray(videoUrls, String.class);

                            for (String videoUrl : videoUrlList) {
                                JSONObject jsonObject = JSONObject.parseObject(videoUrl);
                                String ep = jsonObject.getString("ep");
                                String url = jsonObject.getString("url");

                                Button button = new Button(DetailActivity.this);
                                button.setText(ep);
                                String buttonProperties;
                                buttonProperties = url + "|" + name + "|" + ep;
                                button.setTag(buttonProperties);
                                button.setLayoutParams(new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                ));
                                detailLinear1.addView(button);
                                buttonList.add(button);
                            }
                        }

                        for (Button button : buttonList) {
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String buttonProperties = (String) button.getTag();
                                    String[] propertiesList = buttonProperties.split("\\|");
                                    String videoUrl = propertiesList[0];
                                    String videoName = propertiesList[1];
                                    String videoEp;
                                    if (videoType == 1) {
                                        videoEp = "0";
                                    } else {
                                        videoEp = propertiesList[2];
                                    }
                                    //使用原生android 播放
//                                    Intent intent = new Intent(DetailActivity.this, VideoActivity.class);
//                                    intent.putExtra("videoUrl", videoUrl);
//                                    intent.putExtra("videoName", videoName);
//                                    intent.putExtra("videoEp", videoEp);
//                                    startActivity(intent);

                                    //原生android嵌入webview方式 (全屏有问题,无法投屏)
//                                    Intent intent = new Intent(DetailActivity.this, WebViewActivity.class);
//                                    intent.putExtra("videoUrl", videoUrl);
//                                    startActivity(intent);

                                    //调用第三方浏览器播放
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
                                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                        startActivity(intent);
                                    } else {
                                        CommonUtils.topToast(getApplicationContext(),Config.BROWSABLE_MISS);
                                    }
                                }
                            });
                        }

                    }
                });
            }
        }).start();

    }
}
