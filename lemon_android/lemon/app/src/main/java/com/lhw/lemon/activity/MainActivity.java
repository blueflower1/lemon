package com.lhw.lemon.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.lhw.lemon.R;
import com.lhw.lemon.config.Config;
import com.lhw.lemon.tools.network.MyRequest;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // 初始化视图
        super.onCreate(savedInstanceState);

        //增加该行代码，软键盘覆盖页面
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.acitivity_main);

        // 构建banner
        Banner banner = findViewById(R.id.banner1);

        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);


        ArrayList<ImageView> imageViews = new ArrayList<>();
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add(textView1);
        textViews.add(textView2);
        textViews.add(textView3);
        textViews.add(textView4);

        ArrayList<String> bannerImages = new ArrayList<>();
        ArrayList<Integer> idList = new ArrayList<>();
        ArrayList<Integer> idListForCardView = new ArrayList<>();

        //创建子线程发送get请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                String bannerString = MyRequest.get(Config.BANNER_URL);
                String cardViewString = MyRequest.get(Config.CARD_VIEW_URL);

                //获取到请求数据后，ui线程(主线程)使用Glide加载图片
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<String> bannerList = JSONArray.parseArray(bannerString, String.class);

                        for (String bannerInfo : bannerList) {
                            JSONObject object = JSONObject.parseObject(bannerInfo);
                            String photoUrl = object.getString("photo_url");
                            Integer id = object.getInteger("id");
                            bannerImages.add(photoUrl);
                            idList.add(id);
                        }


                        List<String> cardViewList = JSONArray.parseArray(cardViewString, String.class);

                        int i = 0;
                        for (String cardViewInfo : cardViewList) {
                            JSONObject object = JSONObject.parseObject(cardViewInfo);
                            String name = object.getString("name");
                            String photoUrl = object.getString("photo_url");
                            Integer id = object.getInteger("id");

                            Glide.with(MainActivity.this)
                                    .load(photoUrl)
                                    .into(imageViews.get(i));
                            textViews.get(i).setText(name);
                            idListForCardView.add(id);
                            i++;
                        }
                        i = 0;
                    }
                });
            }
        }).start();



        banner.setAdapter(new BannerImageAdapter<String>(bannerImages) {
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                Glide.with(MainActivity.this)
                        .load(data)
                        .into(holder.imageView);
            }
        });

        banner.isAutoLoop(true);
        banner.setIndicator(new CircleIndicator(this));
        banner.setScrollBarFadeDuration(1000);
        banner.setIndicatorSelectedColor(Color.YELLOW);
        banner.start();

        /**
         * @Author: lvhongwang
         * @Date: 2024/10/21 19:19
         * @Description: 点击banner图片跳转，使用position回传id给下一个页面
        */

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                Integer id = idList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        /**
         * @Author: lvhongwang
         * @Date: 2024/10/22 15:30
         * @Description: 点击cardView跳转至详情页
        */

        CardView cardView1 = findViewById(R.id.cardview1);
        CardView cardView2 = findViewById(R.id.cardview2);
        CardView cardView3 = findViewById(R.id.cardview3);
        CardView cardView4 = findViewById(R.id.cardview4);

        HashMap<String, CardView> cardViewsMap = new HashMap<>();
        cardViewsMap.put("0",cardView1);
        cardViewsMap.put("1",cardView2);
        cardViewsMap.put("2",cardView3);
        cardViewsMap.put("3",cardView4);

        for (Map.Entry<String, CardView> entry : cardViewsMap.entrySet()) {
            String cardViewId = entry.getKey();
            CardView cardView = entry.getValue();

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("id",idListForCardView.get(Integer.parseInt(cardViewId)));
                    startActivity(intent);
                }
            });
        }

        SearchView searchView = findViewById(R.id.searchView1);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!s.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, VideoListActivity.class);
                    intent.putExtra("query",s);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,Config.SEARCH_NULL_TOAST,Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });


    }


}
